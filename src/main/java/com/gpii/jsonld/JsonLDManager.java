package com.gpii.jsonld;

import com.google.gson.Gson;
import com.gpii.ontology.OntologyManager;
import com.gpii.ontology.UPREFS;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author nkak
 */
public class JsonLDManager 
{
    //input
    public String currentNPSet;
    public String currentDeviceManagerPayload;
  
    //static input files
    public String semanticsSolutionsFilePath;
    public String explodePrefTermsFilePath;
    public String mappingRulesFilePath;

    //temp preprocessing output files
    public String preprocessingTempFilePath_device;
    public String preprocessingTempFilePath_preferences;
    
    public Gson gson;
    
    private static JsonLDManager instance = null;
    
    private JsonLDManager() 
    {
        File f = new File(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/mappingRules.rules");
 
        if(f.exists())  //deployment mode
        {
            //static input files
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/semanticsSolutions.jsonld";
            explodePrefTermsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            mappingRulesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/mappingRules.rules";
            
            //temp preprocessing output files
            preprocessingTempFilePath_preferences = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/preprocessingPreferences.jsonld";
            preprocessingTempFilePath_device = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/preprocessingDevice.jsonld";
        }
        else            //Jetty integration tests
        {
            //static input files
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/semanticsSolutions.jsonld";
            explodePrefTermsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            mappingRulesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/mappingRules.rules";
            
            //temp preprocessing output files
            preprocessingTempFilePath_preferences = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/preprocessingPreferences.jsonld";
            preprocessingTempFilePath_device = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/preprocessingDevice.jsonld";
        }
        currentNPSet = "";
        currentDeviceManagerPayload = "";
        
        gson = new Gson();
    }
    
    public static JsonLDManager getInstance() 
    {
        if(instance == null) 
            instance = new JsonLDManager();
        return instance;
    }
    
    public void runJSONLDTests() 
    {
        //[0] Pre-processing - add context to preferences and device characteristics and store as JSONLD
        preprocessing();
 
        //[1] Load preferences (JSONLD) and device characteristics (JSONLD)
        loadPreferencesAndDevicePayload();
        
        //[2] Load other semantic data source (registry and solutions)
        loadSemanticData();
        
        //[3] Run JENA rules to infer knowledge used for conflict resolution
        runJenaRules();
    }
    
    //[0] Pre-processing - add context to preferences and device characteristics and store as JSONLD
    private void preprocessing()
    {
        try
        {
            System.out.println("\n**********************************************************************************************");
            System.out.println("* Pre-processing - add context to preferences and device characteristics and store as JSONLD *");
            System.out.println("**********************************************************************************************");

            // Transforming preferences: 
            JSONTokener preferencesTokener = new JSONTokener(currentNPSet);
            JSONObject preferences = new JSONObject(preferencesTokener);		
            JSONObject outerPrefsObject = new JSONObject();
            JSONArray prefsArray = new JSONArray();
            Iterator<?> keys = preferences.keys();
            while( keys.hasNext() )
            {
                String key = (String)keys.next();
                /**
                * create a new inner JSONobject and put: 
                * @id = URI
                * gpii:type = common or application
                * gpii:name = preference name
                * gpii:value = either the value (common) or an JSONObject of values (app-specific)   
                */ 
                JSONObject innerPrefsObject = new JSONObject();
                innerPrefsObject.put("@id", key);
                if(key.contains("common")) 
                    innerPrefsObject.put(UPREFS.type.toString(), "common"); 
                if(key.contains("applications")) 
                    innerPrefsObject.put(UPREFS.type.toString(), "applications");
                URI uri = new URI(key);
                String path = uri.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                innerPrefsObject.put(UPREFS.name.toString(), idStr);
                // transform values to gpii:value: 
                if( preferences.get(key) instanceof JSONArray )
                {
                    // outer value array	        
                    JSONArray values = new JSONArray(preferences.get(key).toString());
                    for (int i = 0, size = (values.length()); i < size; i++)
                    {	
                        // inner value object
                        innerPrefsObject = getPreferenceValues(innerPrefsObject, values.get(i), key);
                    }
                }
                prefsArray.put(innerPrefsObject);	            
            }
            //System.out.println(prefsArray);
            outerPrefsObject.put(UPREFS.preference.toString(), prefsArray);
            byte dataToWrite[] = outerPrefsObject.toString().getBytes(StandardCharsets.US_ASCII);
            writeFile(preprocessingTempFilePath_preferences, dataToWrite);
            System.out.println("File created: " + preprocessingTempFilePath_preferences);

            // Transforming solutions: 
            JSONObject solutions = new JSONObject(); 
            JSONTokener deviceTokener = new JSONTokener(currentDeviceManagerPayload);
            JSONArray device = new JSONArray(deviceTokener);			

            JSONArray sol = new JSONArray(); 
            for (int i = 0, size = device.length(); i < size; i++)
            {
                JSONObject objectInArray = device.getJSONObject(i);
                String[] elementNames = JSONObject.getNames(objectInArray);
                for (String elementName : elementNames)
                {
                    String value = objectInArray.getString(elementName);
                    sol.put(value); 
                }
            }
            solutions.put(UPREFS.installedSolutions.toString(), sol);
            byte cDataToWrite[] = solutions.toString().getBytes(StandardCharsets.US_ASCII);
            writeFile(preprocessingTempFilePath_device, cDataToWrite);
            System.out.println("File created: " + preprocessingTempFilePath_device);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //[1] Load preferences (JSONLD) and device characteristics (JSONLD)
    private void loadPreferencesAndDevicePayload()
    {
        System.out.println("\n*****************************************************************");
        System.out.println("* Load preferences (JSONLD) and device characteristics (JSONLD) *");
        System.out.println("*****************************************************************");
			
        // load accessibility namespace
        OntologyManager.getInstance().m.setNsPrefix("ax", UPREFS.NS);
        // create RDF Model from preferences and solutions
        OntologyManager.getInstance().m = ModelFactory.createDefaultModel().read(preprocessingTempFilePath_preferences, "JSON-LD");
        Model d = ModelFactory.createDefaultModel().read(preprocessingTempFilePath_device, "JSON-LD");
        OntologyManager.getInstance().m.add(d);
        OntologyManager.getInstance().m.write(System.out);

        // TODO: use ModelFactors or RDFDataMrg ? 
        //alternative to read preferences from JSONLD			
        //RDFDataMgr.read(m, inputURL.toUri().toString(), null, JenaJSONLD.JSONLD);
    }
    
    //[2] Load other semantic data source (registry and solutions)
    private void loadSemanticData()
    {
        System.out.println("\n************************************************************");
        System.out.println("* Load other semantic data source (registry and solutions) *");
        System.out.println("************************************************************");

        //Model registry = ModelFactory.createDefaultModel().read(reg, "JSON-LD");
        Model uListing = ModelFactory.createDefaultModel().read(semanticsSolutionsFilePath, "JSON-LD");
        Model exTerms = ModelFactory.createDefaultModel().read(explodePrefTermsFilePath, "JSON-LD");

        // merge the Models
        OntologyManager.getInstance().m = OntologyManager.getInstance().m.union(exTerms);
        //m = m.union(uListing);	        
        // print the Model as RDF/XML
        OntologyManager.getInstance().m.write(System.out);
    }
    
    //[3] Run JENA rules to infer knowledge used for conflict resolution
    private void runJenaRules()
    {
        System.out.println("\n******************************************************************");
        System.out.println("* Run JENA rules to infer knowledge used for conflict resolution *");
        System.out.println("******************************************************************");
        /** 
            * TODO make this mapping more general to achieve the goal that we are not limited to GPII input sources
            * This step is used for any kind of mappings from an abritary input source (here preferences from GPII) 
            * to infer required knowledge for the RBMM reasoning and vocabulary. 
            * 
            */

        File f = new File(mappingRulesFilePath);
        if (f.exists()) 
        {
            List<Rule> rules = Rule.rulesFromURL("file:" + mappingRulesFilePath);
            GenericRuleReasoner r = new GenericRuleReasoner(rules);
            InfModel infModel = ModelFactory.createInfModel(r, OntologyManager.getInstance().m);
            // starting the rule execution
            infModel.prepare();					
            // TODO why am I doing this here?
            Model deducedModel = infModel.getDeductionsModel();  
            OntologyManager.getInstance().m.add(deducedModel);
            deducedModel.write(System.out);
            //m.write(System.out);
        } 
        else
            System.out.println("That rules file does not exist.");
    }

    private static JSONObject getPreferenceValues(JSONObject outerValues, Object innerValues, String solutionID) throws JSONException 
    {
        if(innerValues instanceof JSONObject)
        {
            JSONObject inValues = new JSONObject(innerValues.toString());
            Iterator<?> inKeys = inValues.keys();
            while( inKeys.hasNext() ){
                String inKey = (String)inKeys.next();
                // value  as inner object 
                if (inValues.get(inKey) instanceof JSONObject){
                    JSONObject newInnerValues = new JSONObject();
                    newInnerValues = getPreferenceValues(newInnerValues, inValues.get(inKey), solutionID);
                    outerValues.put(UPREFS.value.toString(), newInnerValues);
                }else {
                    // value flat
                    if(inKey.equals("value")) {
                            outerValues.put(UPREFS.value.toString(), inValues.get(inKey));
                    }
                    else outerValues.put(solutionID+"/"+inKey, inValues.get(inKey));

                }
            }	        	        		
        }
        return outerValues;
    }
    
    void writeFile(String path, byte[] dataToWrite)
    {
            FileOutputStream out = null;
            try {
                    out = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            try {
                    out.write(dataToWrite);
                    out.close();
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
    
    
}
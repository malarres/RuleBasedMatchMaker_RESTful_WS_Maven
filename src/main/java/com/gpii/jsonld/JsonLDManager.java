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
    public String semanticsSolutionsFilePath;
    public String semanticsSolutionsString;

    public String explodePreferenceTermsFilePath;
    public String explodePrefTermsString;

    public String preferenceTempFilePath;
    public String preferenceInputString;

    public String solutionseTempFilePath;
    public String solutionsString;
    
    public String currentDeviceManagerPayload;
    public String currentDeviceManagerPayloadTempFilePath;
    public String currentNPSet;
    public String currentNPSetTempFilePath;
    
    public String rdfXMLTempFilePath1;
    public String rdfXMLTempFilePath2;
    public String deducedModelTemplFilePath;
    
    public String mappingRules;
    
    public Gson gson;
    
    private static JsonLDManager instance = null;
    
    private JsonLDManager() 
    {
        File f = new File(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/mappingRules.rules");
 
        if(f.exists())  //deployment mode
        {
            preferenceTempFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/preferences.jsonld";
            solutionseTempFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/solutions.jsonld";
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/semanticsSolutions.jsonld";
            explodePreferenceTermsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/explodePreferenceTermsFilePath.jsonld";
            mappingRules = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/mappingRules.rules";
            currentNPSetTempFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/currentNPSet.jsonld";
            currentDeviceManagerPayloadTempFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/currentDeviceManagerPayload.jsonld";
            rdfXMLTempFilePath1 = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/rdfXML1.rdf";
            rdfXMLTempFilePath2 = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/rdfXML2.rdf";
            deducedModelTemplFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/deducedModel.rdf";
        }
        else            //Jetty integration tests
        {
            preferenceTempFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/preferences.jsonld";
            solutionseTempFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/solutions.jsonld";
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/semanticsSolutions.jsonld";
            explodePreferenceTermsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/explodePreferenceTermsFilePath.jsonld";
            mappingRules = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/mappingRules.rules";
            currentNPSetTempFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/currentNPSet.jsonld";
            currentDeviceManagerPayloadTempFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/currentDeviceManagerPayload.jsonld";
            rdfXMLTempFilePath1 = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/rdfXML1.rdf";
            rdfXMLTempFilePath2 = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/rdfXML2.rdf";
            deducedModelTemplFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/deducedModel.rdf";
        }
        semanticsSolutionsString = "";
        explodePrefTermsString = "";
        solutionsString = "";
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
        try
        {
            JSONTokener currentNPSetTokener = new JSONTokener(JsonLDManager.getInstance().currentNPSet);
            JSONObject currentNP = new JSONObject(currentNPSetTokener);		

            JSONObject outerPrefsObject = new JSONObject();
            JSONArray prefsArray = new JSONArray();

            Iterator<?> keys = currentNP.keys();
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
                if (key.contains("common")) 
                    innerPrefsObject.put(UPREFS.type.toString(), "common"); 
                if (key.contains("applications")) 
                    innerPrefsObject.put(UPREFS.type.toString(), "applications");
                URI uri = new URI(key);
                String path = uri.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                innerPrefsObject.put(UPREFS.name.toString(), idStr);
                // transform values to gpii:value: 
                if( currentNP.get(key) instanceof JSONArray )
                {
                    // outer value array	        
                    JSONArray values = new JSONArray(currentNP.get(key).toString());
                    for (int i = 0, size = (values.length()); i < size; i++)
                    {	
                        // inner value object
                        innerPrefsObject = getPreferenceValues(innerPrefsObject, values.get(i), key);
                    }
                }
                prefsArray.put(innerPrefsObject);	            
            }
            
            outerPrefsObject.put(UPREFS.preference.toString(), prefsArray);
            byte dataToWrite[] = outerPrefsObject.toString().getBytes(StandardCharsets.US_ASCII);
            writeFile(JsonLDManager.getInstance().currentNPSetTempFilePath, dataToWrite);

            // Transforming solutions: 
            JSONObject solutions = new JSONObject(); 
            //String deviceString = readFile(deviceFile, StandardCharsets.UTF_8);
            JSONTokener deviceTokener = new JSONTokener(new String(JsonLDManager.getInstance().currentDeviceManagerPayload.getBytes(), StandardCharsets.UTF_8));
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
            writeFile(JsonLDManager.getInstance().currentDeviceManagerPayloadTempFilePath, cDataToWrite);

            //Load preferences (JSONLD) and device characteristics (JSONLD)

            // load accessibility namespace
            OntologyManager.getInstance().m.setNsPrefix("ax", UPREFS.NS);
            // create RDF Model from preferences and solutions
            
            File tempFile = new File(JsonLDManager.getInstance().preferenceTempFilePath);
            FileWriter tempFileWriter = new FileWriter(tempFile);
            tempFileWriter.write(JsonLDManager.getInstance().preferenceInputString);
            tempFileWriter.close();
            
            OntologyManager.getInstance().m = ModelFactory.createDefaultModel().read(JsonLDManager.getInstance().preferenceTempFilePath, "JSON-LD");
            
            tempFile = new File(JsonLDManager.getInstance().solutionseTempFilePath);
            tempFileWriter = new FileWriter(tempFile);
            tempFileWriter.write(JsonLDManager.getInstance().solutionsString);
            tempFileWriter.close();
            
            Model d = ModelFactory.createDefaultModel().read(JsonLDManager.getInstance().solutionseTempFilePath, "JSON-LD");
            OntologyManager.getInstance().m.add(d);
            //m.write(System.out);

            
            // TODO: use ModelFactors or RDFDataMrg ? 
            //alternative to read preferences from JSONLD			
            //RDFDataMgr.read(m, inputURL.toUri().toString(), null, JenaJSONLD.JSONLD);

            //Load other semantic data source (registry and solutions)

            //Model registry = ModelFactory.createDefaultModel().read(reg, "JSON-LD");
            
            tempFile = new File(JsonLDManager.getInstance().semanticsSolutionsFilePath);
            tempFileWriter = new FileWriter(tempFile);
            tempFileWriter.write(JsonLDManager.getInstance().semanticsSolutionsString);
            tempFileWriter.close();
            
            Model uListing = ModelFactory.createDefaultModel().read(JsonLDManager.getInstance().semanticsSolutionsFilePath, "JSON-LD");
            
            tempFile = new File(JsonLDManager.getInstance().explodePreferenceTermsFilePath);
            tempFileWriter = new FileWriter(tempFile);
            tempFileWriter.write(JsonLDManager.getInstance().explodePrefTermsString);
            tempFileWriter.close();
            
            Model exTerms = ModelFactory.createDefaultModel().read(JsonLDManager.getInstance().explodePreferenceTermsFilePath, "JSON-LD");

            // merge the Models
            OntologyManager.getInstance().m = OntologyManager.getInstance().m.union(exTerms);
            //m = m.union(uListing);	        
            // print the Model as RDF/XML
            //m.write(System.out);
            
            Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(JsonLDManager.getInstance().rdfXMLTempFilePath1), "utf-8"));
                OntologyManager.getInstance().m.write(writer);
            } catch (IOException ex) {
            // report
            } finally {
            try {writer.close();} catch (Exception ex) {}
            }

            //Run JENA rules to infer knowledge used for conflict resolution
            /** 
                * TODO make this mapping more general to achieve the goal that we are not limited to GPII input sources
                * This step is used for any kind of mappings from an abritary input source (here preferences from GPII) 
                * to infer required knowledge for the RBMM reasoning and vocabulary. 
                * 
                */            

            File f = new File(JsonLDManager.getInstance().mappingRules);
            if (f.exists()) 
            {
                List<Rule> rules = Rule.rulesFromURL("file:" + JsonLDManager.getInstance().mappingRules);
                GenericRuleReasoner r = new GenericRuleReasoner(rules);
                InfModel infModel = ModelFactory.createInfModel(r, OntologyManager.getInstance().m);
                // starting the rule execution
                infModel.prepare();					
                // TODO why am I doing this here?
                Model deducedModel = infModel.getDeductionsModel();  
                OntologyManager.getInstance().m.add(deducedModel);
                //deducedModel.write(System.out);
                //m.write(System.out);
                
                writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(JsonLDManager.getInstance().deducedModelTemplFilePath), "utf-8"));
                    deducedModel.write(writer);
                } catch (IOException ex) {
                // report
                } finally {
                try {writer.close();} catch (Exception ex) {}
                }
                
                writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(JsonLDManager.getInstance().rdfXMLTempFilePath2), "utf-8"));
                    OntologyManager.getInstance().m.write(writer);
                } catch (IOException ex) {
                // report
                } finally {
                try {writer.close();} catch (Exception ex) {}
                }
            } 
            else
                System.out.println("That rules file does not exist.\n\n");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
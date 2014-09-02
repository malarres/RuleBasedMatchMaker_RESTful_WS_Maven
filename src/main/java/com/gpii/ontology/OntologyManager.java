package com.gpii.ontology;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.json.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.atlas.lib.Bytes;
import org.apache.jena.riot.RDFDataMgr;

import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.jena.JenaJSONLD;
import com.github.jsonldjava.utils.JSONUtils;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.shared.DoesNotExistException;

/**
 *
 * @author nkak
 */
public class OntologyManager
{
	
    // default model automatically initialized with data from JSON-LD  	
    public static Model m;

    //	accessibilityConflictModel
    public static Model acm;

    public static BufferedReader br;

    
    /** 
     * TODO this are static input source used in the RBMM Web Service.
     * These input sources should be exchangeable with any other semantic representations of preference terms or solutions
     */
    
    // Define semantic representations: 
    // String reg = "C:\\eclipse\\workspace\\PrototypeRBMM_Maven\\RBMMPlayground\\src\\main\\java\\gpii\\semantics\\registry.jsonld";
    String semanticsSolutions;
    String explodePrefTerms;

    // Final input format for the reasoning process. Created in [0].  
    String preferenceInput;
    String solutionsInput;
    
    // solution test file for multiple solutions conflict:
    String deviceFile;
    String preferenceFile;
    String mappingRules;

    
    boolean printDebugInfo;
    public String debug;
    private static OntologyManager instance = null;
    
    private OntologyManager() 
    {
        File f = new File(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/semanticsSolutions.jsonld");
 
        if(f.exists())  //deployment mode
        {
            semanticsSolutions = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/semanticsSolutions.jsonld";
            explodePrefTerms = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            preferenceInput = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/input/preferences.jsonld";
            solutionsInput = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/input/solutions.jsonld";
            deviceFile = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/installedSolutions/multipleMagnifierScreenreader.json";
            preferenceFile = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INFtestData/preferences/pointerControllEnhancement.json";
            mappingRules = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/mappingRules.rules";
        }
        else            //Jetty integration tests
        {
            semanticsSolutions = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/semanticsSolutions.jsonld";
            explodePrefTerms = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            preferenceInput = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/input/preferences.jsonld";
            solutionsInput = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/input/solutions.jsonld";
            deviceFile = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/installedSolutions/multipleMagnifierScreenreader.json";
            preferenceFile = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/preferences/pointerControllEnhancement.json";
            mappingRules = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/mappingRules.rules";
        }
        
        debug = "";
        printDebugInfo = false;
        
        // JenaJSONLD must be initialized so that the readers and writers are registered with Jena.
        JenaJSONLD.init();
        m = ModelFactory.createDefaultModel();
    }
    
    public static OntologyManager getInstance() 
    {
        if(instance == null) 
            instance = new OntologyManager();
        return instance;
    }
    
    public void runJSONLDTests() 
    {
        try
        {
            //Load preferences (JSONLD) and device characteristics (JSONLD)
            //Pre-processing - add context to preferences and device characteristics and store as JSONLD

            // Transforming preferences: 
            String preferenceString = readFile(preferenceFile, StandardCharsets.UTF_8);
            JSONTokener preferencesTokener = new JSONTokener(preferenceString);
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
                if (key.contains("common")) 
                    innerPrefsObject.put(UPREFS.type.toString(), "common"); 
                if (key.contains("applications")) 
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
            
            outerPrefsObject.put(UPREFS.preference.toString(), prefsArray);
            byte dataToWrite[] = outerPrefsObject.toString().getBytes(StandardCharsets.US_ASCII);
            writeFile(preferenceInput, dataToWrite);

            // Transforming solutions: 
            JSONObject solutions = new JSONObject(); 
            String deviceString = readFile(deviceFile, StandardCharsets.UTF_8);
            JSONTokener deviceTokener = new JSONTokener(deviceString);
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
            writeFile(solutionsInput, cDataToWrite);

            //Load preferences (JSONLD) and device characteristics (JSONLD)

            // load accessibility namespace
            m.setNsPrefix("ax", UPREFS.NS);
            // create RDF Model from preferences and solutions
            m = ModelFactory.createDefaultModel().read(preferenceInput, "JSON-LD");
            Model d = ModelFactory.createDefaultModel().read(solutionsInput, "JSON-LD");
            m.add(d);
            //m.write(System.out);

            // TODO: use ModelFactors or RDFDataMrg ? 
            //alternative to read preferences from JSONLD			
            //RDFDataMgr.read(m, inputURL.toUri().toString(), null, JenaJSONLD.JSONLD);

            //Load other semantic data source (registry and solutions)

            //Model registry = ModelFactory.createDefaultModel().read(reg, "JSON-LD");
            Model uListing = ModelFactory.createDefaultModel().read(semanticsSolutions, "JSON-LD");
            Model exTerms = ModelFactory.createDefaultModel().read(explodePrefTerms, "JSON-LD");

            // merge the Models
            m = m.union(exTerms);
            //m = m.union(uListing);	        
            // print the Model as RDF/XML
            //m.write(System.out);

            //Run JENA rules to infer knowledge used for conflict resolution
            /** 
                * TODO make this mapping more general to achieve the goal that we are not limited to GPII input sources
                * This step is used for any kind of mappings from an abritary input source (here preferences from GPII) 
                * to infer required knowledge for the RBMM reasoning and vocabulary. 
                * 
                */            

            File f = new File(mappingRules);
            if (f.exists()) 
            {
                List<Rule> rules = Rule.rulesFromURL("file:" + mappingRules);
                GenericRuleReasoner r = new GenericRuleReasoner(rules);
                InfModel infModel = ModelFactory.createInfModel(r, m);
                // starting the rule execution
                infModel.prepare();					
                // TODO why am I doing this here?
                Model deducedModel = infModel.getDeductionsModel();  
                m.add(deducedModel);
                //deducedModel.write(System.out);
                //m.write(System.out);
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

    String readFile(String path, Charset encoding) throws IOException 
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    void writeFile(String path, byte[] dataToWrite)
    {
        FileOutputStream out = null;
        try 
        {
            out = new FileOutputStream(path);
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        
        try 
        {
            out.write(dataToWrite);
            out.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public String testHello(String tmpName)
    {
        return "Hello " + tmpName + "!";
    }
    
}
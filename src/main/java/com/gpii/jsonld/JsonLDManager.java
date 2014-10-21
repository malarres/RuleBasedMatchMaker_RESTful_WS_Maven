package com.gpii.jsonld;

import com.google.gson.Gson;
import com.gpii.ontology.OntologyManager;
import com.gpii.ontology.UPREFS;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
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
 * @author Claudia Loitsch
 */
public class JsonLDManager 
{
    //input
    public String currentNPSet;
    public String currentDeviceManagerPayload;
  
    //static input files
    //public String semanticsSolutionsFilePath;
    //public String explodePrefTermsFilePath;
    public String mappingRulesFilePath;

    //temp preprocessing output files
    public String preprocessingTempfilePath_defaultInput;
    public String postprocessingTempfilePath_Output;
    
    public Gson gson;
    
    private static JsonLDManager instance = null;
    
    private JsonLDManager() 
    {
        File f = new File(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/basicAlignment.rules");
 
        if(f.exists())  //deployment mode
        {
            //static input files
            //semanticsSolutionsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/semanticsSolutions.jsonld";
            //explodePrefTermsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            
        	/**
        	 * TODO find a new location, not in folder test data; split ontology alingment rules from matching rules 
        	 */
        	mappingRulesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/basicAlignment.rules";
            
            // temp preprocessing output files
            preprocessingTempfilePath_defaultInput = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/TEMP/preprocessingDefaultInput.jsonld";
            postprocessingTempfilePath_Output = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/postprocessingOutput.json";            

        }
        else            //Jetty integration tests
        {
            //static input files
            //semanticsSolutionsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/semanticsSolutions.jsonld";
            //explodePrefTermsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            mappingRulesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/basicAlignment.rules";
            
            //temp preprocessing output files
            preprocessingTempfilePath_defaultInput = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/preprocessingDefaultInput.jsonld";
            postprocessingTempfilePath_Output = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/postprocessingOutput.json";

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

        // preprocessing();
    	
    	// populate all default JSONLDInput to a model 
    	OntologyManager.getInstance().populateJSONLDInput(preprocessingTempfilePath_defaultInput);
    	
    	// infer configuration 
    	Model imodel = inferConfiguration(OntologyManager._dmodel, mappingRulesFilePath);
    	
    	// create MM output
    	createMMoutput(imodel);
    	
 

    }
    
    public Model inferConfiguration(Model model, String ruleFile){
		
	    String mappingRules = "C:\\eclipse\\workspace\\PrototypeRBMM_Maven\\RBMMPlayground\\src\\main\\java\\gpii\\testData\\rules\\basicAlignment.rules";

	    File f = new File(ruleFile);
		if (f.exists()) {
			List<Rule> rules = Rule.rulesFromURL("file:" + mappingRules);
			
			GenericRuleReasoner r = new GenericRuleReasoner(rules);
			
			InfModel infModel = ModelFactory.createInfModel(r, model);		    
			infModel.prepare();					

		    Model deducedModel = infModel.getDeductionsModel();  
			model.add(deducedModel);
		}
   	
    	return model;    	
    }
    
	public void createMMoutput(Model model){

		runQuery(" select DISTINCT (str(?c) as ?contextID) (str(?an) as ?appName) (str(?aa) as ?appActive) (str(?si) as ?setID) (str(?sv) as ?setValue) where{ ?if rdf:type c4a:InferredConfiguration. ?if c4a:id ?c. ?if c4a:refersTo ?app. ?app c4a:name ?an. ?app c4a:isActive ?aa. ?app c4a:settings ?set. ?set c4a:id ?si. ?set c4a:value ?sv. } ORDER BY DESC(?c)", model);  //add the query string

	}  
    private void runQuery(String queryRequest, Model model){
		
		StringBuffer queryStr = new StringBuffer();
		// Establish Prefixes
		//Set default Name space first
		queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
		queryStr.append("PREFIX c4a" + ": <" + "http://rbmm.org/schemas/cloud4all/0.1/" + "> ");
		
		//Now add query
		queryStr.append(queryRequest);
		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
		ResultSet response = qexec.execSelect();
		
		// JSON Object spec the MM output
		JSONObject mmOut = new JSONObject();
		JSONObject infConfig = new JSONObject();
		JSONObject contextSet;
		JSONObject appSet;
		JSONObject solution;
		JSONObject settings;
		
		mmOut.put("inferredConfiguration", infConfig);
		
		while(response.hasNext()){

			
			QuerySolution soln = response.nextSolution();
			System.out.println("out: "+ soln.toString());
		
			RDFNode contextID 	= soln.get("?contextID");
			RDFNode appName 	= soln.get("?appName");
			RDFNode appActive 	= soln.get("?appActive");
			RDFNode setId 		= soln.get("?setID");
			RDFNode setValue 	= soln.get("?setValue");
			
			infConfig = mmOut.getJSONObject("inferredConfiguration"); 
			
			if(infConfig.has(contextID.toString())){
				
				contextSet = infConfig.getJSONObject(contextID.toString());
				
			}else {
				
				appSet = new JSONObject();
				infConfig.put(contextID.toString(), appSet);
				contextSet = infConfig.getJSONObject(contextID.toString());
				
			}
			
			if(contextSet.has("applications")){
				
				appSet = contextSet.getJSONObject("applications");			

			}else {
				
				appSet = new JSONObject();
				contextSet.put("applications", appSet);
				appSet = contextSet.getJSONObject("applications");
			}
			
			if(appSet.has(appName.toString())){
				
				solution = appSet.getJSONObject(appName.toString());
			}else{
				solution = new JSONObject();
				appSet.put(appName.toString(), solution);
				solution = appSet.getJSONObject(appName.toString());
			}
			
			if(!solution.has("active")){
				
				solution.put("active", appActive.toString());
			}

			if(solution.has("settings")){
				settings = solution.getJSONObject("settings");
				settings.put(setId.toString(), setValue.toString());
			}else {
				settings = new JSONObject(); 
				settings.put(setId.toString(), setValue.toString());
				solution.put("settings", settings);
			}
	
		}
	    byte dataToWrite[] = mmOut.toString().getBytes(StandardCharsets.US_ASCII);
	    writeFile(postprocessingTempfilePath_Output, dataToWrite);
	    
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally { qexec.close();}				

	}
    
	/**
	 * TODO Pre-processing 
	 * Create a new class performing translating from JSON input to JSONLD
	 * Expected input is defined here: https://code.stypi.com/kaspermarkus/MM%20stuff/MM%20Input.js
	 * Expected output is content of file preprocessingTempfilePath_defaultInput    
	 */
    private void preprocessing() {}



    /**
     * TODO create a class for help functions
     * @param path where to write the file
     * @param dataToWrite // data to write in the file
     */
    private void writeFile(String path, byte[] dataToWrite){
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
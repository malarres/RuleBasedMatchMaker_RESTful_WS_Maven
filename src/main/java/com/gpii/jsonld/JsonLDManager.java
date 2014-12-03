package com.gpii.jsonld;

import com.google.gson.Gson;
import com.gpii.ontology.OntologyManager;
import com.gpii.transformer.TransformerManager;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
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
    public static final boolean INTEGRATION_TESTS_INCLUDE_ONTOLOGY_TRANSFORMATION_INTO_JSONLD = false; 
    
    //static input files
    public String semanticsSolutionsFilePath;
    public String semanticsSolutionsGeneratedFromOwlFilePath;
    public String feedbackMessagesFilePath;
    //public String explodePrefTermsFilePath;
    public String mappingRulesFilePath;
    public String decConflictsRulesFilePath;
    public String resConflictsRulesFilePath;
    public String addPrefSolutionToConfig;
    public String addFeebackRulesFilePath;
    
    /**
     * TODO make a global configuration for all C4a specific files
     */
    public String querryCondPath;
    public String querryAppsPath;
    public String querryMetaDataPath;
    
    public Gson gson;
    
    private static JsonLDManager instance = null;
    
    private JsonLDManager() 
    {
        File f = new File(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/basicAlignment.rules");
 
        if(f.exists())  //deployment mode
        {
            //static input files
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/semanticsSolutions.jsonld";
            semanticsSolutionsGeneratedFromOwlFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/semanticsSolutions_GENERATED.jsonld";
            feedbackMessagesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/feedbackMessages.jsonld";
            //explodePrefTermsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            
        	/**
        	 * TODO find a new location, not in folder test data; split ontology alingment rules from matching rules 
        	 */
        	mappingRulesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/basicAlignment.rules";
        	decConflictsRulesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/conflictDetection.rules";
        	resConflictsRulesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/conflictresolution.rules";
        	addPrefSolutionToConfig = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/AddPreferredSolutionToConfiguration.rules";
        	addFeebackRulesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/feedback.rules";
        	// querries 
        	querryCondPath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/queries/outCondition.sparql";
        	querryAppsPath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/queries/outApplications.sparql";
        	querryMetaDataPath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/queries/outMetadata.sparql";
        }
        else            //Jetty integration tests
        {
            //static input files
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/semanticsSolutions.jsonld";
            semanticsSolutionsGeneratedFromOwlFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/semanticsSolutions_GENERATED.jsonld";
            feedbackMessagesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/feedbackMessages.jsonld";
            //explodePrefTermsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            mappingRulesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/basicAlignment.rules";
            decConflictsRulesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/conflictDetection.rules";
            resConflictsRulesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/conflictResolution.rules";
            addPrefSolutionToConfig = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/AddPreferredSolutionToConfiguration.rules";
            addFeebackRulesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/feedback.rules";
            
        	querryCondPath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/queries/outCondition.sparql";
        	querryAppsPath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/queries/outApplications.sparql";
        	querryMetaDataPath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/queries/outMetadata.sparql";        	
        }
        
        gson = new Gson();
    }
    
    public static JsonLDManager getInstance() 
    {
        if(instance == null) 
            instance = new JsonLDManager();
        return instance;
    }
    
    public String runJSONLDTests(String tmpInputJsonStr) throws IOException, JSONException 
    {
        String resJsonStr = "";
        
        String transIn =  TransformerManager.getInstance().transformInput(tmpInputJsonStr);
    	
        /**
         * TODO make it configurable to add various input, e.g other semantics.
         *  
         */
    	// populate all JSONLDInput to a model 
    	OntologyManager.getInstance().populateJSONLDInput(transIn, new String[] {semanticsSolutionsFilePath, feedbackMessagesFilePath});
    	
    	// infer configuration 
    	Model imodel = inferConfiguration(OntologyManager._dmodel, new String[] {mappingRulesFilePath, decConflictsRulesFilePath, addPrefSolutionToConfig, resConflictsRulesFilePath, addFeebackRulesFilePath});
    	
    	// create MM output
    	/**
    	 * TODO make a global configuration for cloud4all to use the specific C4a queries
    	 */
    	String[] queries = {querryMetaDataPath, querryCondPath, querryAppsPath};
    	
    	resJsonStr = TransformerManager.getInstance().transformOutput(imodel, queries);

        return resJsonStr;
    }
    
    public Model inferConfiguration(Model model, String[] rulePths)
    {
        for (String path : rulePths) {
        	
            File f = new File(path);
            if (f.exists()) 
            {
	            List<Rule> rules = Rule.rulesFromURL("file:" + path);
	            
	            System.out.println(rules.toString());
	
	            GenericRuleReasoner r = new GenericRuleReasoner(rules);
	
	            InfModel infModel = ModelFactory.createInfModel(r, model);		    
	            
	            infModel.prepare();					
	
	            Model deducedModel = infModel.getDeductionsModel();  
	            
	            model.add(deducedModel);
	            
	            //deducedModel.write(System.out, "N-TRIPLE");
	            //model.write(System.out, "N-TRIPLE");
            }
        	
        }
    	return model;    	
    }

    
    
    
    
}
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    //static input files
    public String semanticsSolutionsFilePath;
    //public String explodePrefTermsFilePath;
    public String mappingRulesFilePath;
    
    /**
     * TODO make a global configuration for all C4a specific files
     */
    public String querryCondPath;
    public String querryAppsPath;
    
    //temp preprocessing output files
    public String preprocessingTempfilePath;
    public String postprocessingTempfilePath;

    
    public Gson gson;
    
    private static JsonLDManager instance = null;
    
    private JsonLDManager() 
    {
        File f = new File(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/basicAlignment.rules");
 
        if(f.exists())  //deployment mode
        {
            //static input files
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/semanticsSolutions.jsonld";
            //explodePrefTermsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            
        	/**
        	 * TODO find a new location, not in folder test data; split ontology alingment rules from matching rules 
        	 */
        	mappingRulesFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/rules/basicAlignment.rules";
        	
        	// querries 
        	querryCondPath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/queries/outCondition.sparql";
        	querryAppsPath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/testData/queries/outApplications.sparql";
            
            postprocessingTempfilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/postprocessingOutput.json";            

        }
        else            //Jetty integration tests
        {
            //static input files
            semanticsSolutionsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/semanticsSolutions.jsonld";
            //explodePrefTermsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/explodePreferenceTerms.jsonld";
            mappingRulesFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/rules/basicAlignment.rules";
            
        	querryCondPath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/queries/outCondition.sparql";
        	querryAppsPath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/queries/outApplications.sparql";
        	
            postprocessingTempfilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/TEMP/postprocessingOutput.json";

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
         * TODO use String object and not temp  
         */
    	// populate all JSONLDInput to a model 
    	OntologyManager.getInstance().populateJSONLDInput(transIn, new String[] {semanticsSolutionsFilePath});
    	
    	// infer configuration 
    	Model imodel = inferConfiguration(OntologyManager._dmodel, mappingRulesFilePath);
    	
    	// create MM output
    	/**
    	 * TODO make a global configuration for cloud4all to use the specific C4a queries
    	 */
    	String[] queries = {querryCondPath, querryAppsPath};
    	
    	/**
    	 * TODO writing TEMP output is not required anymore; it is just there for testing issues 
    	*/
    	byte [] outToWrite = TransformerManager.getInstance().transformOutput(imodel, queries);
    	writeFile(postprocessingTempfilePath, outToWrite);    	
        
        resJsonStr = new String(outToWrite, "UTF-8");
        
        return resJsonStr;
    }
    
    public Model inferConfiguration(Model model, String ruleFile)
    {
        File f = new File(ruleFile);
        if (f.exists()) 
        {
                List<Rule> rules = Rule.rulesFromURL("file:" + mappingRulesFilePath);

                GenericRuleReasoner r = new GenericRuleReasoner(rules);

                InfModel infModel = ModelFactory.createInfModel(r, model);		    
                infModel.prepare();					

            Model deducedModel = infModel.getDeductionsModel();  
                model.add(deducedModel);
            	model.write(System.out, "N-TRIPLE");
        }
    	return model;    	
    }

    
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
    
    /**
     * TODO create a class for help functions
     * @param path where to write the file
     * @param dataToWrite // data to write in the file
     */
	static String readFile(String path, Charset encoding) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
}
    
    
}
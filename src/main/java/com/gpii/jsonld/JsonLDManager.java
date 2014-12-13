package com.gpii.jsonld;

import com.google.gson.Gson;
import com.gpii.ontology.OntologyManager;
import com.gpii.transformer.TransformerManager;
import com.gpii.utils.Utils;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import java.io.*;
import java.util.List;
import java.util.Properties;
import org.json.JSONException;

/**
 *
 * @author nkak
 * @author Claudia Loitsch
 */
 
public class JsonLDManager 
{

    public boolean INTEGRATION_TESTS_INCLUDE_ONTOLOGY_TRANSFORMATION_INTO_JSONLD; 
    public boolean USE_THE_REAL_ONTOLOGY;
    
    //static input files
    public String semanticsSolutionsFilePath;
    public String semanticsSolutionsGeneratedFromOwlFilePath;
    public String feedbackMessagesFilePath;    
    public String [] semantics;
    //public String explodePrefTermsFilePath;
    
    public String mappingRulesFilePath;
    public String decConflictsRulesFilePath;
    public String resConflictsRulesFilePath;
    public String addPrefSolutionToConfig;
    public String addFeebackRulesFilePath;

    public String [] rules; 
    
    public String [] queries; 
    
    public String querryCondPath;
    public String querryAppsPath;
    public String querryMetaDataPath;
    
    //files for debugging
    public String initialJsonInputFilepath;
    public String transformedJsonLDInputFilepath;
    public String initialOntModelFilepath;
    public String inferredOntModelFilepath;
    public String rbmmJsonOutputFilepath;
    //-files for debugging
    
    public Gson gson;
    
    private static JsonLDManager instance = null;
    
    private JsonLDManager() 
    {
        //read properties file
        Properties prop = new Properties();
        InputStream configInputStream = null;
        
        File f = new File(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/config.properties");
 
        try
        {
            if(f.exists())  //Deployment mode
            {
                configInputStream = new FileInputStream(System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/config.properties");
                //read properties file
                prop.load(configInputStream);
                
                //static input files
                //semanticsSolutionsFilePath = System.getProperty("user.dir") + prop.getProperty("semanticsSolutionsFilePath_DEPLOYMENT");
                semanticsSolutionsGeneratedFromOwlFilePath = System.getProperty("user.dir") + prop.getProperty("semanticsSolutionsGeneratedFromOwlFilePath_DEPLOYMENT");
                semantics = prop.getProperty("semanticsSolutionsFilePath_DEPLOYMENT").split(";");
                //debug
                initialJsonInputFilepath = System.getProperty("user.dir") + prop.getProperty("initialJsonInputFilepath_DEPLOYMENT");
                transformedJsonLDInputFilepath = System.getProperty("user.dir") + prop.getProperty("transformedJsonLDInputFilepath_DEPLOYMENT");
                initialOntModelFilepath = System.getProperty("user.dir") + prop.getProperty("initialOntModelFilepath_DEPLOYMENT");
                inferredOntModelFilepath = System.getProperty("user.dir") + prop.getProperty("inferredOntModelFilepath_DEPLOYMENT");
                rbmmJsonOutputFilepath = System.getProperty("user.dir") + prop.getProperty("rbmmJsonOutputFilepath_DEPLOYMENT");
                //-debug

                //explodePrefTermsFilePath = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/semantics/explodePreferenceTerms.jsonld";

                /**
                 * TODO find a new location, not in folder test data; split ontology alignment rules from matching rules 
                 */
                //mappingRulesFilePath = System.getProperty("user.dir") + prop.getProperty("mappingRulesFilePath_DEPLOYMENT");
                rules = prop.getProperty("mappingRulesFilePath_DEPLOYMENT").split(";");

                // queries 
                queries = prop.getProperty("querryPaths_DEPLOYMENT").split(";");
                //querryCondPath = System.getProperty("user.dir") + prop.getProperty("querryCondPath_DEPLOYMENT");
                //querryAppsPath = System.getProperty("user.dir") + prop.getProperty("querryAppsPath_DEPLOYMENT");
            }
            else            //Jetty integration tests
            {
                configInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/config.properties");
                //read properties file
                prop.load(configInputStream);
                
                //static input files
                //semanticsSolutionsFilePath = System.getProperty("user.dir") + prop.getProperty("semanticsSolutionsFilePath_JETTY");
                //semanticsSolutionsGeneratedFromOwlFilePath = System.getProperty("user.dir") + prop.getProperty("semanticsSolutionsGeneratedFromOwlFilePath_JETTY");
                semantics = prop.getProperty("semanticsSolutionsFilePath_JETTY").split(";");
                //debug
                initialJsonInputFilepath = System.getProperty("user.dir") + prop.getProperty("initialJsonInputFilepath_JETTY");
                transformedJsonLDInputFilepath = System.getProperty("user.dir") + prop.getProperty("transformedJsonLDInputFilepath_JETTY");
                initialOntModelFilepath = System.getProperty("user.dir") + prop.getProperty("initialOntModelFilepath_JETTY");
                inferredOntModelFilepath = System.getProperty("user.dir") + prop.getProperty("inferredOntModelFilepath_JETTY");
                rbmmJsonOutputFilepath = System.getProperty("user.dir") + prop.getProperty("rbmmJsonOutputFilepath_JETTY");
                //-debug

                //explodePrefTermsFilePath = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/semantics/explodePreferenceTerms.jsonld";
                //mappingRulesFilePath = System.getProperty("user.dir") + prop.getProperty("mappingRulesFilePath_JETTY");
                rules = prop.getProperty("mappingRulesFilePath_JETTY").split(";");
                
                queries = prop.getProperty("querryPaths_JETTY").split(";");
                //querryCondPath = System.getProperty("user.dir") + prop.getProperty("querryCondPath_JETTY");
                //querryAppsPath = System.getProperty("user.dir") + prop.getProperty("querryAppsPath_JETTY");
            }
            INTEGRATION_TESTS_INCLUDE_ONTOLOGY_TRANSFORMATION_INTO_JSONLD = Boolean.parseBoolean(prop.getProperty("INTEGRATION_TESTS_INCLUDE_ONTOLOGY_TRANSFORMATION_INTO_JSONLD"));
            USE_THE_REAL_ONTOLOGY = Boolean.parseBoolean(prop.getProperty("USE_THE_REAL_ONTOLOGY"));
            
            if(USE_THE_REAL_ONTOLOGY)
                semanticsSolutionsFilePath = semanticsSolutionsGeneratedFromOwlFilePath;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (configInputStream != null) {
		try {
                    configInputStream.close();
		} catch (IOException e) {
                    e.printStackTrace();
		}
            }
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
        
        /*************************/
        /* PREPROCESSING - Start */
        /*************************/
        
        //debug - write initial JSON input to file
        Utils.getInstance().writeFile(initialJsonInputFilepath, Utils.getInstance().jsonPrettyPrint(tmpInputJsonStr));
        
        //transform input to JSON-LD
        String transIn =  TransformerManager.getInstance().transformInput(tmpInputJsonStr);
        
        //debug - write transformed JSON-LD input to file
        Utils.getInstance().writeFile(transformedJsonLDInputFilepath, Utils.getInstance().jsonPrettyPrint(transIn));
    	
    	//populate all JSON-LD input to a model 
    	OntologyManager.getInstance().populateJSONLDInput(transIn, semantics);
    	
        //debug - write initial model to .owl
        Utils.getInstance().writeOntologyModelToFile(OntologyManager._dmodel, initialOntModelFilepath);
        
        /***********************/
        /* PREPROCESSING - End */
        /***********************/
        
        /*******************************/
    	/* INFER CONFIGURATION - Start */
        /*******************************/
        //run the rules
    	Model imodel = inferConfiguration(OntologyManager._dmodel, rules);
        
        //debug - write inferred model to .owl
        Utils.getInstance().writeOntologyModelToFile(imodel, inferredOntModelFilepath);
        
        /*****************************/
    	/* INFER CONFIGURATION - End */
        /*****************************/
        
        /***************************/
        /* POST PROCESSING - Start */
        /***************************/
        
        // create MM output

    	resJsonStr = TransformerManager.getInstance().transformOutput(imodel, queries);
        
        //debug - write final RBMM JSON output to file
        Utils.getInstance().writeFile(rbmmJsonOutputFilepath, Utils.getInstance().jsonPrettyPrint(resJsonStr));        
        
        /*************************/
        /* POST PROCESSING - End */
        /*************************/
        
        return resJsonStr;
    }
    
    public Model inferConfiguration(Model model, String[] rulePths)
    {
        for (String path : rulePths) {
        	
        	File f = new File(System.getProperty("user.dir")+path);        	
            if (f.exists()) 
            {
	            List<Rule> rules = Rule.rulesFromURL("file:"+f.getPath());
	            
	            //System.out.println(rules.toString());
	
	            GenericRuleReasoner r = new GenericRuleReasoner(rules);
	
	            InfModel infModel = ModelFactory.createInfModel(r, model);		    
	            
	            infModel.prepare();					
	
	            Model deducedModel = infModel.getDeductionsModel();  
	            
	            model.add(deducedModel);
	            
	          // deducedModel.write(System.out, "N-TRIPLE");
	          // model.write(System.out, "N-TRIPLE");
            }
            else System.out.println("Rule file does not exist: " + path);
        }
    	return model;    	
    }
}
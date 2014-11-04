package com.gpii.transformer;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Claudia Loitsch
 */
 
public class TransformerManager
{

	private static TransformerManager instance = null;
	
    /**
     * TODO make it configurable
     */
    static String defaultNameSpace = "http://rbmm.org/schemas/cloud4all/0.1/";
    
    private TransformerManager()  {}
    
    public static TransformerManager getInstance() 
    {
        if(instance == null) 
            
        	instance = new TransformerManager();
        
        
        return instance;
    }

    /**
     * Queries all requiered information from the rdf model and transforms the result the specific C4a JSON Structure. 
     * @param model
     * @param queries
     * @return
     * @throws JSONException
     */
	public byte[] transformOutput(Model model, String [] queries) throws JSONException {
				
				/**
				 * mmOut - JSON Object spec the matchmaker output
				 * 
				 */
				JSONObject mmOut = new JSONObject();
				
				/**
				 * infConfig - JSONObject spec the inferred configuration object of the matchmaker output
				 */
				JSONObject infConfig = new JSONObject();
				mmOut.put("inferredConfiguration", infConfig);
		    	
		        for ( String url : queries) {
		    		
		        	Query query = QueryFactory.read(url);

					QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
					
					try {
						ResultSet response = qexec.execSelect();

						JSONObject 	contextSet;
						JSONObject 	appSet;
						JSONObject 	solution = null;
						JSONObject 	settings;
						JSONArray 	conSet; 
						
						String contextID = null; 
						String queryType = null;
						
						while(response.hasNext()){
							
							QuerySolution soln = response.nextSolution();
							
							infConfig = mmOut.getJSONObject("inferredConfiguration");
							
							// get context id
							if(soln.contains("?contextID")){
								
								contextID 	= soln.get("?contextID").toString();
							}
							
							// get query type, e.g. Condition
							if(soln.contains("?type")){
								
								queryType 	= soln.get("?type").toString();
								
							}
							
							/**
							 * Context - create a new context object if not exitis
							 *  
							 */
							if(infConfig.has(contextID.toString())){
								
								contextSet = infConfig.getJSONObject(contextID.toString());
								
							}else {
								
								appSet = new JSONObject();
								infConfig.put(contextID.toString(), appSet);
								contextSet = infConfig.getJSONObject(contextID.toString());
								
							}
							/**
							 * Application - create a new application object if not exists
							 * 
							 */
							if(queryType.equals(defaultNameSpace+"Configuration")){
								
								if(contextSet.has("applications")){
									
									appSet = contextSet.getJSONObject("applications");			

								}else {
									
									appSet = new JSONObject();
									contextSet.put("applications", appSet);
									appSet = contextSet.getJSONObject("applications");
								}
								

								// add application name if not exists
								if(soln.contains("?appName")){
									
									String appName 	= soln.get("?appName").toString();
									
									if(appSet.has(appName)){										
										
										solution = appSet.getJSONObject(appName);
										
									}else{
										
										solution = new JSONObject();
										appSet.put(appName, solution);
										solution = appSet.getJSONObject(appName);
									}	
								}
								
								// add activation of application
								if(soln.contains("?appActive")){

									String appActive 	= soln.get("?appActive").toString();
									
									if(!solution.has("active")){
										
										solution.put("active", appActive);
									}
								}
								
								// add settings to separate settings block. 							
								if(soln.contains("?setID") && soln.contains("setValue") ){

									String setId 		= soln.get("?setID").toString();
									String setValue 	= soln.get("?setValue").toString();
									
									if(solution.has("settings")){
										
										settings = solution.getJSONObject("settings");
										settings.put(setId, setValue);
										
									}else {
										
										settings = new JSONObject(); 
										settings.put(setId, setValue);
										solution.put("settings", settings);
									}									
								}	
							}						

							/**
							 * Condition - create a new condition array if not exists
							 * 
							 */
							if(queryType.equals(defaultNameSpace+"Condition")){

								if(contextSet.has("conditions")){
									
									conSet = contextSet.getJSONArray("conditions");			

								}else {
									
									conSet = new JSONArray();
									contextSet.put("conditions", conSet);
									conSet = contextSet.getJSONArray("conditions");
								}
								
								// create a new condition object and put it to the condition array
								JSONObject condition = new JSONObject (); 
								/**
								 * TODO: condition payload does not use generic keys. min or max are specific to a specific type of condition. 
								 * This should be improved in general. 
								 *  
								 */
								condition.put("type", soln.get("?condOp").toString());
								condition.put("inputPath", soln.get("?condPa").toString());
								condition.put("min", soln.get("?condMi").toString());
								condition.put("max", soln.get("?condMa").toString());
								
								conSet.put(condition);								
			                }
						}
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally { qexec.close();}	
		        }
		        
			    byte dataToWrite[] = mmOut.toString().getBytes(StandardCharsets.US_ASCII);
			    return dataToWrite;	    		

	}
}
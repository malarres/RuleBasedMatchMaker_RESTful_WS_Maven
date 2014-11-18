package com.gpii.transformer;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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

    public String transformInput(String in) throws JSONException{
		
		String inputString = in;
		JSONTokener inputTokener = new JSONTokener(inputString);
		JSONObject mmIn = new JSONObject(inputTokener);		
		
		JSONObject 	outPreProc 	= new JSONObject();
		JSONObject 	outContext 	= new JSONObject();
		JSONArray 	outGraph 	= new JSONArray();

		if(mmIn.has("preferences")){
			JSONObject inContext  = mmIn.getJSONObject("preferences").getJSONObject("contexts");
   			
			/** Translate preferences sets 
			 * IN: 
			 * "gpii-default": {
			 * 		"name": "Default preferences",
			 * 		"preferences": {
			 *  		"http://registry.gpii.net/common/fontSize": 15,
			 *       }
			 * }
			 * GOAL: {
			 *  "@id": "c4a:nighttime-at-home",
			 *  "@type": "c4a:PreferenceSet",
			 *  "c4a:id": "nighttime-at-home",
			 *  "c4a:name": "Nighttimeathome",
			 *  "c4a:hasPrefs": [{
			 *  	"c4a:id": "http://registry.gpii.net/common/fontSize",
			 *  	"@type": "c4a:Preference",
			 *  	"c4a:type": "common",
			 *  	"c4a:name": "fontSize",
			 *  	"c4a:value": "18"
			 *  }] 
			 */
			Iterator<?> cKeys = inContext.keys(); 
	        while( cKeys.hasNext() ){
	        	String cID = (String)cKeys.next();
	        	String cName = inContext.getJSONObject(cID).get("name").toString();
	        	
	        	JSONObject outPrefSet = new JSONObject();
	        	outPrefSet.put("@id", "c4a:"+cID);
	        	outPrefSet.put("@type", "c4a:PreferenceSet");
	        	outPrefSet.put("c4a:id", cID);
	        	outPrefSet.put("c4a:name", cName);

	        	// translate preferences and add hasPrefs relation 
	        	JSONObject cPrefs = inContext.getJSONObject(cID).getJSONObject("preferences");
	        	
	        	JSONArray outPrefArray = new JSONArray(); 
    			Iterator<?> pKeys = cPrefs.keys(); 
    	        while( pKeys.hasNext() ){
    	        	String pID = (String)pKeys.next();
    	        	String pVal = cPrefs.getString(pID);
    	        	
    	        	JSONObject outPref = new JSONObject();
    	        	outPref.put("c4a:id", pID);
    	        	outPref.put("@type", "c4a:Preference");
    	        	
    	            if (pID.contains("common")) outPref.put("c4a:type", "common");
    	            if (pID.contains("applications")) outPref.put("c4a:type", "application");
    	            
    	            URI uri = null;
					try {
						
						uri = new URI(pID);
						
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	            String path = uri.getPath();
    	            String idStr = path.substring(path.lastIndexOf('/') + 1);
    	            outPref.put("c4a:name", idStr);

    	            outPref.put("c4a:value", pVal);
   	        	
    	        	outPrefArray.put(outPref);

    	        }
	        	outPrefSet.put("c4a:hasPrefs", outPrefArray);

	        	// translate metadata and add hasMetadata relation 
	        	if(inContext.getJSONObject(cID).has("metadata")){
	        		JSONArray cMetaOuter = inContext.getJSONObject(cID).getJSONArray("metadata");
		        	
		        	// output array
		        	JSONArray outMetaArray = new JSONArray();
		        	
		        	for(int i = 0; i < cMetaOuter.length(); i++){
		        		
		        		JSONObject cMeta = cMetaOuter.getJSONObject(i);	        		 
		        		
		        		// new JSONObject for each metadata blob
		        		JSONObject outMetaObject = new JSONObject();
		        		
		        		 outMetaObject.put("@type", "c4a:Metadata");
		        		 outMetaObject.put("c4a:type", cMeta.get("type").toString());
		        		 outMetaObject.put("c4a:value", cMeta.get("value").toString());
		        		 outMetaObject.put("c4a:scope", cMeta.getJSONArray("scope"));	        		 
		        		 
		        		 outMetaArray.put(outMetaObject); 
		        	}
		        	outPrefSet.put("c4a:hasMetadata", outMetaArray);	
	        	}
	        	
	        	// translate condition and add hasCondition relation 
	        	if(inContext.getJSONObject(cID).has("conditions")){
	        		
	        		JSONArray cCondOuter = inContext.getJSONObject(cID).getJSONArray("conditions");
		        	
		        	// output array
		        	JSONArray outCondArray = new JSONArray();
		        	
		        	for(int i = 0; i < cCondOuter.length(); i++){
		        		
		        		JSONObject cMeta = cCondOuter.getJSONObject(i);	        		 
		        		
		        		// new JSONObject for each metadata blob
		        		JSONObject outMetaObject = new JSONObject();
		        		
		        		outMetaObject.put("@type", "c4a:Condition");
		        		 
		     			Iterator<?> condKeys = cMeta.keys(); 
		    	        while(condKeys.hasNext()){
		    	        	
		    	        	String condKey = (String)condKeys.next();
		    	        	outMetaObject.put("c4a:"+condKey, cMeta.get(condKey).toString());
		    	        }		        		 
		        		outCondArray.put(outMetaObject); 
		        	}
		        	outPrefSet.put("c4a:hasCondition", outCondArray);	
	        	}
	        	
	        	outGraph.put(outPrefSet);        	        	
	        }			
			
		}
		
		if(mmIn.has("deviceReporter")){
			JSONObject inDevice  = mmIn.getJSONObject("deviceReporter");
			
			/** Translate operating system;
			 * IN:   
			 * "OS": {
			 *  "id": "win32",
			 *  "version": "5.0.0"
			 *  },
			 * GOAL: {
			 * "@id": "c4a:win32",
			 * "@type": "c4a:OperatingSystem",
			 * "c4a:name": "win32"
			 * },
			 */    			
			if(inDevice.has("OS")){
    			JSONObject inOS = inDevice.getJSONObject("OS");
    			String osID = inOS.get("id").toString();
    			String osVer = inOS.get("version").toString();        			

    			JSONObject outOS = new JSONObject(); 
    			outOS.put("@type", "c4a:OperatingSystem");
    			outOS.put("@id", "c4a:"+osID);
    			outOS.put("name", osID);
    			outOS.put("version", osVer);        			
    			
    			outGraph.put(outOS);        			
			}

			/** Translate installed solutions;
			 * IN:   
			 * solutions": [
			 * 	{ "id": com.cats.org }
			 * ]
			 *  GOAL: {
			 * "@id": "c4a:com.cats.org",
			 * "@type": "c4a:InstalledSolution",
			 * "c4a:name": "com.cats.org"
			 * },
			 */
			if(inDevice.has("solutions")){
    			JSONArray inSol = inDevice.getJSONArray("solutions");
    			for(int i = 0; i < inSol.length(); i++){
    				
    				String solID = inSol.getJSONObject(i).get("id").toString();

    				JSONObject outSol = new JSONObject(); 
    				outSol.put("@type", "c4a:InstalledSolution");
    				outSol.put("@id", "c4a:"+solID);
    				outSol.put("name", solID);        			
        			
        			outGraph.put(outSol);     
    			}   			
			}    				
		}
        
		outContext.put("c4a", "http://rbmm.org/schemas/cloud4all/0.1/");
		outContext.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");		
		
		outPreProc.put("@context", outContext);
		outPreProc.put("@graph", outGraph);
		
		/**
		 * make it configurable to spec the indent factor (number of spaces to add to each level of indentation).
		 */
		return outPreProc.toString(5);
    	
    }
    
    /**
     * Queries all requiered information from the rdf model and transforms the result the specific C4a JSON Structure. 
     * @param model
     * @param queries
     * @return
     * @throws JSONException
     */
	public String transformOutput(Model model, String [] queries) throws JSONException {
				
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
							System.out.println("out querry: " +soln.toString());
							
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
		        
				/**
				 * make it configurable to spec the indent factor (number of spaces to add to each level of indentation).
				 */
			    return mmOut.toString(5);	    		

	}
}
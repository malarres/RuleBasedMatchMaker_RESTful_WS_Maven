package com.gpii.ontology;


import com.github.jsonldjava.jena.JenaJSONLD;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 *
 * @author nkak
 * @author Claudia Loitsch
 * 
 */
public class OntologyManager
{
    private static OntologyManager instance = null;
    
    // default model automatically initialized with data from JSON-LD  	
    public static Model _dmodel;
    
    boolean printDebugInfo;
    public String debug;
    
    private OntologyManager() 
    {
        debug = "";
        printDebugInfo = false;
        
        // create an ontology model 
        _dmodel = ModelFactory.createOntologyModel(); 
        
        // JenaJSONLD must be initialized so that the readers and writers are registered with Jena.
        JenaJSONLD.init();
    }
    
    public static OntologyManager getInstance() 
    {
        if(instance == null) 
            instance = new OntologyManager();
        return instance;
    }
    
	public void populateJSONLDInput(String[] uris){
		
		//_dmodel = ModelFactory.createOntologyModel().read(uri, "JSON-LD");
		
		for (int i=0; i < uris.length; i++){		
			
			
			_dmodel.read(uris[i]);
			
		}		
		
	}
    
    public String testHello(String tmpName)
    {
        return "Hello " + tmpName + "!";
    }
    
}
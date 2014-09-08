package com.gpii.ontology;


import com.github.jsonldjava.jena.JenaJSONLD;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.BufferedReader;

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

    boolean printDebugInfo;
    public String debug;
    private static OntologyManager instance = null;
    
    private OntologyManager() 
    {
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
    
    public String testHello(String tmpName)
    {
        return "Hello " + tmpName + "!";
    }
    
}
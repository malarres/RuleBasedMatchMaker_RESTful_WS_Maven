package com.gpii.ontology;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

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
        
        // JenaJSONLD must be initialized so that the readers and writers are registered with Jena.
        JenaJSONLD.init();
    }
    
    public static OntologyManager getInstance() 
    {
        if(instance == null) 
            instance = new OntologyManager();
        return instance;
    }
    
    public void populateJSONLDInput(String transIn, String[] uris)
    {
    	InputStream is = new ByteArrayInputStream( transIn.getBytes() );
   	
        _dmodel = ModelFactory.createOntologyModel().read(is, null, "JSON-LD");

        for (int i=0; i < uris.length; i++)
            _dmodel.read(uris[i]);
    }
    
    public String testHello(String tmpName)
    {
        return "Hello " + tmpName + "!";
    }
    
}
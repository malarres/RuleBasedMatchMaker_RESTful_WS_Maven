package com.gpii.ontology;


import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.lang.PipedRDFIterator;

import com.github.jsonldjava.utils.*;
import com.github.jsonldjava.jena.*;
import com.github.jsonldjava.core.*;
import com.github.jsonldjava.impl.*;


import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.shared.DoesNotExistException;
import com.hp.hpl.jena.query.*;

/**
 *
 * @author nkak
 */
public class OntologyManager implements Serializable
{
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://gpii.org/schemas/accessibility#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>Datatype of a preference .</p> */
    public static final Property PrefDatatype = m_model.createProperty( "http://gpii.org/schemas/accessibility#PrefDatatype" );
    
    /** <p>Name of a preference .</p> */
    public static final Property PrefName = m_model.createProperty( "http://gpii.org/schemas/accessibility#PrefName" );
    
    /** <p>Value of a preference .</p> */
    public static final Property PrefValue = m_model.createProperty( "http://gpii.org/schemas/accessibility#PrefValue" );
    
    /** <p>Value range of a preference .</p> */
    public static final Property PrefValueRange = m_model.createProperty( "http://gpii.org/schemas/accessibility#PrefValueRange" );
    
    /** <p>A user has accessibility.</p> */
    public static final Property hasPrefs = m_model.createProperty( "http://gpii.org/schemas/accessibility#hasPrefs" );
    
    /** <p>Application specific accessibility preferences of a person .</p> */
    public static final Resource Appspecific = m_model.createResource( "http://gpii.org/schemas/accessibility#Appspecific" );
    
    /** <p>Common accessibility preferences of a person.</p> */
    public static final Resource Common = m_model.createResource( "http://gpii.org/schemas/accessibility#Common" );
    
    /** <p>Preferences of a person.</p> */
    public static final Resource Preference = m_model.createResource( "http://gpii.org/schemas/accessibility#Preference" );
    
    // effectively used; to be stored permanently
    
    /** <p>User requires specific accessibility settings.</p> */
    public static final Resource User = m_model.createResource( "http://gpii.org/schemas/accessibility#User" );
    
    public static final Property requiresAT = m_model.createProperty( "http://gpii.org/schemas/accessibility#requiresAT" );
    
    // to distinguish between preferred AT (explicitly though user voting) and used AT (implicitly through app-specific prefs)
    public static final Property prefersAT = m_model.createProperty( "http://gpii.org/schemas/accessibility#prefersAT" );
    
    public static final Resource Environment = m_model.createResource( "http://gpii.org/schemas/accessibility#Environment" );
    
    public static final Resource MultipleSolutionsConflict = m_model.createResource( "http://gpii.org/schemas/accessibility#MultipleSolutionsConflict" );
    
    // class to describe accessibility conflicts
    public static final Property accessibilityConflict = m_model.createProperty( "http://gpii.org/schemas/accessibility#accessibilityConflict" );
    
    // class to describe certain assistive technology classes  
    public static final Resource ATType = m_model.createResource( "http://gpii.org/schemas/accessibility#ATType" );
    
    public static final Property applyATType = m_model.createProperty( "http://gpii.org/schemas/accessibility#applyATType" );
    
    public static final Property applyATProduct = m_model.createProperty( "http://gpii.org/schemas/accessibility#applyATProduct" );
    
    
    // default model automatically initialized with data from JSON-LD  	
    public static Model m;

    //	accessibilityConflictModel
    public static Model acm;

    
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
    
    public static void runJSONLDTests(InputStream incomingData) throws IOException 
    {
        OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "Load preferences (JSONLD) and device characteristics (JSONLD)";

        // load accessibility namespace
        m.setNsPrefix("ax", NS);
        RDFDataMgr.read(m, incomingData, null, JenaJSONLD.JSONLD);

        OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "\nLoad other semantic data source (registry and solutions)";

        OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "\nRun JENA rules to infer knowledge used for conflict resolution";
                /** 
                    * TODO make this mapping more general to achieve the goal that we are not limited to GPII input sources
                    * This step is used for any kind of mappings from an abritary input source (here preferences from GPII) 
                    * to infer required knowledge for the RBMM reasoning and vocabulary. 
                    * 
                    */
        String mappingRules = System.getProperty("user.dir") + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/mappingRules.rules";

        File f = new File(mappingRules);
            if (f.exists()) {
                    List<Rule> rules = Rule.rulesFromURL("file:" + mappingRules);
                    GenericRuleReasoner r = new GenericRuleReasoner(rules);
                    InfModel infModel = ModelFactory.createInfModel(r, m);
                    // starting the rule execution
                    infModel.prepare();					
                    // write down the results in RDF/XML form
                    infModel.write(System.out);			

                // TODO why am I doing this here?  
                    m.add(infModel.getDeductionsModel());
            } else
                    OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "\nThat rules file does not exist.";

        OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "\nSPARQL Query: detect conflicts";
            /**
            * TODO 
            * Fix: ?y is not constructed in the RDF model.
            * SPARQL query not in source code 
            */

        String constructString = "CONSTRUCT";
            constructString += "{ ";
            constructString += " <http://gpii.org/schemas/accessibility#Environment> <http://gpii.org/schemas/accessibility#accessibilityConflict> <http://gpii.org/schemas/accessibility#MultipleSolutionsConflict> .";
            constructString += " <http://gpii.org/schemas/accessibility#MultipleSolutionsConflict> <http://gpii.org/schemas/accessibility#applyATType> ?x .";
            constructString += " <http://gpii.org/schemas/accessibility#MultipleSolutionsConflict> <http://gpii.org/schemas/accessibility#applyATProduct> ?y .";
            constructString += " } ";
            constructString += "WHERE { ";
            constructString += " SELECT ?x (COUNT(?y) AS ?count) ";
            constructString += "{ ";
            constructString += "<http://gpii.org/schemas/accessibility#User> <http://gpii.org/schemas/accessibility#requiresAT> ?x . ";
            constructString += "?y <http://registry.gpii.org/applications/type> ?x . ";	
            constructString += "} GROUP BY ?x";
            constructString += " HAVING (?count > 1)";
            constructString += " }";


        Query query = QueryFactory.create(constructString) ;
        QueryExecution qexec = QueryExecutionFactory.create(query, m) ;
        acm = qexec.execConstruct() ;
        StmtIterator si = acm.listStatements();
        Statement s = null;
        while (si.hasNext()) 
        {
            s = si.next();
            //PrevaylerManager.getInstance().debug = "\n" + PrevaylerManager.getInstance().debug + s.getPredicate().toString();
        }
        
        qexec.close();		

        //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nResolve confilcts => not implemented";
        /**
            * TODO implement conflict resolution
            */
        //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nstill not implemented";

        OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "\nOutput: Results as JSONLD object:\n";
        
        // list the statements in the Model
        StmtIterator iter = m.listStatements();

        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) 
        {
            OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "\n";
            Statement stmt      = iter.nextStatement();  // get next statement
            Resource  subject   = stmt.getSubject();     // get the subject
            Property  predicate = stmt.getPredicate();   // get the predicate
            RDFNode   object    = stmt.getObject();      // get the object

            OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + subject.toString();
            OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + " " + predicate.toString() + " ";
            if (object instanceof Resource) 
                OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + object.toString();
            else // object is a literal                
                OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + " \"" + object.toString() + "\"";
        } 
        
        /**
            * TODO implement output as JSONLD
            */
        //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nstill not implemented";

        // Test 1 - use RDFDataMrg 
        // RDFDataMgr.write(System.out, (Model) m, JenaJSONLD.JSONLD);
        // m.write(System.out, "JSON-LD");

        // TEST 2 - use JSONLdProcessor
        /*JsonLdOptions options = new JsonLdOptions(); 
        options.format = "application/ld+json";		    
        Object json;
            try {
                    json = JsonLdProcessor.fromRDF(m, options);
                String jsonStr = JSONUtils.toPrettyString(json);
                System.out.println(jsonStr);				
            } catch (JsonLdError e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }*/

                
        si = m.listStatements();
        s = null;
        while (si.hasNext()) 
        {
            s = si.next();
        }
    }
    
    public String testHello(String tmpName)
    {
        return "Hello " + tmpName + "!";
    }
    
}
package com.certh.iti.cloud4all.prevayler;

/**
 *
 * @author nkak
 */

import com.hp.hpl.jena.ontology.OntModel;
import com.certh.iti.cloud4all.ontology.OntologyManager;
import com.certh.iti.cloud4all.ontology.Solution;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import org.prevayler.Query;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;




public class PrevaylerManager 
{
    public boolean USE_PREVAYLER;
    public boolean SHOW_DEBUG_INFO;
    
    public Prevayler<Root> prevayler;
    public String debug;
    
    private static PrevaylerManager instance = null;
        
    private PrevaylerManager() throws Exception
    {
        USE_PREVAYLER = false;
        SHOW_DEBUG_INFO = true;
        
        // Create or load existing prevalence layer from journal and/or snapshot.
        if(USE_PREVAYLER)
            prevayler = PrevaylerFactory.createPrevayler(new Root(), "./lib/prevayler"/*"target/PrevalenceBase_" + System.currentTimeMillis()*/);
        debug = "";
    }
    
    public static PrevaylerManager getInstance() 
    {
        if(instance == null) 
        {
            try {
                instance = new PrevaylerManager();
            } catch (Exception ex) {
                Logger.getLogger(PrevaylerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    
    public void releasePrevayler()
    {
        try {
            prevayler.close();
        } catch (IOException ex) {
            Logger.getLogger(PrevaylerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isThereAnyOntModelStored()
    {
        boolean res = false;
        try
        {
            res = !prevayler.execute(new Query<Root, Boolean>() {
                public Boolean query(Root prevalentSystem, Date executionTime) throws Exception {
                  return prevalentSystem.getOntologyModels().isEmpty();
                }
              });
        }
        catch(Exception ex)
        {
            Logger.getLogger(PrevaylerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public ArrayList<String> getLastSolutionsArray()
    {
        OntologyModel queryResponse = null;
        try {
            queryResponse = prevayler.execute(new GetOntologyModel("myStoredOntModel"));
        } catch (Exception ex) {
            Logger.getLogger(PrevaylerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(queryResponse != null)
            return queryResponse.getSolutionsArray();
        return null;
    }
    
    public void updatePrevayler(ArrayList<Solution> tmpAllInstances_Solution)
    {
        ArrayList<String> tmpAllInstances_Solution_Str = new ArrayList<String>();
        for(int i=0; i<tmpAllInstances_Solution.size(); i++)
        {
            Solution tmpSolution = tmpAllInstances_Solution.get(i);
            tmpAllInstances_Solution_Str.add(tmpSolution.instanceName);
            tmpAllInstances_Solution_Str.add(tmpSolution.hasSolutionName);
            tmpAllInstances_Solution_Str.add(tmpSolution.id);
        }
        
        OntologyModel curOntologyModel = null;
        try {
            curOntologyModel = prevayler.execute(new CreateOntologyModelTransaction("myStoredOntModel"));
            //prevayler.takeSnapshot();
        } catch (Exception ex) {
            Logger.getLogger(PrevaylerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(curOntologyModel !=null)
            prevayler.execute(new UpdateOntModelTransaction(curOntologyModel.getIdentity(), tmpAllInstances_Solution_Str));
    }
    
    //https://github.com/jsampson/prevayler/blob/master/demos/tutorial/src/test/java/org/prevayler/examples/e101/E101Test.java
}

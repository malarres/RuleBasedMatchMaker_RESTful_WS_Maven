package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class Environment 
{
    int classID;
    
    String hasEnvironmentName;
    
    public Environment()
    {
        classID = -1;
        
        hasEnvironmentName = "";
    }
    
    public String toString()
    {
        String result = "Environment";//OntologyManager.getInstance().classNamesAndIDs.get(classID);
        
        result = result + "\n\thasEnvironmentName: " + hasEnvironmentName;
        
        return result;
    }
}

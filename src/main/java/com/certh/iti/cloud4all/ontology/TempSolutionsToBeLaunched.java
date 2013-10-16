package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class TempSolutionsToBeLaunched 
{
    String IDs;
    
    public TempSolutionsToBeLaunched()
    {
        IDs = "";
    }
    
    @Override
    public String toString()
    {
        String result = "";
        
        result = result + "\n\t\t" + IDs;
       
        return result;
    }
}

package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class TempPossibleSolution 
{
    String instanceName;
    String text;
    String comment;
    
    public TempPossibleSolution()
    {
        instanceName = "";
        text = "";
        comment = "";
    }
    
    @Override
    public String toString()
    {
        String result = "\tPossible Solution:";
        
        result = result + "\n\t\t" + instanceName;
        result = result + "\n\t\t" + text;
        result = result + "\n\t\tComment:" + comment;
       
        return result;
    }
}

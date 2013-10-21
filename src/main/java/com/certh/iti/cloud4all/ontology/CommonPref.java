package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class CommonPref 
{
    public String commonTermID;
    public String value;
    
    public CommonPref()
    {
        commonTermID = "";
        value = "";
    }
    
    @Override
    public String toString()
    {
        String res = "";
        res = "commonTermID: " + commonTermID + ", value: " + value;
        return res;
    }
}

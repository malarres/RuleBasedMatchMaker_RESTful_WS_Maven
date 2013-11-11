package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class CommonTerm 
{
    public String instanceName;
    public String ID;
    public String name;
    public String description;
    public String type;
    public String defaulValue;
    public String valueSpace;
    public String notes;
    
    public CommonTerm()
    {
        instanceName = "";
        ID = "";
        name = "";
        description = "";
        type = "";
        defaulValue = "";
        valueSpace = "";
        notes = "";
    }
    
    @Override
    public String toString()
    {
        String res = "";
        res = "CommonTerm -> instanceName: " + instanceName + 
                ", ID: " + ID +
                ", name: " + name + 
                ", description: " + description + 
                ", type: " + type + 
                ", defaulValue: " + defaulValue + 
                ", valueSpace: " + valueSpace + 
                ", notes: " + notes;
        return res;
    }
}

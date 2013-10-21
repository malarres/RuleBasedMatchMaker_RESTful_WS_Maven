package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class AppSpecificSettingRelatedToCommonTerm 
{
    public static final int STRING = 0;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int BOOLEAN = 3;
    
    public String appSpecificSettingID;
    public int appSpecificSettingType;
    public String appSpecificSettingValue;
    public String mappedCommonTermID;
    public String mappedCommonTermTypeStr;
    public int mappedCommonTermType;
    
    public AppSpecificSettingRelatedToCommonTerm()
    {
        appSpecificSettingID = "";
        appSpecificSettingType = -1;
        appSpecificSettingValue = "";
        mappedCommonTermID = "";
        mappedCommonTermTypeStr = "";
        mappedCommonTermType = -1;
    }
    
    public void findType()
    {
        if(appSpecificSettingValue.endsWith("^^http://www.w3.org/2001/XMLSchema#string"))
            appSpecificSettingType = STRING;
        else if(appSpecificSettingValue.endsWith("^^http://www.w3.org/2001/XMLSchema#int"))
            appSpecificSettingType = INTEGER;
        else if(appSpecificSettingValue.endsWith("^^http://www.w3.org/2001/XMLSchema#float"))
            appSpecificSettingType = FLOAT;
        else if(appSpecificSettingValue.endsWith("^^http://www.w3.org/2001/XMLSchema#boolean"))
            appSpecificSettingType = BOOLEAN;
        
        if(mappedCommonTermTypeStr.toLowerCase().startsWith("string"))
            mappedCommonTermType = STRING;
        else if(mappedCommonTermTypeStr.toLowerCase().startsWith("integer"))
            mappedCommonTermType = INTEGER;
        else if(mappedCommonTermTypeStr.toLowerCase().startsWith("float"))
            mappedCommonTermType = FLOAT;
        else if(mappedCommonTermTypeStr.toLowerCase().startsWith("boolean"))
            mappedCommonTermType = BOOLEAN;
    }
    
    public void removeRDFPrefix()
    {
        appSpecificSettingID = appSpecificSettingID.substring(0, appSpecificSettingID.indexOf("^^http://"));
        appSpecificSettingValue = appSpecificSettingValue.substring(0, appSpecificSettingValue.indexOf("^^http://"));
        mappedCommonTermID = mappedCommonTermID.substring(0, mappedCommonTermID.indexOf("^^http://"));
        mappedCommonTermTypeStr = "";
    }
    
    public boolean alignmentHasCompatibleTypes()
    {
        if(appSpecificSettingType == mappedCommonTermType)
            return true;
        return false;
    }
    
    @Override
    public String toString()
    {
        String res = "";
        String typeStr = "";
        if(appSpecificSettingType == STRING)
            typeStr = "string";
        else if(appSpecificSettingType == INTEGER)
            typeStr = "int";
        else if(appSpecificSettingType == FLOAT)
            typeStr = "float";
        else if(appSpecificSettingType == BOOLEAN)
            typeStr = "boolean";
        String commonTermTypeStr = "";
        if(mappedCommonTermType == STRING)
            commonTermTypeStr = "string";
        else if(mappedCommonTermType == INTEGER)
            commonTermTypeStr = "int";
        else if(mappedCommonTermType == FLOAT)
            commonTermTypeStr = "float";
        else if(mappedCommonTermType == BOOLEAN)
            commonTermTypeStr = "boolean";
        res = "\n    [ID:" + appSpecificSettingID + " TYPE:" + typeStr + " DEFAULT_VALUE:" + appSpecificSettingValue + " mappedTo " + mappedCommonTermID + " TYPE:" + commonTermTypeStr + "]";
        return res;
    }
}

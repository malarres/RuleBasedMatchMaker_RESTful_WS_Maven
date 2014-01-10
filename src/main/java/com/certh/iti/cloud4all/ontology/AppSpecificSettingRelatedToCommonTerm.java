package com.certh.iti.cloud4all.ontology;

import com.certh.iti.cloud4all.prevayler.PrevaylerManager;

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
    public String appSpecificSettingValueSpace;
    public String mappedCommonTermID;
    public String mappedCommonTermTypeStr;
    public int mappedCommonTermType;
    
    public AppSpecificSettingRelatedToCommonTerm()
    {
        appSpecificSettingID = "";
        appSpecificSettingType = -1;
        appSpecificSettingValue = "";
        appSpecificSettingValueSpace = "";
        mappedCommonTermID = "";
        mappedCommonTermTypeStr = "";
        mappedCommonTermType = -1;
    }
    
    public void findType()
    {
        if(appSpecificSettingValue.trim().toLowerCase().endsWith("^^http://www.w3.org/2001/xmlschema#string"))
            appSpecificSettingType = STRING;
        else if(appSpecificSettingValue.trim().toLowerCase().endsWith("^^http://www.w3.org/2001/xmlschema#int"))
            appSpecificSettingType = INTEGER;
        else if(appSpecificSettingValue.trim().toLowerCase().endsWith("^^http://www.w3.org/2001/xmlschema#float"))
            appSpecificSettingType = FLOAT;
        else if(appSpecificSettingValue.trim().toLowerCase().endsWith("^^http://www.w3.org/2001/xmlschema#boolean"))
            appSpecificSettingType = BOOLEAN;
        
        if(mappedCommonTermTypeStr.trim().toLowerCase().startsWith("string"))
            mappedCommonTermType = STRING;
        else if(mappedCommonTermTypeStr.trim().toLowerCase().startsWith("integer"))
            mappedCommonTermType = INTEGER;
        else if(mappedCommonTermTypeStr.trim().toLowerCase().startsWith("float"))
            mappedCommonTermType = FLOAT;
        else if(mappedCommonTermTypeStr.trim().toLowerCase().startsWith("boolean"))
            mappedCommonTermType = BOOLEAN;
    }
    
    public void removeRDFPrefix()
    {
        if(appSpecificSettingID.indexOf("^^http://") != -1)
            appSpecificSettingID = appSpecificSettingID.substring(0, appSpecificSettingID.indexOf("^^http://"));
        else
            
        if(appSpecificSettingValue.indexOf("^^http://") != -1)
            appSpecificSettingValue = appSpecificSettingValue.substring(0, appSpecificSettingValue.indexOf("^^http://"));
        if(appSpecificSettingValueSpace.indexOf("^^http://") != -1)
            appSpecificSettingValueSpace = appSpecificSettingValueSpace.substring(0, appSpecificSettingValueSpace.indexOf("^^http://"));
        if(mappedCommonTermID.indexOf("^^http://") != -1)
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
        
        
        if(mappedCommonTermID.equals("display.screenReader.-provisional-speakTutorialMessages")
            || mappedCommonTermID.equals("display.screenReader.-provisional-keyEcho")
            || mappedCommonTermID.equals("display.screenReader.-provisional-wordEcho")
            || mappedCommonTermID.equals("display.screenReader.-provisional-announceCapitals")
            || mappedCommonTermID.equals("display.screenReader.-provisional-screenReaderBrailleOutput")
            || mappedCommonTermID.equals("display.screenReader.-provisional-screenReaderTTSEnabled") )
            res = "\n    [INTERESTING :) -> ID:" + appSpecificSettingID + " TYPE:" + typeStr + " DEFAULT_VALUE:" + appSpecificSettingValue + " valueSpace:" + appSpecificSettingValueSpace + " mappedTo " + mappedCommonTermID + " TYPE:" + commonTermTypeStr + "]";
        else
            res = "\n    [ID:" + appSpecificSettingID + " TYPE:" + typeStr + " DEFAULT_VALUE:" + appSpecificSettingValue + " valueSpace:" + appSpecificSettingValueSpace + " mappedTo " + mappedCommonTermID + " TYPE:" + commonTermTypeStr + "]";
        return res;
    }
}

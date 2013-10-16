package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class Platform 
{
    int classID;

    String hasPlatformName;
    String hasPlatformDescription;
    String platformType;
    String platformSubtype;
    String hasPlatformSpecificSetting;
    String hasPlatformVendor;
    String platformSupports;
    String hasPlatformVersion;

    public Platform()
    {
        classID = -1;

        hasPlatformName = "";
        hasPlatformDescription = "";
        platformType = "";
        platformSubtype = "";
        hasPlatformSpecificSetting = "";
        hasPlatformVendor = "";
        platformSupports = "";
        hasPlatformVersion = "";
    }

    public String toString()
    {
        String result = "Platform:";//OntologyManager.getInstance().classNamesAndIDs.get(classID);

        result = result + "\n\thasPlatformName: " + hasPlatformName;
        result = result + "\n\thasPlatformDescription: " + hasPlatformDescription;
        result = result + "\n\tplatformType: " + platformType;
        result = result + "\n\tplatformSubtype: " + platformSubtype;
        result = result + "\n\thasPlatformSpecificSetting: " + hasPlatformSpecificSetting;
        result = result + "\n\thasPlatformVendor: " + hasPlatformVendor;
        result = result + "\n\tplatformSupports: " + platformSupports;
        result = result + "\n\thasPlatformVersion: " + hasPlatformVersion;

        return result;
    }
}
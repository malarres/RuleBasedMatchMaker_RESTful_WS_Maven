package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class PlatformVendor 
{
    int classID;

    String hasPlatformVendorName;
    String isPlatformVendorOf;

    public PlatformVendor()
    {
        classID = -1;

        hasPlatformVendorName = "";
        isPlatformVendorOf = "";
    }

    public String toString()
    {
        String result = "PlatformVendor:";//OntologyManager.getInstance().classNamesAndIDs.get(classID);

        result = result + "\n\thasPlatformVendorName: " + hasPlatformVendorName;
        result = result + "\n\tisPlatformVendorOf: " + isPlatformVendorOf;

        return result;
    }
}

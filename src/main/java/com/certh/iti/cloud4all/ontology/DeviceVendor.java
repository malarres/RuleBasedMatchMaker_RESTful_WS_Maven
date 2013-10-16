package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class DeviceVendor 
{
    int classID;

    String hasDeviceVendorName;
    String isDeviceVendorOf;

    public DeviceVendor()
    {
        classID = -1;

        hasDeviceVendorName = "";
        isDeviceVendorOf = "";
    }

    public String toString()
    {
        String result = "DeviceVebndor";//OntologyManager.getInstance().classNamesAndIDs.get(classID);

        result = result + "\n\thasDeviceVendorName: " + hasDeviceVendorName;
        result = result + "\n\tisDeviceVendorOf: " + isDeviceVendorOf;

        return result;
    }
}

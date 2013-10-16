package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class Device 
{
    int classID;

    String hasDeviceName;
    String hasDeviceDescription;
    String hasDeviceSpecificSetting;
    String hasDeviceVendor;
    String isSupportingDeviceOf;

    public Device()
    {
        classID = -1;

        hasDeviceName = "";
        hasDeviceDescription = "";
        hasDeviceSpecificSetting = "";
        hasDeviceVendor = "";
        isSupportingDeviceOf = "";
    }

    public String toString()
    {
        String result = "Device:";//OntologyManager.getInstance().classNamesAndIDs.get(classID);

        result = result + "\n\thasDeviceName: " + hasDeviceName;
        result = result + "\n\thasDeviceDescription: " + hasDeviceDescription;
        result = result + "\n\thasDeviceSpecificSetting: " + hasDeviceSpecificSetting;
        result = result + "\n\thasDeviceVendor: " + hasDeviceVendor;
        result = result + "\n\tisSupportingDeviceOf: " + isSupportingDeviceOf;

        return result;
    }
}

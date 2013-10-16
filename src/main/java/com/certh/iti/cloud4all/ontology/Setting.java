package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class Setting 
{
    int classID;

    String hasSettingName;
    String hasSettingDescription;
    String allowCascading;
    String allowedSolutionsForCascading;
    String freeAllowedDaysOfInvocation;
    String hasCostForCascading;
    String adaptingDevice;
    String adaptingPlatform;
    String adaptingSolution;
    String hasCostCurrencyForCascading;
    String hasCostForCascadingPaymentCharge;
    String hasSettingDefaultValue;
    String hasSettingRange;
    String hasSettingValue;

    public Setting()
    {
        classID = -1;

        hasSettingName = "";
        hasSettingDescription = "";
        allowCascading = "";
        allowedSolutionsForCascading = "";
        freeAllowedDaysOfInvocation = "";
        hasCostForCascading = "";
        adaptingDevice = "";
        adaptingPlatform = "";
        adaptingSolution = "";
        hasCostCurrencyForCascading = "";
        hasCostForCascadingPaymentCharge = "";
        hasSettingDefaultValue = "";
        hasSettingRange = "";
        hasSettingValue = "";
    }

    public String toString()
    {
        String result = "Setting:";//OntologyManager.getInstance().classNamesAndIDs.get(classID);

        result = result + "\n\thasSettingName: " + hasSettingName;
        result = result + "\n\thasSettingDescription: " + hasSettingDescription;
        result = result + "\n\tallowCascading: " + allowCascading;
        result = result + "\n\tallowedSolutionsForCascading: " + allowedSolutionsForCascading;
        result = result + "\n\tfreeAllowedDaysOfInvocation: " + freeAllowedDaysOfInvocation;
        result = result + "\n\thasCostForCascading: " + hasCostForCascading;
        result = result + "\n\tadaptingDevice: " + adaptingDevice;
        result = result + "\n\tadaptingPlatform: " + adaptingPlatform;
        result = result + "\n\tadaptingSolution: " + adaptingSolution;
        result = result + "\n\thasCostCurrencyForCascading: " + hasCostCurrencyForCascading;
        result = result + "\n\thasCostForCascadingPaymentCharge: " + hasCostForCascadingPaymentCharge;
        result = result + "\n\thasSettingDefaultValue: " + hasSettingDefaultValue;
        result = result + "\n\thasSettingRange: " + hasSettingRange;
        result = result + "\n\thasSettingValue: " + hasSettingValue;

        return result;
    }
}

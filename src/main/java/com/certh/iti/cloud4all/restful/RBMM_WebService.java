package com.certh.iti.cloud4all.restful;

import com.certh.iti.cloud4all.feedback.FeedbackManager;
import com.certh.iti.cloud4all.inference.RulesManager;
import com.certh.iti.cloud4all.instantiation.InstantiationManager;
import com.certh.iti.cloud4all.ontology.CommonPref;
import com.certh.iti.cloud4all.ontology.OntologyManager;
import com.certh.iti.cloud4all.prevayler.PrevaylerManager;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import sun.nio.cs.StandardCharsets;



@Path("/RBMM")
public class RBMM_WebService
{
    private static String str_piece(String str, char separator, int index) {
        String str_result = "";
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == separator) {
                count++;
                if(count == index) {
                    break;
                }
            }
            else {
                if(count == index-1) {
                    str_result += str.charAt(i);
                }
            }
        }
        return str_result;
    }
    
    private void parseUserProfile(String tmpUser)
    {
        String fontSize = str_piece(tmpUser, '|', 1);
        String magnification = str_piece(tmpUser, '|', 2);
        String foregroundColor = str_piece(tmpUser, '|', 3);
        String backgroundColor = str_piece(tmpUser, '|', 4);
        String highContrast = str_piece(tmpUser, '|', 5);
        String magnifierFullScreen = str_piece(tmpUser, '|', 6);
        String specificPreferencesForSolutions_IDs = str_piece(tmpUser, '|', 7);
        String commonTermsAndValues_oneString = str_piece(tmpUser, '|', 8);
        
        OntologyManager.getInstance(); //initialization
        
        InstantiationManager.getInstance().USER_fontSize = Integer.parseInt(fontSize);
        InstantiationManager.getInstance().USER_magnification = Double.parseDouble(magnification);
        InstantiationManager.getInstance().USER_foregroundColor = foregroundColor;
        InstantiationManager.getInstance().USER_backgroundColor = backgroundColor;
        InstantiationManager.getInstance().USER_highContrast = Boolean.parseBoolean(highContrast);
        InstantiationManager.getInstance().USER_magnifierFullScreen = Boolean.parseBoolean(magnifierFullScreen);
        InstantiationManager.getInstance().USER_SpecificPreferencesForSolutions_IDs = specificPreferencesForSolutions_IDs;
        
        PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nInstantiationManager.getInstance().USER_SpecificPreferencesForSolutions_IDs: " + InstantiationManager.getInstance().USER_SpecificPreferencesForSolutions_IDs;
        
        String[] commonTermsAndValues = commonTermsAndValues_oneString.split("\\*");
        for(int i=0; i<commonTermsAndValues.length; i=i+2)
        {
            if((i+1) ==  commonTermsAndValues.length)
                break;
            CommonPref tmpCommonPref = new CommonPref();
            tmpCommonPref.commonTermID = commonTermsAndValues[i];
            tmpCommonPref.value = commonTermsAndValues[i+1];
            InstantiationManager.getInstance().USER_CommonTermsIDs.add(tmpCommonPref);
            PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\n[Common Term -> " + tmpCommonPref.toString() + "]";
        }
    }
    
    private void parseEnvironment(String tmpEnvironment)
    {
        String os_id = str_piece(tmpEnvironment, '|', 1);
        String os_version = str_piece(tmpEnvironment, '|', 2);
        String installed_solutions_ids = str_piece(tmpEnvironment, '|', 3);
        String available_solutions_ids = str_piece(tmpEnvironment, '|', 4);
    
        InstantiationManager.getInstance().DEVICE_REPORTER_OS_id = os_id;
        if(os_version.startsWith("6.1"))
            os_version = "6.1";
        InstantiationManager.getInstance().DEVICE_REPORTER_OS_version = os_version;
        InstantiationManager.getInstance().DEVICE_REPORTER_INSTALLEDSOLUTIONS_IDs = installed_solutions_ids;
        InstantiationManager.getInstance().DEVICE_REPORTER_AVAILABLESOLUTIONS_IDs = InstantiationManager.getInstance().DEVICE_REPORTER_INSTALLEDSOLUTIONS_IDs; //available_solutions_ids;
    
        PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\n\nDEVICE_REPORTER_OS_id: " + InstantiationManager.getInstance().DEVICE_REPORTER_OS_id + ", DEVICE_REPORTER_OS_version: " + InstantiationManager.getInstance().DEVICE_REPORTER_OS_version + "\n\n";
    }
    
    @GET
    @Path("/runRules/{tmpUser}/{tmpEnvironment}")
    public Response getAllUsers(@PathParam("tmpUser") String tempUser, @PathParam("tmpEnvironment") String tempEnvironment)
    {
        PrevaylerManager.getInstance().debug = "";
        FeedbackManager.getInstance().init();
        
        parseUserProfile(tempUser);
        parseEnvironment(tempEnvironment);
        
        InstantiationManager.getInstance().createInstanceInOntologyForJSONUserPreferencesSet();
        InstantiationManager.getInstance().createInstanceInOntologyForJSONEnvironment();

        String finalUserPrefs = RulesManager.getInstance().executeMyCloudRulesForFindingHandicapSituations(false);
        
        if(FeedbackManager.getInstance().writeFeedbackToFile)
        {
            FeedbackManager.getInstance().writeHTML();
            finalUserPrefs = finalUserPrefs + " " + FeedbackManager.getInstance().englishURL + " " + FeedbackManager.getInstance().germanURL + " " + FeedbackManager.getInstance().greekURL + " " + FeedbackManager.getInstance().spanishURL;
        }
        
        if(PrevaylerManager.getInstance().SHOW_DEBUG_INFO)
            finalUserPrefs = finalUserPrefs + " - DEBUG: " + PrevaylerManager.getInstance().debug;
        
        return Response.status(200).entity(finalUserPrefs).build();
    }
    
    
    //http://localhost:8080/CLOUD4All_RBMM_Restful_WS/RBMM/runJSONLDRules
    //
    @POST
    @Path("/runJSONLDRules")
    @Consumes("application/json")
    public Response runJSONLDRules(InputStream incomingData)
    {
        PrevaylerManager.getInstance().debug = "";
        
        //Json-LD test
        try {
            OntologyManager.getInstance().runJSONLDTests(incomingData);
        } catch (IOException ex) {
            PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "runJSONLDTests EXCEPTION! -> " + ex.toString();
        }
        
        return Response.status(200).entity(PrevaylerManager.getInstance().debug).build();
    }
}

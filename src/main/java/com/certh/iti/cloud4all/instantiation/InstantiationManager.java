package com.certh.iti.cloud4all.instantiation;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.certh.iti.cloud4all.ontology.OntologyManager;
import java.util.ArrayList;

/**
 *
 * @author nkak
 */
public class InstantiationManager 
{
    //Node.js
    public Integer USER_fontSize;
    public Double USER_magnification;
    public String USER_foregroundColor;
    public String USER_backgroundColor;
    public boolean USER_highContrast;
    public boolean USER_magnifierFullScreen;
    public String USER_SpecificPreferencesForSolutions_IDs;
    
    public String DEVICE_REPORTER_OS_id;
    public String DEVICE_REPORTER_OS_version;
    public String DEVICE_REPORTER_INSTALLEDSOLUTIONS_IDs;
    public String DEVICE_REPORTER_AVAILABLESOLUTIONS_IDs;
    //-Node.js
    
    public static final String SOURCE = "http://www.cloud4all.eu/SemanticFrameworkForContentAndSolutions.owl";
    public static final String NS = SOURCE + "#";
    
    public String sammyFilepath;
    public String environmentProfileFilepath;
    
    //temporary statements
    Statement fontSizeStat;
    Statement magnificationStat;
    Statement foregroundColorStat;
    Statement backgroundColorStat;
    Statement highContrastStat;
    Statement magnifierFullScreenStat;
    ArrayList<Statement> preferredSolutionStats;
        
    Statement platformNameStat;
    Statement platformVersionStat;
    ArrayList<Statement> installedSolutionsIDsStats;
    ArrayList<Statement> availableSolutionsIDsStats;
    Statement windowManagerIdStat;
    Statement windowManagerVersionStat;
    //-temporary statements
    
    private static InstantiationManager instance = null;
    
    private InstantiationManager() 
    {
        USER_fontSize = -1;
        USER_magnification = -1.0;
        USER_foregroundColor = "";
        USER_backgroundColor = "";
        USER_highContrast = false;
        USER_magnifierFullScreen = false;
        USER_SpecificPreferencesForSolutions_IDs = "";

        DEVICE_REPORTER_OS_id = "";
        DEVICE_REPORTER_OS_version = "";
        DEVICE_REPORTER_INSTALLEDSOLUTIONS_IDs = "";
        DEVICE_REPORTER_AVAILABLESOLUTIONS_IDs = "";
        
        preferredSolutionStats = new ArrayList<Statement>();
        installedSolutionsIDsStats = new ArrayList<Statement>();
        availableSolutionsIDsStats = new ArrayList<Statement>();
    }
    
    public static InstantiationManager getInstance() 
    {
        if(instance == null) 
            instance = new InstantiationManager();
        return instance;
    }
    
    public void removeTempInstances()
    {
        OntologyManager.getInstance().model.remove(fontSizeStat);
        OntologyManager.getInstance().model.remove(magnificationStat);
        OntologyManager.getInstance().model.remove(foregroundColorStat);
        OntologyManager.getInstance().model.remove(backgroundColorStat);
        OntologyManager.getInstance().model.remove(highContrastStat);
        OntologyManager.getInstance().model.remove(magnifierFullScreenStat);
        for(int i=0; i<preferredSolutionStats.size(); i++)
        {
            Statement tmpStatement = preferredSolutionStats.get(i);
            OntologyManager.getInstance().model.remove(tmpStatement);
        }

        OntologyManager.getInstance().model.remove(platformNameStat);
        OntologyManager.getInstance().model.remove(platformVersionStat);
        for(int i=0; i<installedSolutionsIDsStats.size(); i++)
        {
            Statement tmpStatement = installedSolutionsIDsStats.get(i);
            OntologyManager.getInstance().model.remove(tmpStatement);
        }
        for(int i=0; i<availableSolutionsIDsStats.size(); i++)
        {
            Statement tmpStatement = availableSolutionsIDsStats.get(i);
            OntologyManager.getInstance().model.remove(tmpStatement);
        }
        OntologyManager.getInstance().model.remove(windowManagerIdStat);
        OntologyManager.getInstance().model.remove(windowManagerVersionStat);        
        
        preferredSolutionStats.clear();
        installedSolutionsIDsStats.clear();
        availableSolutionsIDsStats.clear();
    }
    
    public String createInstanceInOntologyForJSONUserPreferencesSet()
    {
        String res = "createInstanceInOntologyForJSONUserPreferencesSet() -> ok";
        
        try
        {
            //add to ontology
            Individual tmpUserOntInstance = OntologyManager.getInstance().model.getOntClass(NS + "TempUsers").createIndividual(NS + "TEST_SAMMY");
            
            DatatypeProperty fontSizeProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempUsers_fontSize");
            Literal fontSizeProp_value;
            fontSizeProp_value = OntologyManager.getInstance().model.createTypedLiteral(USER_fontSize, XSDDatatype.XSDint);
            fontSizeStat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, fontSizeProp, fontSizeProp_value);
            OntologyManager.getInstance().model.add(fontSizeStat);
            
            DatatypeProperty magnificationProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempUsers_magnification");
            Literal magnificationProp_value;
            magnificationProp_value = OntologyManager.getInstance().model.createTypedLiteral(USER_magnification, XSDDatatype.XSDfloat);
            magnificationStat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, magnificationProp, magnificationProp_value);
            OntologyManager.getInstance().model.add(magnificationStat);
            
            DatatypeProperty foregroundColorProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempUsers_foregroundColor");
            Literal foregroundColorProp_value;
            foregroundColorProp_value = OntologyManager.getInstance().model.createTypedLiteral(USER_foregroundColor, XSDDatatype.XSDstring);
            foregroundColorStat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, foregroundColorProp, foregroundColorProp_value);
            OntologyManager.getInstance().model.add(foregroundColorStat);
            
            DatatypeProperty backgroundColorProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempUsers_backgroundColor");
            Literal backgroundColorProp_value;
            backgroundColorProp_value = OntologyManager.getInstance().model.createTypedLiteral(USER_backgroundColor, XSDDatatype.XSDstring);
            backgroundColorStat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, backgroundColorProp, backgroundColorProp_value);
            OntologyManager.getInstance().model.add(backgroundColorStat);
            
            DatatypeProperty highContrastProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempUsers_highContrast");
            Literal highContrastProp_value;
            highContrastProp_value = OntologyManager.getInstance().model.createTypedLiteral(USER_highContrast, XSDDatatype.XSDboolean);
            highContrastStat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, highContrastProp, highContrastProp_value);
            OntologyManager.getInstance().model.add(highContrastStat);
            
            DatatypeProperty magnifierFullScreenProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempUsers_magnifierFullScreen");
            Literal magnifierFullScreenProp_value;
            magnifierFullScreenProp_value = OntologyManager.getInstance().model.createTypedLiteral(USER_magnifierFullScreen, XSDDatatype.XSDboolean);
            magnifierFullScreenStat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, magnifierFullScreenProp, magnifierFullScreenProp_value);
            OntologyManager.getInstance().model.add(magnifierFullScreenStat);
            
            ObjectProperty specificPreferencesForSolutionsProp = OntologyManager.getInstance().model.getObjectProperty(NS + "TempUsers_hasSpecificPreferencesForSolutions");
            String[] tmpSpecificPreferencesForSolutions_Str = USER_SpecificPreferencesForSolutions_IDs.split(" ");
            for(int tmpCnt=0; tmpCnt<tmpSpecificPreferencesForSolutions_Str.length; tmpCnt++)
            {
                String tmpInstanceName = OntologyManager.getInstance().getInstanceNameBySolutionID(tmpSpecificPreferencesForSolutions_Str[tmpCnt]);
                if(tmpInstanceName.equals("not found!") == false)
                {
                    Resource preferredSolutionResource = OntologyManager.getInstance().model.getResource(tmpInstanceName);
                    Statement preferredSolutionStat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, specificPreferencesForSolutionsProp, preferredSolutionResource);
                    OntologyManager.getInstance().model.add(preferredSolutionStat);
                    preferredSolutionStats.add(preferredSolutionStat);
                }
            }
            
            /*DatatypeProperty screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Prop = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempUsers_ScreenReaderTTSEnabled_whenLaunchAtStartupIsTrue");
            
            if( ((VariableStringValue)tmpUser.screenReaderTTSEnabled.get(0)).condition.operator.property.equals("http://gpii.org/ns/LaunchAtStartup")
                    && ((VariableStringValue)tmpUser.screenReaderTTSEnabled.get(0)).condition.operator.value.toLowerCase().equals("true") )
            {
                Literal screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Prop_value = null;
                if( ((VariableStringValue)tmpUser.screenReaderTTSEnabled.get(0)).value.toLowerCase().equals("true") )
                    screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Prop_value = OntologyManager.getInstance().model.createTypedLiteral(true, XSDDatatype.XSDboolean);
                else
                    screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Prop_value = OntologyManager.getInstance().model.createTypedLiteral(false, XSDDatatype.XSDboolean);
                Statement screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Stat = OntologyManager.getInstance().model.createStatement(tmpUserOntInstance, screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Prop, screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Prop_value);
                OntologyManager.getInstance().model.add(screenReaderTTSEnabled_whenLaunchAtStartupIsTrue_Stat);
            }*/
        }
        catch(Exception e)
        {
            System.out.println("\ncreateInstanceInOntologyForJSONUserPreferencesSet-> EXCEPTION CAUGHT!\n");
            e.printStackTrace();
            return "exception!!!!!";
        }
        return res;
    }
    
    public String createInstanceInOntologyForJSONEnvironment()
    {
        String res = "createInstanceInOntologyForJSONEnvironment() -> ok";
        try
        {
            //add to ontology
            Individual tmpEnvironmentOntInstance = OntologyManager.getInstance().model.getOntClass(NS + "TempEnvironment").createIndividual(NS + "TEST_ENVIRONMENT");
            
            DatatypeProperty platformNameProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempEnvironment_platformName");
            Literal platformNameProp_value = OntologyManager.getInstance().model.createTypedLiteral(DEVICE_REPORTER_OS_id, XSDDatatype.XSDstring);
            platformNameStat = OntologyManager.getInstance().model.createStatement(tmpEnvironmentOntInstance, platformNameProp, platformNameProp_value);
            OntologyManager.getInstance().model.add(platformNameStat);
            
            DatatypeProperty platformVersionProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempEnvironment_platformVersion");
            Literal platformVersionProp_value = OntologyManager.getInstance().model.createTypedLiteral(DEVICE_REPORTER_OS_version, XSDDatatype.XSDstring);
            platformVersionStat = OntologyManager.getInstance().model.createStatement(tmpEnvironmentOntInstance, platformVersionProp, platformVersionProp_value);
            OntologyManager.getInstance().model.add(platformVersionStat);
            
            ObjectProperty installedSolutionsIDsProp = OntologyManager.getInstance().model.getObjectProperty(NS + "TempEnvironment_installedSolutions");
            String[] tmpInstalledSolutionsIDs_Str = DEVICE_REPORTER_INSTALLEDSOLUTIONS_IDs.split(" ");
            for(int tmpCnt=0; tmpCnt<tmpInstalledSolutionsIDs_Str.length; tmpCnt++)
            {
                String tmpInstanceName = OntologyManager.getInstance().getInstanceNameBySolutionID(tmpInstalledSolutionsIDs_Str[tmpCnt]);
                if(tmpInstanceName.equals("not found!") == false)
                {
                    Resource installedSolutionResource = OntologyManager.getInstance().model.getResource(tmpInstanceName);
                    Statement installedSolutionsIDsStat = OntologyManager.getInstance().model.createStatement(tmpEnvironmentOntInstance, installedSolutionsIDsProp, installedSolutionResource);
                    OntologyManager.getInstance().model.add(installedSolutionsIDsStat);
                    installedSolutionsIDsStats.add(installedSolutionsIDsStat);
                }
            }
            
            ObjectProperty availableSolutionsIDsProp = OntologyManager.getInstance().model.getObjectProperty(NS + "TempEnvironment_availableSolutions");
            String[] tmpAvailableSolutionsIDs_Str = DEVICE_REPORTER_AVAILABLESOLUTIONS_IDs.split(" ");
            for(int tmpCnt=0; tmpCnt<tmpAvailableSolutionsIDs_Str.length; tmpCnt++)
            {
                String tmpInstanceName = OntologyManager.getInstance().getInstanceNameBySolutionID(tmpAvailableSolutionsIDs_Str[tmpCnt]);
                if(tmpInstanceName.equals("not found!") == false)
                {
                    Resource availableSolutionResource = OntologyManager.getInstance().model.getResource(tmpInstanceName);
                    Statement availableSolutionsIDsStat = OntologyManager.getInstance().model.createStatement(tmpEnvironmentOntInstance, availableSolutionsIDsProp, availableSolutionResource);
                    OntologyManager.getInstance().model.add(availableSolutionsIDsStat);
                    availableSolutionsIDsStats.add(availableSolutionsIDsStat);
                }
            }
                                
            DatatypeProperty windowManagerIdProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempEnvironment_windowManager_id");
            Literal windowManagerIdProp_value = OntologyManager.getInstance().model.createTypedLiteral("unknown", XSDDatatype.XSDstring);
            windowManagerIdStat = OntologyManager.getInstance().model.createStatement(tmpEnvironmentOntInstance, windowManagerIdProp, windowManagerIdProp_value);
            OntologyManager.getInstance().model.add(windowManagerIdStat);
            
            DatatypeProperty windowManagerVersionProp = OntologyManager.getInstance().model.getDatatypeProperty(NS + "TempEnvironment_windowManager_version");
            Literal windowManagerVersionProp_value = OntologyManager.getInstance().model.createTypedLiteral("unknown", XSDDatatype.XSDstring);
            windowManagerVersionStat = OntologyManager.getInstance().model.createStatement(tmpEnvironmentOntInstance, windowManagerVersionProp, windowManagerVersionProp_value);
            OntologyManager.getInstance().model.add(windowManagerVersionStat);
        }
        catch(Exception e)
        {
            System.out.println("\ncreateInstanceInOntologyForJSONEnvironment-> EXCEPTION CAUGHT!\n");
            e.printStackTrace();
        }
        return res;
    }
}
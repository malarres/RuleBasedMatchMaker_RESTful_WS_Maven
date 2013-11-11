package com.certh.iti.cloud4all.inference;
/**
 *
 * @author nkak
 */

import com.certh.iti.cloud4all.feedback.FeedbackManager;
import com.certh.iti.cloud4all.instantiation.InstantiationManager;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.certh.iti.cloud4all.ontology.*;
import com.certh.iti.cloud4all.prevayler.PrevaylerManager;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class RulesManager 
{
    public static final int GET_FIRST_INSTANCE_BETWEEN_SOLUTIONS_OF_THE_SAME_TYPE = 0;
    public static final int GET_RANDOM_INSTANCE_BEWTWEEN_SOLUTIONS_OF_THE_SAME_TYPE = 1;
    
    public Model m;
    public BufferedReader br;
    
    String uri_MatchMaker_rules;
    
    private static RulesManager instance = null;
    
    private RulesManager() 
    {
        m = ModelFactory.createDefaultModel();
        
        String currentDir = System.getProperty("user.dir");
        uri_MatchMaker_rules = currentDir + "/../webapps/CLOUD4All_RBMM_Restful_WS/WEB-INF/MatchMakerRules.rules";
    }
    
    public static RulesManager getInstance() 
    {
        if(instance == null) 
            instance = new RulesManager();
        return instance;
    }
    
    public String executeMyCloudRulesForFindingHandicapSituations(boolean writeInfModelForDebug)
    {
        String resultForNodeJs = "";
        
        //RULES EXECUTION - START
        List<Rule> rules = Rule.rulesFromURL(uri_MatchMaker_rules);
        GenericRuleReasoner r = new GenericRuleReasoner(rules);
        r.setOWLTranslation(true);           
        r.setTransitiveClosureCaching(true);
        InfModel infmodel = ModelFactory.createInfModel(r, OntologyManager.getInstance().model);
        Model deductionsModel = infmodel.getDeductionsModel();
        OntologyManager.getInstance().model.add(deductionsModel);
        //RULES EXECUTION - END
        
        OntologyManager.getInstance().getInstancesAfterRulesExecution();
      
        //get TempHandicapSituations
        if(OntologyManager.getInstance().allInstances_TempHandicapSituation != null)
        {
            for(int i=0; i<OntologyManager.getInstance().allInstances_TempHandicapSituation.size(); i++)
            {
                if(OntologyManager.getInstance().allInstances_TempHandicapSituation.get(i).problemWithMagnifierFullScreen)
                {
                    resultForNodeJs = "ENABLE_DEFAULT_THEME ENABLE_MAGNIFIER_WITH_INVERSE_COLOURS";
                    //break;
                }
                else if(OntologyManager.getInstance().allInstances_TempHandicapSituation.get(i).problemWithHighContrast)
                {
                    resultForNodeJs = "ENABLE_DEFAULT_THEME ENABLE_MAGNIFIER_WITH_INVERSE_COLOURS";
                    //break;
                }
            }
        }
        
        FeedbackManager.getInstance().findSolutionsToBeExcluded();
        
        //solutions to be excluded
        for(int j=0; j<FeedbackManager.getInstance().solutionsToBeExcluded.size(); j++)
        {
            Solution tempSolution = FeedbackManager.getInstance().solutionsToBeExcluded.get(j);
            if(tempSolution.id.equals("org.gnome.desktop.a11y.magnifier"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_GNOME_MAGNIFIER";
            else if(tempSolution.id.equals("ISO24751.screenMagnifier"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_ISO24751_MAGNIFIER";
            else if(tempSolution.id.equals("com.microsoft.windows.magnifier"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_WINDOWS_MAGNIFIER";
            else if(tempSolution.id.equals("ZoomText.screenMagnifier"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_ZOOMTEXT_MAGNIFIER";
            else if(tempSolution.id.equals("ISO24751.screenReader"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_ISO24751_SCREENREADER";
            else if(tempSolution.id.equals("jaws.screenReader"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_JAWS_SCREENREADER";
            else if(tempSolution.id.equals("nvda.screenReader"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_NVDA_SCREENREADER";
            else if(tempSolution.id.equals("org.gnome.orca"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_ORCA_SCREENREADER";
            else if(tempSolution.id.equals("satogo.screenReader"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_SATOGO_SCREENREADER";
            else if(tempSolution.id.equals("com.android.talkback"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_TALKBACK_SCREENREADER";
            else if(tempSolution.id.equals("webinsight.webAnywhere.windows"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_WEBANYWHERE_SCREENREADER";
            else if(tempSolution.id.equals("Win7BuiltInNarrator.screenReader"))
                resultForNodeJs = resultForNodeJs + " EXCLUDE_WIN7BUILTINNARRATOR_SCREENREADER";
        }
        
        //get TempSolutionsToBeLaunched
        if(OntologyManager.getInstance().allInstances_TempSolutionsToBeLaunched != null)
        {
            for(int i=0; i<OntologyManager.getInstance().allInstances_TempSolutionsToBeLaunched.size(); i++)
            {
                String[] tmpIDsToBeLaunched_Str = OntologyManager.getInstance().allInstances_TempSolutionsToBeLaunched.get(i).IDs.split(" ");
                for(int tmpCnt=0; tmpCnt<tmpIDsToBeLaunched_Str.length; tmpCnt++)
                {
                    String tempID = tmpIDsToBeLaunched_Str[tmpCnt].trim();
                    if(tempID.equals("org.gnome.desktop.a11y.magnifier"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_GNOME_MAGNIFIER";
                    else if(tempID.equals("ISO24751.screenMagnifier"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_ISO24751_MAGNIFIER";
                    else if(tempID.equals("com.microsoft.windows.magnifier"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_WINDOWS_MAGNIFIER";
                    else if(tempID.equals("ZoomText.screenMagnifier"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_ZOOMTEXT_MAGNIFIER";
                    else if(tempID.equals("ISO24751.screenReader"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_ISO24751_SCREENREADER";
                    else if(tempID.equals("jaws.screenReader"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_JAWS_SCREENREADER";
                    else if(tempID.equals("nvda.screenReader"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_NVDA_SCREENREADER";
                    else if(tempID.equals("org.gnome.orca"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_ORCA_SCREENREADER";
                    else if(tempID.equals("satogo.screenReader"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_SATOGO_SCREENREADER";
                    else if(tempID.equals("com.android.talkback"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_TALKBACK_SCREENREADER";
                    else if(tempID.equals("webinsight.webAnywhere.windows"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_WEBANYWHERE_SCREENREADER";
                    else if(tempID.equals("Win7BuiltInNarrator.screenReader"))
                        resultForNodeJs = resultForNodeJs + " LAUNCH_WIN7BUILTINNARRATOR_SCREENREADER";
                }
            }
        }

        //DEBUG
        if(writeInfModelForDebug)
        {
            try
            {
                OutputStream out = new FileOutputStream("./lib/generatedOntologyWithTestUserAndEnvironmement.owl");
                OntologyManager.getInstance().model.write(out, "RDF/XML-ABBREV"); // readable rdf/xml
                out.close();            
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        //-DEBUG
        
        //initialize again the model
        OntologyManager.getInstance().model.remove(deductionsModel); //remove statements created by the rules
        InstantiationManager.getInstance().removeTempInstances();    //remove temp instances for user and environment
        
        return resultForNodeJs;
    }
}
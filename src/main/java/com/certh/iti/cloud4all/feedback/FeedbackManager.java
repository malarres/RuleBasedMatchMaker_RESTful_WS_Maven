package com.certh.iti.cloud4all.feedback;

/**
 *
 * @author nkak
 */

import com.certh.iti.cloud4all.ontology.OntologyManager;
import com.certh.iti.cloud4all.ontology.Shortcut;
import com.certh.iti.cloud4all.ontology.Solution;
import com.certh.iti.cloud4all.prevayler.PrevaylerManager;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackManager 
{
    public static final int TOMCAT_IS_INSTALLED_ON_THE_FINAL_WINDOWS_SERVER = 1;
    public static final String TOMCAT_FINAL_DIR_FOR_RBMM_HTML_FEEDBACK = "C:/xampp/htdocs/RBMM/";
    public static final String TOMCAT_FINAL_DELETE_OLD_FILES_DIR = "C://xampp//htdocs//RBMM";
    public static final String TOMCAT_FINAL_IP = "160.40.50.183";
    
    public static final int TOMCAT_IS_INSTALLED_ON_OS_X_FOR_TESTING = 2;
    public static final String TOMCAT_OSX_DIR_FOR_RBMM_HTML_FEEDBACK = "/Applications/XAMPP/htdocs/RBMM/";
    public static final String TOMCAT_OSX_DELETE_OLD_FILES_DIR = "/Applications/XAMPP/htdocs/RBMM";
    public static final String TOMCAT_OSX_IP = "192.168.1.19"; //DYNAMIC!
    
    public int current_tomcat_server;
    public String current_tomcat_dir_for_rbmm_html_feedback;
    public String current_tomcat_delete_old_files_dir;
    public String current_tomcat_ip;
    
    public static final int GENERATE_DIFFERENT_HTML_FOR_EACH_USER = 0;
    public static final int GENERATE_ONLY_ONE_HTML_THAT_IS_REPLACED_IN_EACH_RBMM_EXECUTION = 1;
    public int current_HTML_mode;
    public static final int DELETE_FILES_OLDER_THAN_THESE_MINUTES = 5;
    
    private static FeedbackManager instance = null;
    public ArrayList<Solution> solutionsToBeLaunched;
    public ArrayList<Solution> allAvailableSolutions;
    public ArrayList<Solution> solutionsToBeExcluded;
    public boolean writeFeedbackToFile;
    String HTML_START;
    String HTML_END;
    String HTML_STYLE;
    String HTML_NAVIGATION;
    String HTML_MAIN_DIV;
    public String appliedCommonPrefs;
    public String englishURL;
    public String germanURL;
    public String greekURL;
    public String spanishURL;
    
    Long curMilliseconds;
    
    public boolean TEST_GENERATE_FEEDBACK_FOR_NVDA;
    
    private FeedbackManager() 
    {
        //current_tomcat_server = TOMCAT_IS_INSTALLED_ON_OS_X_FOR_TESTING;
        current_tomcat_server = TOMCAT_IS_INSTALLED_ON_THE_FINAL_WINDOWS_SERVER;
        
        //current_HTML_mode = GENERATE_DIFFERENT_HTML_FOR_EACH_USER;
        current_HTML_mode = GENERATE_ONLY_ONE_HTML_THAT_IS_REPLACED_IN_EACH_RBMM_EXECUTION;
        
        TEST_GENERATE_FEEDBACK_FOR_NVDA = false;
        
        if(current_tomcat_server == TOMCAT_IS_INSTALLED_ON_THE_FINAL_WINDOWS_SERVER)
        {
            current_tomcat_dir_for_rbmm_html_feedback = TOMCAT_FINAL_DIR_FOR_RBMM_HTML_FEEDBACK;
            current_tomcat_delete_old_files_dir = TOMCAT_FINAL_DELETE_OLD_FILES_DIR;
            current_tomcat_ip = TOMCAT_FINAL_IP;
        }
        else if(current_tomcat_server == TOMCAT_IS_INSTALLED_ON_OS_X_FOR_TESTING)
        {
            current_tomcat_dir_for_rbmm_html_feedback = TOMCAT_OSX_DIR_FOR_RBMM_HTML_FEEDBACK;
            current_tomcat_delete_old_files_dir = TOMCAT_OSX_DELETE_OLD_FILES_DIR;
            current_tomcat_ip = TOMCAT_OSX_IP;
        }
        
        solutionsToBeLaunched = new ArrayList<Solution>(); 
        allAvailableSolutions = new ArrayList<Solution>();
        solutionsToBeExcluded = new ArrayList<Solution>();
        writeFeedbackToFile = false;
        
        HTML_STYLE = "\n<style type=\"text/css\">" +
            "\n#container {" + 
            "\nmargin: 0 auto;" + 
            "\nwidth: 60em;" +
            "\n}" + 
            "body {" +
            "\nfont-family: Verdana,Arial,Helvetica,sans-serif;" +
            "\nmargin: 0;" +
            "\n}" + 
            "\n#helpernav {" +
            "\nheight: 1.2em;" +
            "\nline-height: 1.2em;" +
            "\nwidth: 36em;" +
            "\n}" +
            "\n#helpernav ul {" +
            "\nfont-size: 0.6em;" +
            "\nmargin: 0;" +
            "\npadding: 0;" +
            "\n}" +
            "\n.hidden {" +
            "\nheight: 1px;" +
            "\nleft: -10000px;" +
            "\noverflow: hidden;" +
            "\nposition: absolute;" +
            "\ntop: auto;" +
            "\nwidth: 1px;" +
            "\n}" +
            "\n@media Screen" +
            "\n{" +
            "\nTABLE {" +
            "\nMARGIN: 1em 0px; FONT-SIZE: 0.8em" +
            "\n}" +
            "\nTABLE {" +
                "\nBORDER-BOTTOM: #2e2e2e 1px solid;" +
		"\nBORDER-LEFT: #2e2e2e 1px solid;" +
		"\nPADDING-BOTTOM: 5px;" + 
		"\nPADDING-LEFT: 5px;" + 
		"\nPADDING-RIGHT: 5px;" + 
		"\nBORDER-COLLAPSE: collapse;" + 
		"\nBORDER-TOP: #2e2e2e 1px solid;" + 
		"\nBORDER-RIGHT: #2e2e2e 1px solid;" + 
		"\nPADDING-TOP: 5px" +
            "\n}" +
            "\nTD {" +
		"\nBORDER-BOTTOM: #2e2e2e 1px solid;" + 
		"\nBORDER-LEFT: #2e2e2e 1px solid;" + 
		"\nPADDING-BOTTOM: 5px;" + 
		"\nPADDING-LEFT: 5px;" + 
		"\nPADDING-RIGHT: 5px;" + 
		"\nBORDER-COLLAPSE: collapse;" + 
		"\nBORDER-TOP: #2e2e2e 1px solid;" + 
		"\nBORDER-RIGHT: #2e2e2e 1px solid;" + 
		"\nPADDING-TOP: 5px" +
            "\n}" +
            "\nTH {" +
		"\nBORDER-BOTTOM: #2e2e2e 1px solid;" + 
		"\nBORDER-LEFT: #2e2e2e 1px solid;" + 
		"\nPADDING-BOTTOM: 5px;" + 
		"\nPADDING-LEFT: 5px;" + 
		"\nPADDING-RIGHT: 5px;" + 
		"\nBORDER-COLLAPSE: collapse;" + 
		"\nBORDER-TOP: #2e2e2e 1px solid;" + 
		"\nBORDER-RIGHT: #2e2e2e 1px solid;" + 
		"\nPADDING-TOP: 5px" +
            "\n}" +
            "\nTD {" +
		"\nTEXT-ALIGN: center" +
            "\n}" +
            "\nTH {" +
		"\nTEXT-ALIGN: center" +
            "\n}" +
            "\nTH {" +
		"\nBACKGROUND-COLOR: #fff5e5" +
            "\n}" +
            "\nCAPTION {" +
		"\nTEXT-ALIGN: left; MARGIN: 1em 0px" +
            "\n}" +
            "\nCAPTION SPAN {" +
		"\nPADDING-BOTTOM: 10px; FONT-STYLE: italic; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; PADDING-TOP: 10px" +
            "\n}" +
        "\n</style>";
    }
    
    public static FeedbackManager getInstance() 
    {
        if(instance == null) 
            instance = new FeedbackManager();
        return instance;
    }
    
    public void init()
    {
        solutionsToBeLaunched.clear();
        allAvailableSolutions.clear();
        solutionsToBeExcluded.clear();
        
        appliedCommonPrefs = "";
        englishURL = "";
        germanURL = "";
        greekURL = "";
        spanishURL = "";
    }
    
    public void findSolutionsToBeExcluded()
    {
        //solutionsToBeLaunched has max 3 items (1 screen reader, 1 magnifier, 1 screen reader[according to the number of RDF statements])
        
        if(solutionsToBeLaunched.size()>0 
                /*&& (solutionsToBeLaunched.get(0)!=null || solutionsToBeLaunched.get(1)!=null)*/)
        {
            for(int i=0; i<allAvailableSolutions.size(); i++)
            {
                Solution tempSolution = allAvailableSolutions.get(i);
                if(solutionsToBeLaunched.size() == 2)
                {
                    if(solutionsToBeLaunched.get(0)!=null && solutionsToBeLaunched.get(1)==null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(0).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)==null && solutionsToBeLaunched.get(1)!=null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(1).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)!=null && solutionsToBeLaunched.get(1)!=null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(0).hasSolutionName.trim().toLowerCase()) == false
                                && tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(1).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                }
                else if(solutionsToBeLaunched.size() == 3)
                {
                    if(solutionsToBeLaunched.get(0)!=null 
                            && solutionsToBeLaunched.get(1)==null
                            && solutionsToBeLaunched.get(2)==null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(0).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)==null 
                            && solutionsToBeLaunched.get(1)!=null
                            && solutionsToBeLaunched.get(2)==null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(1).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)==null 
                            && solutionsToBeLaunched.get(1)==null
                            && solutionsToBeLaunched.get(2)!=null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(2).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)!=null 
                            && solutionsToBeLaunched.get(1)!=null
                            && solutionsToBeLaunched.get(2)==null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(0).hasSolutionName.trim().toLowerCase()) == false
                                && tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(1).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)==null 
                            && solutionsToBeLaunched.get(1)!=null
                            && solutionsToBeLaunched.get(2)!=null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(1).hasSolutionName.trim().toLowerCase()) == false
                                && tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(2).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)!=null 
                            && solutionsToBeLaunched.get(1)==null
                            && solutionsToBeLaunched.get(2)!=null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(0).hasSolutionName.trim().toLowerCase()) == false
                                && tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(2).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                    else if(solutionsToBeLaunched.get(0)!=null 
                            && solutionsToBeLaunched.get(1)!=null
                            && solutionsToBeLaunched.get(2)!=null)
                    {
                        if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(0).hasSolutionName.trim().toLowerCase()) == false
                                && tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(1).hasSolutionName.trim().toLowerCase()) == false
                                && tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(2).hasSolutionName.trim().toLowerCase()) == false)
                        {
                            solutionsToBeExcluded.add(tempSolution);
                        }
                    }
                }
            }
        }
    }
    
    public String prepareHTML(int tmpLanguage)
    {
        TranslationManager.getInstance().setLanguage(tmpLanguage);
        
        HTML_START = "<!DOCTYPE HTML PUBLIC" + 
            "\n\"-//W3C//DTD HTML 4.01 Transitional//EN\"" +
            "\n\"http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd\">" + 
            "\n\n<HTML>" +
            "\n<HEAD>" + 
            "\n<TITLE>Cloud4All - Rule-based MatchMaker adaptation feedback</TITLE>" +
            "\n<META HTTP-EQUIV=\"Content-Type\" " +
            "\n\tCONTENT=\"text/html; charset=" + TranslationManager.getInstance().ENCODING + "\">" +
            "\n</HEAD>" + 
            HTML_STYLE + 
            "\n<BODY>" +
            "\n<P>\n";
        
        HTML_NAVIGATION = "\n<div id=\"container\">" + 
            "\n<div id=\"helpernav\" role=\"navigation\">" + 
            "\n<ul id=\"accessibility_nav\" class=\"hidden\">" + 
            "\n<li><a href=\"#content\">" + TranslationManager.getInstance().GO_TO_CONTENT + "</a></li>" + 
            "\n<li><a href=\"#mainnav\">" + TranslationManager.getInstance().GO_TO_NAVIGATION + "</a></li>" + 
            "\n</ul>" + 
        "\n</div>" + 
        "\n<div id=\"mainnavigation\" role=\"navigation\">" + 
            "\n<ul role=\"navigation\">" + 
            "\n<li>" + 
                "\n<a href=\"#decisions\">" + TranslationManager.getInstance().YOUR_ADAPTATIONS + "</a>" + 
            "\n</li>" + 
            "\n<li>" + 
                "\n<a href=\"#help\">" + TranslationManager.getInstance().HELP + "</a>" + 
            "\n</li>" + 
            "\n<li>" + 
                "\n<a href=\"#recommendations\">" + TranslationManager.getInstance().FURTHER_RECOMMENDATIONS + "</a>" + 
            "\n</li>" + 
            "\n</ul>" + 
        "\n</div>";
        
        String solutionName = "";
        String solutionUserManualURL = "";
        String alternativeSolutions = "";        
        String generalCommands = "";
        String systemCaretCommands = "";
        String reviewCommands = "";
        String browserCommands = "";
        String tableCommands = "";
        
        Solution tmpSolution = null;
        for(int i=0; i<solutionsToBeLaunched.size(); i++)
        {
            tmpSolution = solutionsToBeLaunched.get(i);
            if(tmpSolution != null)
            {
                solutionName = solutionName + "<b>" + tmpSolution.hasSolutionName + "</b>";
                solutionUserManualURL = tmpSolution.userManualURL;
            }
        }
        
        for(int i=0; i<allAvailableSolutions.size(); i++)
        {
            Solution tempSolution = allAvailableSolutions.get(i);
            if(solutionsToBeLaunched.size()>0 && solutionsToBeLaunched.get(0)!=null)
            {
                if(tempSolution.hasSolutionName.trim().toLowerCase().equals(solutionsToBeLaunched.get(0).hasSolutionName.trim().toLowerCase()) == false)
                {
                    if(alternativeSolutions.length() == 0)
                        alternativeSolutions = "<i>" + tempSolution.hasSolutionName + "</i>";
                    else
                        alternativeSolutions = alternativeSolutions + ", <i>" + tempSolution.hasSolutionName + "</i>";
                }
            }
        }
        
        if(alternativeSolutions.indexOf(",") != -1)
            TranslationManager.getInstance().WANT_TO_LAUNCH = TranslationManager.getInstance().WANT_TO_LAUNCH_ONE_OF;
        
        if(tmpSolution != null)
        {
            for(int i=0; i<tmpSolution.shortcuts.size(); i++)
            {
                Shortcut tmpShortcut = tmpSolution.shortcuts.get(i);
                
                String tmpShortcutName = "";
                if(tmpLanguage == TranslationManager.ENGLISH)
                    tmpShortcutName = tmpShortcut.name_EN;
                else if(tmpLanguage == TranslationManager.GERMAN)
                    tmpShortcutName = tmpShortcut.name_DE;
                else if(tmpLanguage == TranslationManager.GREEK)
                    tmpShortcutName = tmpShortcut.name_GR;
                else if(tmpLanguage == TranslationManager.SPANISH)
                    tmpShortcutName = tmpShortcut.name_SP;
                
                if(tmpShortcut.category.trim().equals("General commands"))
                {
                    generalCommands = generalCommands + "\n<TR>" +
                        "\n<TH id=modifier>" + tmpShortcutName + "</TH>" +
                        "\n<TD>" + tmpShortcut.desktopLayout + "</TD>" +
                        "\n<TD>" + tmpShortcut.laptopLayout + "</TD></TR>";
                }
                else if(tmpShortcut.category.trim().equals("System caret commands"))
                {
                    systemCaretCommands = systemCaretCommands + "\n<TR>" +
                        "\n<TH id=modifier>" + tmpShortcutName + "</TH>" +
                        "\n<TD>" + tmpShortcut.desktopLayout + "</TD>" +
                        "\n<TD>" + tmpShortcut.laptopLayout + "</TD></TR>";
                }
                else if(tmpShortcut.category.trim().equals("Review commands"))
                {
                    reviewCommands = reviewCommands + "\n<TR>" +
                        "\n<TH id=modifier>" + tmpShortcutName + "</TH>" +
                        "\n<TD>" + tmpShortcut.desktopLayout + "</TD>" +
                        "\n<TD>" + tmpShortcut.laptopLayout + "</TD></TR>";
                }
                else if(tmpShortcut.category.trim().equals("Browser commands"))
                {
                    browserCommands = browserCommands + "\n<TR>" +
                        "\n<TH id=modifier>" + tmpShortcutName + "</TH>" +
                        "\n<TD>" + tmpShortcut.desktopLayout + "</TD>" +
                        "\n<TD>" + tmpShortcut.laptopLayout + "</TD></TR>";
                }
                else if(tmpShortcut.category.trim().equals("Table"))
                {
                    tableCommands = tableCommands + "\n<TR>" +
                        "\n<TH id=modifier>" + tmpShortcutName + "</TH>" +
                        "\n<TD>" + tmpShortcut.desktopLayout + "</TD>" +
                        "\n<TD>" + tmpShortcut.laptopLayout + "</TD></TR>";
                }
            }
        }
        
        HTML_MAIN_DIV = "\n<div id=\"content\" role=\"main\">" +
            "\n<h1 class=\"entry-title\"><a name=\"decisions\">" + TranslationManager.getInstance().YOUR_ADAPTATIONS + "</a></h1>" +
            "\n<p>" + TranslationManager.getInstance().WE_HAVE_LAUNCHED + solutionName + TranslationManager.getInstance().FOR_YOU + "</p>" +
			"\n<p>" + TranslationManager.getInstance().THE_FOLLOWING_PREFS + appliedCommonPrefs + "</p>" +
			"\n<h1 class=\"entry-title\"><a name=\"help\">" + TranslationManager.getInstance().HELP + "</a></h1>" +
			"\n<p>" + TranslationManager.getInstance().WE_HAVE_NEVER + solutionName + TranslationManager.getInstance().FOR_YOU_BEFORE + "</p>" +
			"\n<h2 class=\"entry-title\"><a name=\"help_type\">" + TranslationManager.getInstance().SHORTCUTS + "</a></h2>" +
			"\n<!--general short cuts-->" +
			"\n<h3 class=\"entry-title\">" + TranslationManager.getInstance().GENERAL_COMMANDS + "</h3>" +
			"\n<TABLE " +
			"\nsummary=\"" + TranslationManager.getInstance().GENERAL_COMMANDS_SUMMARY + "\">" +
			  "\n<CAPTION align=bottom>" + TranslationManager.getInstance().DEFAULT_GENERAL_COMMANDS + solutionName + ".</CAPTION>" +
			  "\n<TBODY>" +
			  "\n<TR>" +
				"\n<TH id=type scope=col></TH>" +
				"\n<TH id=desktop scope=col>" + TranslationManager.getInstance().DESKTOP_LAYOUT + "</TH>" +
				"\n<TH id=laptop scope=col>" + TranslationManager.getInstance().LAPTOP_LAYOUT + "</TH></TR>" +
                                "\n" + generalCommands +
				"\n</TBODY></TABLE>" +
			"\n<!-- system caret short cuts-->" +
			"\n<h3 class=\"entry-title\">" + TranslationManager.getInstance().SYSTEM_CARET_COMMANDS + "</h3>" +
			"\n<TABLE " +
			"\nsummary=\"" + TranslationManager.getInstance().SYSTEM_CARET_SUMMARY_COMMANDS + "\">" +
			  "\n<CAPTION align=bottom>" + TranslationManager.getInstance().DEFAULT_SYSTEM_CARET_COMMANDS + solutionName + ".</CAPTION>" +
			  "\n<TBODY>" +
			  "\n<TR>" +
				"\n<TH id=type scope=col></TH>" +
				"\n<TH id=desktop scope=col>" + TranslationManager.getInstance().DESKTOP_LAYOUT + "</TH>" +
				"\n<TH id=laptop scope=col>" + TranslationManager.getInstance().LAPTOP_LAYOUT + "</TH></TR>" +
                                "\n" + systemCaretCommands +
                            "\n</TBODY></TABLE>" +			
			"\n<!-- Review short cuts-->" +
			"\n<h3 class=\"entry-title\">" + TranslationManager.getInstance().REVIEW_COMMANDS + "</h3>" +
			"\n<TABLE " +
			"\nsummary=\"" + TranslationManager.getInstance().REVIEW_COMMANDS_SUMMARY + "\">" +
			  "\n<CAPTION align=bottom>" + TranslationManager.getInstance().DEFAULT_REVIEW_COMMANDS + solutionName + ".</CAPTION>" +
			  "\n<TBODY>" +
			  "\n<TR>" +
				"\n<TH id=type scope=col></TH>" +
				"\n<TH id=desktop scope=col>" + TranslationManager.getInstance().DESKTOP_LAYOUT + "</TH>" +
				"\n<TH id=laptop scope=col>" + TranslationManager.getInstance().LAPTOP_LAYOUT + "</TH></TR>" +
                                "\n" + reviewCommands + 
			  "\n</TBODY></TABLE>" +
			"\n<!-- Browser short cuts -->" +
			"\n<h3 class=\"entry-title\">" + TranslationManager.getInstance().BROWSER_COMMANDS + "</h3>" +
			"\n<TABLE " +
			"\nsummary=\"" + TranslationManager.getInstance().BROWSER_COMMANDS_SUMMARY + "\">" +
			  "\n<CAPTION align=bottom>" + TranslationManager.getInstance().DEFAULT_BROWSER_COMMANDS + solutionName + ".</CAPTION>" +
			  "\n<TBODY>" +
			  "\n<TR>" +
				"\n<TH id=type scope=col></TH>" +
				"\n<TH id=desktop scope=col>" + TranslationManager.getInstance().DESKTOP_LAYOUT + "</TH>" +
				"\n<TH id=laptop scope=col>" + TranslationManager.getInstance().LAPTOP_LAYOUT + "</TH></TR>" +
        			"\n" + browserCommands + 
				"\n</TBODY></TABLE>" +
			"\n<!-- Table short cuts--> " +
			"\n<h3 class=\"entry-title\">" + TranslationManager.getInstance().TABLE_COMMANDS + "</h3>" +
			"\n<TABLE " +
			"\nsummary=\"" + TranslationManager.getInstance().TABLE_COMMANDS_SUMMARY + "\">" +
			  "\n<CAPTION align=bottom>" + TranslationManager.getInstance().DEFAULT_TABLE_COMMANDS + solutionName + ".</CAPTION>" +
			  "\n<TBODY>" +
			  "\n<TR>" +
				"\n<TH id=type scope=col></TH>" +
				"\n<TH id=desktop scope=col>" + TranslationManager.getInstance().DESKTOP_LAYOUT + "</TH>" +
				"\n<TH id=laptop scope=col>" + TranslationManager.getInstance().LAPTOP_LAYOUT + "</TH></TR>" +
                                "\n" + tableCommands + 
			  "\n</TBODY></TABLE>" +
			"\n<h2 class=\"entry-title\"><a name=\"user_tut\">" + TranslationManager.getInstance().USER_TUTORIAL + "</a></h2>" +
			"\n<p>" + TranslationManager.getInstance().USER_TUTORIAL_AVAILABLE + "<a name=\"user_tut_url\" href=\"" + solutionUserManualURL + "\" >" + solutionUserManualURL + "</a></p>" +  
			"\n<h1 class=\"entry-title\"><a name=\"recommendations\">" + TranslationManager.getInstance().FURTHER_RECOMMENDATIONS + "</a></h1>" +
            "\n<p>" + TranslationManager.getInstance().ALTERNATIVE_SOLUTIONS + alternativeSolutions + "</p>" +
			"\n<p>" + TranslationManager.getInstance().WANT_TO_LAUNCH + alternativeSolutions + TranslationManager.getInstance().INSTEAD_OF + solutionName + TranslationManager.getInstance().QUESTIONMARK + "</p>" +
         "\n</div>";
        HTML_END = "\n</P>" + 
            HTML_NAVIGATION + 
            HTML_MAIN_DIV +  
            "\n</BODY>" +
            "\n</HTML>";
        
        return HTML_START + HTML_END;
    }
    
    public void writeHTML()
    {
        curMilliseconds = System.currentTimeMillis();
        deleteOldFiles();
        String contentsToWrite = "";
        
        //English
        contentsToWrite = prepareHTML(TranslationManager.ENGLISH);
        
        OutputStreamWriter writer_EN = null;
        try {     
            if(current_HTML_mode == GENERATE_DIFFERENT_HTML_FOR_EACH_USER)
            {    
                writer_EN=new OutputStreamWriter(new FileOutputStream(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackEnglish_" + Long.toString(curMilliseconds) + ".html"),"UTF-8");
                englishURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackEnglish_" + Long.toString(curMilliseconds) + ".html";
            }
            else if(current_HTML_mode == GENERATE_ONLY_ONE_HTML_THAT_IS_REPLACED_IN_EACH_RBMM_EXECUTION)
            {    
                writer_EN=new OutputStreamWriter(new FileOutputStream(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackEnglish.html"),"UTF-8");
                englishURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackEnglish.html";
            }
            writer_EN.write(contentsToWrite);
            writer_EN.close();
            
            //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\n\nFEEDBACK URL (English): " + englishURL;
        } catch (IOException ex) {
            Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer_EN.close();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //German
        contentsToWrite = prepareHTML(TranslationManager.GERMAN);
        
        OutputStreamWriter writer_DE =null;
        try {       
            if(current_HTML_mode == GENERATE_DIFFERENT_HTML_FOR_EACH_USER)
            {
                writer_DE = new OutputStreamWriter(new FileOutputStream(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackGerman_" + Long.toString(curMilliseconds) + ".html"),"UTF-8");
                germanURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackGerman_" + Long.toString(curMilliseconds) + ".html";
            }
            else if(current_HTML_mode == GENERATE_ONLY_ONE_HTML_THAT_IS_REPLACED_IN_EACH_RBMM_EXECUTION)
            {
                writer_DE = new OutputStreamWriter(new FileOutputStream(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackGerman.html"),"UTF-8");
                germanURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackGerman.html";
            }
            writer_DE.write(contentsToWrite);
            writer_DE.close();
            
            //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nFEEDBACK URL (German): " + germanURL;
        } catch (IOException ex) {
            Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer_DE.close();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Greek
        contentsToWrite = prepareHTML(TranslationManager.GREEK);
        
        FileWriter fileWriter_GR = null;
        try {       
            File newTextFile = null;
            if(current_HTML_mode == GENERATE_DIFFERENT_HTML_FOR_EACH_USER)
            {
                newTextFile = new File(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackGreek_" + Long.toString(curMilliseconds) + ".html");
                greekURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackGreek_" + Long.toString(curMilliseconds) + ".html";
            }
            else if(current_HTML_mode == GENERATE_ONLY_ONE_HTML_THAT_IS_REPLACED_IN_EACH_RBMM_EXECUTION)
            {
                newTextFile = new File(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackGreek.html");
                greekURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackGreek.html";
            }
            fileWriter_GR = new FileWriter(newTextFile);
            fileWriter_GR.write(contentsToWrite);
            fileWriter_GR.close();
            
            //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nFEEDBACK URL (Greek): " + greekURL;
        } catch (IOException ex) {
            Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter_GR.close();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Spanish
        contentsToWrite = prepareHTML(TranslationManager.SPANISH);
        
        OutputStreamWriter writer_SP=null;
        try {            
            if(current_HTML_mode == GENERATE_DIFFERENT_HTML_FOR_EACH_USER)
            {
                writer_SP=new OutputStreamWriter(new FileOutputStream(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackSpanish_" + Long.toString(curMilliseconds) + ".html"),"UTF-8");
                spanishURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackSpanish_" + Long.toString(curMilliseconds) + ".html";
            }
            else if(current_HTML_mode == GENERATE_ONLY_ONE_HTML_THAT_IS_REPLACED_IN_EACH_RBMM_EXECUTION)
            {
                writer_SP=new OutputStreamWriter(new FileOutputStream(current_tomcat_dir_for_rbmm_html_feedback + "RBMMFeedbackSpanish.html"),"UTF-8");
                spanishURL = "http://" + current_tomcat_ip + "/RBMM/RBMMFeedbackSpanish.html";
            }
            writer_SP.write(contentsToWrite);
            writer_SP.close();
            
            //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nFEEDBACK URL (Spanish): " + spanishURL;
            //        "\n   [These files will be deleted automatically after " + Long.toString(DELETE_FILES_OLDER_THAN_THESE_MINUTES) + " minutes]\n";
        } catch (IOException ex) {
            Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer_SP.close();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteOldFiles()
    {
        File directory = new File(current_tomcat_delete_old_files_dir);
 
        //get all the files from a directory
        File[] fList = directory.listFiles();
 
        for (File file : fList){
            if (file.isFile())
            {
                String tmpFilename = file.getName();
                if(tmpFilename.equals(".DS_Store") == false)
                {
                    int underscoreIndex = tmpFilename.indexOf("_");
                    int dotIndex = tmpFilename.indexOf(".");
                    
                    if(current_HTML_mode==GENERATE_DIFFERENT_HTML_FOR_EACH_USER && underscoreIndex!=-1 && dotIndex!=-1)
                    {
                        String timeOfCreationInMillisecondsStr = tmpFilename.substring(underscoreIndex+1, dotIndex);
                        Long timeOfCreationInMilliseconds = Long.parseLong(timeOfCreationInMillisecondsStr);
                        if( (curMilliseconds-timeOfCreationInMilliseconds) > (DELETE_FILES_OLDER_THAN_THESE_MINUTES*60*1000) )
                        {
                            File f = new File(current_tomcat_delete_old_files_dir + "//" + tmpFilename);
                            try{
                                f.delete();
                                //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nOLD FILE " + tmpFilename + " DELETED!";
                            }catch(Exception e){
                                // if any error occurs
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(current_HTML_mode == GENERATE_ONLY_ONE_HTML_THAT_IS_REPLACED_IN_EACH_RBMM_EXECUTION)
                    {
                        File f = new File(current_tomcat_delete_old_files_dir + "//" + tmpFilename);
                        try{
                            f.delete();
                            //PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nOLD FILE " + tmpFilename + " DELETED!";
                        }catch(Exception e){
                            // if any error occurs
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
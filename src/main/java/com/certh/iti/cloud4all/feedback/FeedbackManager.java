package com.certh.iti.cloud4all.feedback;

/**
 *
 * @author nkak
 */

import com.certh.iti.cloud4all.prevayler.PrevaylerManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackManager 
{
    public static final int DELETE_FILES_OLDER_THAN_THESE_MINUTES = 5;
    
    private static FeedbackManager instance = null;
    public boolean writeFeedbackToFile;
    String HTML_START;
    String HTML_START_GR;
    String HTML_END;
    public String feedback_EN;   
    public String feedback_DE;
    public String feedback_GR;
    public String feedback_SP;
    Long curMilliseconds;
    
    private FeedbackManager() 
    {
        writeFeedbackToFile = true;
        HTML_START = "<!DOCTYPE HTML PUBLIC" + 
            "\n\"-//W3C//DTD HTML 4.01 Transitional//EN\"" +
            "\n\"http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd\">" + 
            "\n\n<HTML>" +
            "\n<HEAD>" + 
            "\n<TITLE>Cloud4All - Rule-based MatchMaker feedback</TITLE>" +
            "\n<META HTTP-EQUIV=\"Content-Type\" " +
            "\n\tCONTENT=\"text/html; charset=utf-8\">" +
            "\n</HEAD>" + 
            "\n<BODY>" +
            "\n<H1>Cloud4All - Rule-based MatchMaker feedback</H1>" +
            "\n<P>\n";
        HTML_START_GR = "<!DOCTYPE HTML PUBLIC" + 
            "\n\"-//W3C//DTD HTML 4.01 Transitional//EN\"" +
            "\n\"http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd\">" + 
            "\n\n<HTML>" +
            "\n<HEAD>" + 
            "\n<TITLE>Cloud4All - Rule-based MatchMaker feedback</TITLE>" +
            "\n<META HTTP-EQUIV=\"Content-Type\" " +
            "\n\tCONTENT=\"text/html; charset=iso-8859-7\">" +
            "\n</HEAD>" + 
            "\n<BODY>" +
            "\n<H1>Cloud4All - Rule-based MatchMaker feedback</H1>" +
            "\n<P>\n";
        HTML_END = "\n</P>" + 
            "\n</BODY>" +
            "\n</HTML>";
    }
    
    public static FeedbackManager getInstance() 
    {
        if(instance == null) 
            instance = new FeedbackManager();
        return instance;
    }
    
    public void init()
    {
        feedback_EN = "";
        feedback_DE = "";
        feedback_GR = "";
        feedback_SP = "";
    }
    
    public void writeHTML()
    {
        curMilliseconds = System.currentTimeMillis();
        deleteOldFiles();
        feedback_EN = HTML_START + feedback_EN + HTML_END;
        feedback_DE = HTML_START + feedback_DE + HTML_END;
        feedback_GR = HTML_START_GR + feedback_GR + HTML_END;
        feedback_SP = HTML_START + feedback_SP + HTML_END;
        
        //English
        FileWriter fileWriter_EN = null;
        try {            
            File newTextFile = new File("C:/xampp/htdocs/RBMM/RBMMFeedbackEnglish_" + Long.toString(curMilliseconds) + ".html");
            fileWriter_EN = new FileWriter(newTextFile);
            fileWriter_EN.write(feedback_EN);
            fileWriter_EN.close();
            PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\n\nFEEDBACK URL (English): http://160.40.50.183/RBMM/RBMMFeedbackEnglish_" + Long.toString(curMilliseconds) + ".html";
        } catch (IOException ex) {
            Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter_EN.close();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //German
        FileWriter fileWriter_DE = null;
        try {            
            File newTextFile = new File("C:/xampp/htdocs/RBMM/RBMMFeedbackGerman_" + Long.toString(curMilliseconds) + ".html");
            fileWriter_DE = new FileWriter(newTextFile);
            fileWriter_DE.write(feedback_DE);
            fileWriter_DE.close();
            PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nFEEDBACK URL (German): http://160.40.50.183/RBMM/RBMMFeedbackGerman_" + Long.toString(curMilliseconds) + ".html";
        } catch (IOException ex) {
            Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter_DE.close();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Greek
        FileWriter fileWriter_GR = null;
        try {            
            File newTextFile = new File("C:/xampp/htdocs/RBMM/RBMMFeedbackGreek_" + Long.toString(curMilliseconds) + ".html");
            fileWriter_GR = new FileWriter(newTextFile);
            fileWriter_GR.write(feedback_GR);
            fileWriter_GR.close();
            PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nFEEDBACK URL (Greek): http://160.40.50.183/RBMM/RBMMFeedbackGreek_" + Long.toString(curMilliseconds) + ".html";
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
        FileWriter fileWriter_SP = null;
        try {            
            File newTextFile = new File("C:/xampp/htdocs/RBMM/RBMMFeedbackSpanish_" + Long.toString(curMilliseconds) + ".html");
            fileWriter_SP = new FileWriter(newTextFile);
            fileWriter_SP.write(feedback_SP);
            fileWriter_SP.close();
            PrevaylerManager.getInstance().debug = PrevaylerManager.getInstance().debug + "\nFEEDBACK URL (Spanish): http://160.40.50.183/RBMM/RBMMFeedbackSpanish_" + Long.toString(curMilliseconds) + ".html" + 
                    "\n   [These files will be deleted automatically after " + Long.toString(DELETE_FILES_OLDER_THAN_THESE_MINUTES) + " minutes]\n";
        } catch (IOException ex) {
            Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter_SP.close();
            } catch (IOException ex) {
                Logger.getLogger(FeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteOldFiles()
    {
        File directory = new File("C://xampp//htdocs//RBMM");
 
        //get all the files from a directory
        File[] fList = directory.listFiles();
 
        for (File file : fList){
            if (file.isFile())
            {
                String tmpFilename = file.getName();
                int underscoreIndex = tmpFilename.indexOf("_");
                int dotIndex = tmpFilename.indexOf(".");
                if(underscoreIndex!=-1 && dotIndex!=-1)
                {
                    String timeOfCreationInMillisecondsStr = tmpFilename.substring(underscoreIndex+1, dotIndex);
                    Long timeOfCreationInMilliseconds = Long.parseLong(timeOfCreationInMillisecondsStr);
                    if( (curMilliseconds-timeOfCreationInMilliseconds) > (DELETE_FILES_OLDER_THAN_THESE_MINUTES*60*1000) )
                    {
                        File f = new File("C://xampp//htdocs//RBMM//" + tmpFilename);
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
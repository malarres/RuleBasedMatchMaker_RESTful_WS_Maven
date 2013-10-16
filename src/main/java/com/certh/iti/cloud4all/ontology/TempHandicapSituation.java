package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class TempHandicapSituation 
{
    public String instanceName;
    public Boolean problemWithFontSize;
    public Boolean problemWithMagnification;
    public Boolean problemWithForegroundAndBackgroundColor;
    public Boolean problemWithScreenReaderAndGnome;
    public Boolean problemWithHighContrast;
    public Boolean problemWithMagnifierFullScreen;
    
    public TempHandicapSituation()
    {
        instanceName = "";
        problemWithFontSize = false;
        problemWithMagnification = false;
        problemWithForegroundAndBackgroundColor = false;
        problemWithScreenReaderAndGnome = false;
        problemWithHighContrast = false;
        problemWithMagnifierFullScreen = false;
    }
    
    @Override
    public String toString()
    {
        String result = "\tHandicap Situation: " + instanceName;
        
        if(problemWithFontSize)
            result = result + "\n\t\tPROBLEM WITH FONT SIZE!";
        if(problemWithMagnification)
            result = result + "\n\t\tPROBLEM WITH MAGNIFICATION!";
        if(problemWithForegroundAndBackgroundColor)
            result = result + "\n\t\tPROBLEM WITH FOREGROUND AND BACKGROUND COLOR!";
        if(problemWithScreenReaderAndGnome)
            result = result + "\n\t\tPROBLEM WITH SCREEN READER AND GNOME!";
        if(problemWithHighContrast)
            result = result + "\n\t\tPROBLEM WITH HIGH CONTRAST!";
        if(problemWithMagnifierFullScreen)
            result = result + "\n\t\tPROBLEM WITH MAGNIFIER FULL SCREEN!";
       
        return result;
    }
}
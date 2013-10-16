package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class TempUser 
{
   Integer fontSize;
   Float magnification;
   String foregroundColor;
   String backgroundColor;
   String screenReaderTTSEnabled_whenLaunchAtStartupIsTrue;
   
   public TempUser()
   {
       fontSize = -1;
       magnification = -1.0f;
       foregroundColor = "";
       backgroundColor = "";
       screenReaderTTSEnabled_whenLaunchAtStartupIsTrue = "";
   }
   
   @Override
   public String toString()
   {
       String result = "\tUser:";
       
       result = result + "\n\t\tfontSize: " + Integer.toString(fontSize);
       result = result + "\n\t\tmagnification: " + Float.toString(magnification);
       result = result + "\n\t\tforegroundColor: " + foregroundColor;
       result = result + "\n\t\tbackgroundColor: " + backgroundColor;
       result = result + "\n\t\tscreenReaderTTSEnabled_whenLaunchAtStartupIsTrue: " + screenReaderTTSEnabled_whenLaunchAtStartupIsTrue;
       
       return result;
   }
}

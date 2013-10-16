package com.certh.iti.cloud4all.ontology;

/**
 *
 * @author nkak
 */
public class TempEnvironment 
{
   String os_id;
   String os_version;
   String windowManager_id;
   String windowManager_version;
   
   
   public TempEnvironment()
   {
       os_id = "";
       os_version = "";
       windowManager_id = "";
       windowManager_version = "";
   }
   
   @Override
   public String toString()
   {
       String result = "\tEnvironment:";
       
       result = result + "\n\t\tos_id: " + os_id;
       result = result + "\n\t\tos_version: " + os_version;
       result = result + "\n\t\twindowManager_id: " + windowManager_id;
       result = result + "\n\t\twindowManager_version: " + windowManager_version;
       
       return result;
   }
}

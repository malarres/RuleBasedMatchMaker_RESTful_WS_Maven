package com.gpii.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author nkak
 */
public class Utils 
{
    private static Utils instance = null;
       
    private Utils() 
    {
    }
    
    public static Utils getInstance() 
    {
        if(instance == null) 
            instance = new Utils();
        return instance;
    }
    
    public void writeFile(String path, String content)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            out.write(content);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();      
        }
    }
    
    public String readFile(String pathname) throws IOException 
    {
        String fileContents = "";
        FileReader fileReader = new FileReader(pathname);   
        int i;
        while((i =  fileReader.read())!=-1)
        {
            char ch = (char)i;
            fileContents = fileContents + ch; 
        }
        return fileContents;
    }
    
}

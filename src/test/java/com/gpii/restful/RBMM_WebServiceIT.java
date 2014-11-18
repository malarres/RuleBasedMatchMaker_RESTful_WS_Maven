package com.gpii.restful;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

public class RBMM_WebServiceIT extends TestCase
{
    public void testWS() 
    {
        String inputFileStr = null;
        String outputFileStr = null;
        
        File fileIN = new File(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/preferences/vladimir.json");
        File fileOUT = new File(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/configurations/newMMOutput.json");
        
        // read expected input
        try {
            FileReader reader = new FileReader(fileIN);
            char[] chars = new char[(int) fileIN.length()];
            reader.read(chars);
            inputFileStr = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // read expected output
        try {
            FileReader reader = new FileReader(fileOUT);
            char[] chars = new char[(int) fileOUT.length()];
            reader.read(chars);
            outputFileStr = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/RBMM/runJSONLDRules");
        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputFileStr);
        String output = response.getEntity(String.class);
        System.out.println("Output from Server ....\n");
        System.out.println(output);
        //assertEquals(output, outputFileStr);
    }
}
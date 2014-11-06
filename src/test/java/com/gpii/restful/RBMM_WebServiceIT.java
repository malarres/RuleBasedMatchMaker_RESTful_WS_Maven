package com.gpii.restful;


import com.gpii.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import sun.nio.cs.StandardCharsets;

public class RBMM_WebServiceIT extends TestCase
{
    public void testWS() 
    {
        String inputJsonStr = null;
        String expectedOutputJsonStr = null;
        
        String filepathIN = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/preferences/input_test1.json";
        String filepathExpectedOUT = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/expected_output_test1.json";
        
        // read input & expected output
        try {
            inputJsonStr = Utils.getInstance().readFile(filepathIN);
            expectedOutputJsonStr = Utils.getInstance().readFile(filepathExpectedOUT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/RBMM/runJSONLDRules");
        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputJsonStr);
        String output = response.getEntity(String.class);
        
        System.out.println("\nOutput from Server...\n");
        System.out.println(output);
               
        assertEquals(output, expectedOutputJsonStr);
    }
}
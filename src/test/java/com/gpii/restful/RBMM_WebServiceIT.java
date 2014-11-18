package com.gpii.restful;


import com.gpii.jsonld.JsonLDManager;
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
    public void test_runJSONLDRules() 
    {
        System.out.println("\n****************************************");
        System.out.println("* Testing 'runJSONLDRules' web service *");
        System.out.println("****************************************");
        
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
        
        System.out.println("\nWeb service output:\n");
        System.out.println(output);
               
        assertEquals(output, expectedOutputJsonStr);
    }
    
    public void test_detectMultipleSolutionsConflict(){
        
    	System.out.println("\n*****************************************************");
        System.out.println("* Testing 'Detection of Multiple Solutions Conflicts' *");
        System.out.println("*******************************************************");    	
    	
        String inputJsonStr = null;
        String expectedOutputJsonStr = null;
        
        String filepathIN = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/preferences/vladimir.json";
        String filepathExpectedOUT = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/configurations/vladimierOUT.json";
        
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
        
        System.out.println("\nWeb service output:\n");
        System.out.println(output);
               
        assertEquals(output, expectedOutputJsonStr);
    	
    }
    
    public void test_transformOwlToJSONLD() 
    {
        if(JsonLDManager.INTEGRATION_TESTS_INCLUDE_ONTOLOGY_TRANSFORMATION_INTO_JSONLD)
        {
            System.out.println("\n*********************************************************************************************************************************");
            System.out.println("* Testing 'transformOwlToJSONLD' web service                                                                                    *");
            System.out.println("*                                                                                                                               *");
            System.out.println("* Please be patient. This process will take some minutes. The whole solutions ontology is being transformed into JSON-LD format.*");
            System.out.println("* You can disable this test by setting -> JsonLDManager.INTEGRATION_TESTS_INCLUDE_ONTOLOGY_TRANSFORMATION_INTO_JSONLD = false   *");
            System.out.println("*********************************************************************************************************************************");

            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/RBMM/transformOwlToJSONLD");
            ClientResponse response = webResource.get(ClientResponse.class);
            String output = response.getEntity(String.class);

            System.out.println("\nWeb service output:\n");
            System.out.println(output);

            //assertEquals(output, expectedOutputJsonStr);
        }
        else
        {
            System.out.println("\n*****************************************************************************************************************************************************************************");
            System.out.println("* Testing of 'transformOwlToJSONLD' was skipped because it's a time consuming process (JsonLDManager.INTEGRATION_TESTS_INCLUDE_ONTOLOGY_TRANSFORMATION_INTO_JSONLD = false) *");
            System.out.println("*****************************************************************************************************************************************************************************\n");
        }

    }
    
}
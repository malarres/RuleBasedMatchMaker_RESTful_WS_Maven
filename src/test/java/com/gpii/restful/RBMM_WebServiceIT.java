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
        File file = new File(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/testData/input/newInput.json");
        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            inputFileStr = new String(chars);
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
        assertEquals(output, "{\"inferredConfiguration\":{\"gpii-default\":{\"applications\":{\"com.microsoft.windows.desktop\":{\"settings\":{\"http://registry.gpii.net/common/fontSize\":\"15\"},\"active\":\"false\"}}},\"nighttime-at-home\":{\"conditions\":[{\"min\":\"600\",\"max\":\"1800\",\"inputPath\":\"http://registry.gpii.net/conditions/timeOfDay\",\"type\":\"http://gpii.net/common/operators/inRange\"}],\"applications\":{\"com.microsoft.windows.desktop\":{\"settings\":{\"http://registry.gpii.net/common/fontSize\":\"18\"},\"active\":\"false\"}}}}}");
    }
}
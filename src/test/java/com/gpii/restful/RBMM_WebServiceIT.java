package com.gpii.restful;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

public class RBMM_WebServiceIT extends TestCase
{
    public void testWS() 
    {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/RBMM/runJSONLDRules");
        ClientResponse response = webResource.accept("text/plain").post(ClientResponse.class);
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
        System.out.println(output);
        //assertEquals(output, "This is a successful integration test!");
    }
}
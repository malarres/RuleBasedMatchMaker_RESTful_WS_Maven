package com.gpii.restful;

import com.gpii.ontology.OntologyManager;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import sun.nio.cs.StandardCharsets;



@Path("/RBMM")
public class RBMM_WebService
{
    //http://localhost:8080/CLOUD4All_RBMM_Restful_WS/RBMM/runJSONLDRules
    @POST
    @Path("/runJSONLDRules")
    @Consumes("application/json")
    public Response runJSONLDRules(InputStream incomingData)
    {
        OntologyManager.getInstance().debug = "";
        
        //Json-LD test
        try {
            OntologyManager.getInstance().runJSONLDTests(incomingData);
        } catch (IOException ex) {
            OntologyManager.getInstance().debug = OntologyManager.getInstance().debug + "runJSONLDTests EXCEPTION! -> " + ex.toString();
        }
        
        return Response.status(200).entity(OntologyManager.getInstance().debug).build();
    }
}

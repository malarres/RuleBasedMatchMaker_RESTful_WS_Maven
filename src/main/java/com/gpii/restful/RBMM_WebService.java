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
//import sun.nio.cs.StandardCharsets;



@Path("/RBMM")
public class RBMM_WebService
{
    //http://localhost:8080/CLOUD4All_RBMM_Restful_WS/RBMM/runJSONLDRules
    @POST
    @Path("/runJSONLDRules")
    public Response runJSONLDRules()
    {
        OntologyManager.getInstance().debug = "";
        
        //Json-LD test
        OntologyManager.getInstance().runJSONLDTests();
        
        return Response.status(200).entity(OntologyManager.getInstance().debug).build();
    }
    
}

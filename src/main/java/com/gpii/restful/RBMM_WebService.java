package com.gpii.restful;

import com.gpii.jsonld.JsonLDManager;
import com.gpii.ontology.OntologyManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
//import sun.nio.cs.StandardCharsets;
import org.json.JSONException;



@Path("/RBMM")
public class RBMM_WebService
{
    //http://localhost:8080/CLOUD4All_RBMM_Restful_WS/RBMM/runJSONLDRules
    @POST
    @Path("/runJSONLDRules")
    @Consumes("application/json")
    public Response runJSONLDRules(Object tmpInput) throws IOException, JSONException
    {
        OntologyManager.getInstance().debug = "";        
        String inputJsonStr = JsonLDManager.getInstance().gson.toJson(tmpInput);        
        String outputJsonStr = JsonLDManager.getInstance().runJSONLDTests(inputJsonStr);        
        return Response.status(200).entity(outputJsonStr).build();
    }
    
}

package com.gpii.restful;

import com.gpii.jsonld.JsonLDManager;
import com.gpii.jsonld.RBMMInput;
import com.gpii.jsonld.RBMMInputItem;
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
    public Response runJSONLDRules(RBMMInput tmpInput) throws IOException, JSONException
    {
        OntologyManager.getInstance().debug = "";
        String finalResultStr = "";

        for(int i=0; i<tmpInput.getInput().size(); i++)
        {
            RBMMInputItem tmpRBMMInputItem = tmpInput.getInput().get(i);
            String tmpRBMMInputItem_name = tmpRBMMInputItem.getInputName();
            Object tmpRBMMInputItem_body = tmpRBMMInputItem.getInputBody();
            
            //pretty print
            String tmpRBMMInputItem_body_prettyPrint_string = "";
            ObjectMapper mapper = new ObjectMapper();
            try {
                tmpRBMMInputItem_body_prettyPrint_string = mapper.defaultPrettyPrintingWriter().writeValueAsString(tmpRBMMInputItem_body);
            } catch (IOException ex) {
                Logger.getLogger(RBMM_WebService.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
            finalResultStr = finalResultStr + "name: " + tmpRBMMInputItem_name + ",\nbody:\n" + tmpRBMMInputItem_body_prettyPrint_string + "\n\n\n";
            
            if(tmpRBMMInputItem_name.equals("current_np_set"))
                JsonLDManager.getInstance().currentNPSet = tmpRBMMInputItem_body_prettyPrint_string;
            else if(tmpRBMMInputItem_name.equals("current_device_manager_payload"))
                JsonLDManager.getInstance().currentDeviceManagerPayload = tmpRBMMInputItem_body_prettyPrint_string;
        }        
        
        JsonLDManager.getInstance().runJSONLDTests();
        
        return Response.status(200).entity(finalResultStr).build();
    }
    
}

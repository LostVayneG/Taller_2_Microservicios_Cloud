package co.edu.javeriana.ws.rest;

import co.edu.javeriana.ws.rest.Controller.PaseoController;
import co.edu.javeriana.ws.rest.Model.Paseo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
    PaseoController controller = new PaseoController();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("paseos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Paseo> getPaseos() {
        return controller.getPaseos();
    }

    @DELETE
    @Path("paseos/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deletePaseo(@PathParam("id") int id){
        boolean isDeleted = controller.removePaseo(id);
        String result;
        if(isDeleted){
            result = "Paseo "+ id +" was deleted";
        }
        else{
            result = "Paseo with id " + id + " doesnt exist";
        }
        return result;
    }

    @PUT
    @Path("paseos")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Paseo updatePaseo(Paseo paseoToUpdate){
        return controller.updatePaseo(paseoToUpdate);
    }

    @POST
    @Path("paseos")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Paseo createPaseo(Paseo newPaseo){
        newPaseo = controller.createPaseo(newPaseo);
        return newPaseo;
    }
}

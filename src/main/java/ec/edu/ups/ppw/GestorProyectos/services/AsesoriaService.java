package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionAsesorias;
import ec.edu.ups.ppw.GestorProyectos.modelo.Asesoria;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("asesoria")
public class AsesoriaService {

    @Inject
    private GestionAsesorias ga;

    @GET
    @Produces("application/json")
    public List<Asesoria> getListaAsesorias() {
        return ga.getAsesoria();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Asesoria getAsesoriaPorId(@PathParam("id") Long id) {
        Asesoria a = ga.getAsesoriaPorId(id);
        if (a == null) {
            throw new WebApplicationException("Asesoria no encontrada", Response.Status.NOT_FOUND);
        }
        return a;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response crearAsesoria(Asesoria asesoria) {
        if (asesoria == null) {
            throw new WebApplicationException("Datos de asesoria incompletos", Response.Status.BAD_REQUEST);
        }

        if (asesoria.getId() != null) {
            throw new WebApplicationException("No envíes 'id' en POST, se genera automáticamente",
                    Response.Status.BAD_REQUEST);
        }

        ga.crearAsesoria(asesoria);
        return Response.status(Response.Status.CREATED).entity(asesoria).build();
    }

    @PUT
    @Consumes("application/json")
    @Path("{id}")
    public Response actualizarAsesoria(@PathParam("id") Long id, Asesoria asesoria) {
        Asesoria aseExistente = ga.getAsesoriaPorId(id);
        if (aseExistente == null) {
            throw new WebApplicationException("Asesoria no encontrada", Response.Status.NOT_FOUND);
        }

        asesoria.setId(id);
        ga.actualizarAsesoria(asesoria);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarAsesoria(@PathParam("id") Long id) {
        Asesoria existente = ga.getAsesoriaPorId(id);
        if (existente == null) {
            throw new WebApplicationException("Asesoria no encontrada", Response.Status.NOT_FOUND);
        }

        ga.eliminarAsesoria(id);
        return Response.ok().build();
    }
}

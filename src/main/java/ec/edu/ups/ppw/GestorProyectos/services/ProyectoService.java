package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionProyectos;
import ec.edu.ups.ppw.GestorProyectos.modelo.Proyecto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("proyecto")
public class ProyectoService {

    @Inject
    private GestionProyectos gp;

    @GET
    @Produces("application/json")
    public List<Proyecto> getProyectos() {
        return gp.getProyectos();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Proyecto getProyecto(@PathParam("id") Long id) {
        Proyecto p = gp.getProyectoPorId(id);
        if (p == null) {
            throw new WebApplicationException("Proyecto no encontrado", Response.Status.NOT_FOUND);
        }
        return p;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response crearProyecto(Proyecto proyecto) {
        if (proyecto == null) {
            throw new WebApplicationException("Datos requeridos", Response.Status.BAD_REQUEST);
        }

        if (proyecto.getId() != null) {
            throw new WebApplicationException("No envíes 'id' en POST, se genera automáticamente",
                    Response.Status.BAD_REQUEST);
        }

        gp.crearProyecto(proyecto);
        return Response.status(Response.Status.CREATED).entity(proyecto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response actualizarProyecto(@PathParam("id") Long id, Proyecto proyecto) {
        if (gp.getProyectoPorId(id) == null) {
            throw new WebApplicationException("Proyecto no encontrado", Response.Status.NOT_FOUND);
        }
        proyecto.setId(id);
        gp.actualizarProyecto(proyecto);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarProyecto(@PathParam("id") Long id) {
        if (gp.getProyectoPorId(id) == null) {
            throw new WebApplicationException("Proyecto no encontrado", Response.Status.NOT_FOUND);
        }
        gp.eliminarProyecto(id);
        return Response.ok().build();
    }
}

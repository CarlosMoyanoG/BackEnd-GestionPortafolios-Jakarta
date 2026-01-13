package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionProyectos;
import ec.edu.ups.ppw.GestorProyectos.modelo.Proyecto;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
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
    public Proyecto getProyecto(@PathParam("id") int id) {
        Proyecto p = gp.getProyectoPorId(id);
        if (p == null) {
            throw new WebApplicationException("Proyecto no encontrado", Response.Status.NOT_FOUND);
        }
        return p;
    }

    @POST
    @Consumes("application/json")
    public Response crearProyecto(Proyecto proyecto) {
        if (proyecto == null) {
            throw new WebApplicationException("Datos requeridos", Response.Status.BAD_REQUEST);
        }
        if (gp.getProyectoPorId(proyecto.getId()) != null) {
            throw new WebApplicationException("El proyecto ya existe", Response.Status.CONFLICT);
        }
        gp.crearProyecto(proyecto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response actualizarProyecto(@PathParam("id") int id, Proyecto proyecto) {
        if (gp.getProyectoPorId(id) == null) {
            throw new WebApplicationException("Proyecto no encontrado", Response.Status.NOT_FOUND);
        }
        proyecto.setId(id);
        gp.actualizarProyecto(proyecto);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarProyecto(@PathParam("id") int id) {
        if (gp.getProyectoPorId(id) == null) {
            throw new WebApplicationException("Proyecto no encontrado", Response.Status.NOT_FOUND);
        }
        gp.eliminarProyecto(id);
        return Response.ok().build();
    }
}

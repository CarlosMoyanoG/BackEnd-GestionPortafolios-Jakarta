package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionDisponibilidades;
import ec.edu.ups.ppw.GestorProyectos.modelo.Disponibilidad;
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

@Path("disponibilidad")
public class DisponibilidadService {

    @Inject
    private GestionDisponibilidades gd;

    @GET
    @Produces("application/json")
    public List<Disponibilidad> getDisponibilidades() {
        return gd.getDisponibilidades();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Disponibilidad getDisponibilidad(@PathParam("id") int id) {
        Disponibilidad d = gd.getDisponibilidadPorId(id);
        if (d == null) {
            throw new WebApplicationException("Disponibilidad no encontrada", Response.Status.NOT_FOUND);
        }
        return d;
    }

    @POST
    @Consumes("application/json")
    public Response crearDisponibilidad(Disponibilidad disponibilidad) {
        if (disponibilidad == null) {
            throw new WebApplicationException("Datos requeridos", Response.Status.BAD_REQUEST);
        }
        if (gd.getDisponibilidadPorId(disponibilidad.getId()) != null) {
            throw new WebApplicationException("La disponibilidad ya existe", Response.Status.CONFLICT);
        }
        gd.crearDisponibilidad(disponibilidad);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response actualizarDisponibilidad(@PathParam("id") int id, Disponibilidad disponibilidad) {
        if (gd.getDisponibilidadPorId(id) == null) {
            throw new WebApplicationException("Disponibilidad no encontrada", Response.Status.NOT_FOUND);
        }
        disponibilidad.setId(id);
        gd.actualizarDisponibilidad(disponibilidad);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarDisponibilidad(@PathParam("id") int id) {
        if (gd.getDisponibilidadPorId(id) == null) {
            throw new WebApplicationException("Disponibilidad no encontrada", Response.Status.NOT_FOUND);
        }
        gd.eliminarDisponibilidad(id);
        return Response.ok().build();
    }
}

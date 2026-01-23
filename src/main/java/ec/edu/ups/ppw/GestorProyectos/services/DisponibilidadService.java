package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionDisponibilidades;
import ec.edu.ups.ppw.GestorProyectos.modelo.Disponibilidad;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    public Disponibilidad getDisponibilidad(@PathParam("id") Long id) {
        Disponibilidad d = gd.getDisponibilidadPorId(id);
        if (d == null) {
            throw new WebApplicationException("Disponibilidad no encontrada", Response.Status.NOT_FOUND);
        }
        return d;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response crearDisponibilidad(Disponibilidad disponibilidad) {
        if (disponibilidad == null) {
            throw new WebApplicationException("Datos requeridos", Response.Status.BAD_REQUEST);
        }

        if (disponibilidad.getId() != null) {
            throw new WebApplicationException("No envíes 'id' en POST, se genera automáticamente",
                    Response.Status.BAD_REQUEST);
        }

        gd.crearDisponibilidad(disponibilidad);
        return Response.status(Response.Status.CREATED).entity(disponibilidad).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response actualizarDisponibilidad(@PathParam("id") Long id, Disponibilidad disponibilidad) {
        if (gd.getDisponibilidadPorId(id) == null) {
            throw new WebApplicationException("Disponibilidad no encontrada", Response.Status.NOT_FOUND);
        }

        disponibilidad.setId(id);
        gd.actualizarDisponibilidad(disponibilidad);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarDisponibilidad(@PathParam("id") Long id) {
        if (gd.getDisponibilidadPorId(id) == null) {
            throw new WebApplicationException("Disponibilidad no encontrada", Response.Status.NOT_FOUND);
        }

        gd.eliminarDisponibilidad(id);
        return Response.ok().build();
    }
}

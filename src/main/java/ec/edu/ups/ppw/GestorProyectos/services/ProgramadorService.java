package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionProgramadores;
import ec.edu.ups.ppw.GestorProyectos.modelo.Programador;
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

@Path("programador")
public class ProgramadorService {

    @Inject
    private GestionProgramadores gp;

    @GET
    @Produces("application/json")
    public List<Programador> getProgramadores() {
        return gp.getProgramadores();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Programador getProgramador(@PathParam("id") Long id) {
        Programador p = gp.getProgramadorPorId(id);
        if (p == null) {
            throw new WebApplicationException("Programador no encontrado", Response.Status.NOT_FOUND);
        }
        return p;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response crearProgramador(Programador programador) {
        if (programador == null) {
            throw new WebApplicationException("Datos requeridos", Response.Status.BAD_REQUEST);
        }

        if (programador.getId() != null) {
            throw new WebApplicationException("No envíes 'id' en POST, se genera automáticamente",
                    Response.Status.BAD_REQUEST);
        }

        gp.crearProgramador(programador);
        return Response.status(Response.Status.CREATED).entity(programador).build();
    }


    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response actualizarProgramador(@PathParam("id") Long id, Programador programador) {
        if (id == null) {
            throw new WebApplicationException("Id requerido", Response.Status.BAD_REQUEST);
        }

        Programador existente = gp.getProgramadorPorId(id);
        if (existente == null) {
            throw new WebApplicationException("Programador no encontrado", Response.Status.NOT_FOUND);
        }

        programador.setId(id);
        gp.actualizarProgramador(programador);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarProgramador(@PathParam("id") Long id) {
        if (gp.getProgramadorPorId(id) == null) {
            throw new WebApplicationException("Programador no encontrado", Response.Status.NOT_FOUND);
        }
        gp.eliminarProgramador(id);
        return Response.ok().build();
    }
}

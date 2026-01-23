package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionUsuarios;
import ec.edu.ups.ppw.GestorProyectos.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("usuario")
public class UsuarioService {

    @Inject
    private GestionUsuarios gu;

    @GET
    @Produces("application/json")
    public List<Usuario> getUsuarios() {
        return gu.getUsuarios();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Usuario getUsuario(@PathParam("id") Long id) {
        Usuario u = gu.getUsuarioPorId(id);
        if (u == null) {
            throw new WebApplicationException("Usuario no encontrado", Response.Status.NOT_FOUND);
        }
        return u;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response crearUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new WebApplicationException("Datos requeridos", Response.Status.BAD_REQUEST);
        }

        if (usuario.getId() != null) {
            throw new WebApplicationException("No envíes 'id' en POST, se genera automáticamente",
                    Response.Status.BAD_REQUEST);
        }

        gu.crearUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response actualizarUsuario(@PathParam("id") Long id, Usuario usuario) {
        if (gu.getUsuarioPorId(id) == null) {
            throw new WebApplicationException("Usuario no encontrado", Response.Status.NOT_FOUND);
        }

        usuario.setId(id);
        gu.actualizarUsuario(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarUsuario(@PathParam("id") Long id) {
        if (gu.getUsuarioPorId(id) == null) {
            throw new WebApplicationException("Usuario no encontrado", Response.Status.NOT_FOUND);
        }

        gu.eliminarUsuario(id);
        return Response.ok().build();
    }
}

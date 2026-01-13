package ec.edu.ups.ppw.GestorProyectos.services;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.bussines.GestionAsesorias;
import ec.edu.ups.ppw.GestorProyectos.modelo.Asesoria;
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

@Path("asesoria")
public class AsesoriaService {
	
	@Inject
	private GestionAsesorias ga;
	
	@GET //GET es para obtener algo ya creado
	@Produces("application/json")
	public List<Asesoria> getListaPersonas(){
		return ga.getAsesoria();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Asesoria getAsesoriaPorId(@PathParam("id") int id) {
		Asesoria a = ga.getAsesoriaPorId(id);
		if ( a == null ) {
			throw new WebApplicationException("Asesoria no encontrada");
		}
		
		return a;
	}
	
	@POST // POST es para crear algo nuevo
	@Consumes("application/json")
	public Response crearAsesoria(Asesoria asesoria) {
		if( asesoria == null) {
			throw new WebApplicationException("Datos de asesoria incompletos", Response.Status.BAD_REQUEST);
		}
		else if(ga.getAsesoriaPorId(asesoria.getId()) != null){
			throw new WebApplicationException("Asesoria ya existente", Response.Status.CONFLICT);
		}
		else{
			ga.crearAsesoria(asesoria);
			return Response.status(Response.Status.CREATED).build();
		}
	}
	
	@PUT // PUT es para actualizar un objeto que ya existe
    @Consumes("application/json")
    @Path("{id}")
    public Response actualizarAsesoria(@PathParam("id") int id, Asesoria asesoria) {
		
		Asesoria aseExistente = ga.getAsesoriaPorId(id);
		if (aseExistente == null) {
			throw new WebApplicationException("Asesoria no encontrada", Response.Status.NOT_FOUND);
		}
		
		asesoria.setId(id);
		ga.actualizarAsesoria(asesoria);
		return Response.ok().build();
	}
	
	@DELETE // DELETE para eliminar un objeto
    @Path("{id}")
    public Response eliminarAsesoria(@PathParam("id") int id) {
        Asesoria existente = ga.getAsesoriaPorId(id);
        if (existente == null) {
            throw new WebApplicationException("Asesoría no encontrada", Response.Status.NOT_FOUND);
        }

        ga.eliminarAsesoria(id);
        return Response.ok().build();
    }
}

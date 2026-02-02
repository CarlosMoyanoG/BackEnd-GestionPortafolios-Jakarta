package ec.edu.ups.ppw.GestorProyectos.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/health")
public class HealthService{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health() {
        return Response.ok(
            """
            {
              "service": "GestorProyectos Jakarta",
              "status": "UP",
              "message": "Servidor WildFly funcionando correctamente"
            }
            """
        ).build();
    }
}

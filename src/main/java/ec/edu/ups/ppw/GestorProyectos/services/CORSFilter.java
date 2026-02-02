package ec.edu.ups.ppw.GestorProyectos.services;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {

        String allowedOrigin = System.getenv("FRONTEND_ORIGIN");

        if (allowedOrigin == null || allowedOrigin.isBlank()) {
            allowedOrigin = "http://localhost:4200";
        }

        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", allowedOrigin);
        responseContext.getHeaders().putSingle(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization"
        );
        responseContext.getHeaders().putSingle(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD"
        );

        responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
    }
}

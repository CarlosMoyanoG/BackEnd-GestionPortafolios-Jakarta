package ec.edu.ups.ppw.GestorProyectos.services;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class FirebaseAuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String path = requestContext.getUriInfo().getPath();

        // Health check libre
        if (path.endsWith("health")) return;

        // Preflight CORS
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) return;

        String authHeader =
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Token JWT requerido")
                        .build()
            );
            return;
        }

        String token = authHeader.substring(7);

        try {
            FirebaseInitializer.init();

            FirebaseToken decoded =
                FirebaseAuth.getInstance().verifyIdToken(token);

            requestContext.setProperty(
                "firebaseUid",
                decoded.getUid()
            );

        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Token invalido: " + e.getMessage())
                        .build()
            );
        }
    }
}

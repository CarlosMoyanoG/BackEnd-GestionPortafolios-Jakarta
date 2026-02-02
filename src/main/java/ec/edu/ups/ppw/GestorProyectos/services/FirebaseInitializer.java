package ec.edu.ups.ppw.GestorProyectos.services;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static synchronized void init() {
        if (initialized) return;

        try {
            String json = System.getenv("FIREBASE_CREDENTIALS_JSON");

            if (json == null || json.isBlank()) {
                throw new RuntimeException("No se encontró la variable de entorno FIREBASE_CREDENTIALS_JSON");
            }

            GoogleCredentials credentials = GoogleCredentials.fromStream(
                new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
            );

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            initialized = true;

        } catch (Exception e) {
            throw new RuntimeException("Error inicializando Firebase: " + e.getMessage(), e);
        }
    }
}

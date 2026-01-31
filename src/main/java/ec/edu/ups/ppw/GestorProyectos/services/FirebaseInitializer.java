package ec.edu.ups.ppw.GestorProyectos.services;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static synchronized void init() {
        if (initialized) return;

        try {
            String path = System.getProperty("firebase.credentials.path");
            if (path == null || path.isBlank()) {
                throw new RuntimeException("No se encontró firebase.credentials.path");
            }
            
            System.out.println("RUTA FIREBASE: " + System.getProperty("firebase.credentials.path"));

            FileInputStream serviceAccount = new FileInputStream(path);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
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

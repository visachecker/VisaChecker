package main.java;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

//Initializing connection to Firebase
public class Firebase {
    
    final static String databaseUrl = "https://mvcr-database-default-rtdb.europe-west1.firebasedatabase.app";

    public static void initialize() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("./ServiceAccountKeyVisa.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();

        FirebaseApp.initializeApp(options);
    }
}

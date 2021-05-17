package com.hcl.cowin.config;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialisation {

	@PostConstruct
	public void initialize() {

		try {
			FileInputStream serviceAccount = new FileInputStream("./hclcowin_service_account.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			FirebaseApp.initializeApp(options);
			System.out.println("========= initialize database connection ========");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

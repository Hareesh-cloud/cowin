package com.hcl.cowin.config;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service

public class FirebaseInitialisation {

	public void initialize() {

		try {
			FileInputStream serviceAccount = new FileInputStream("./hclcowin_service_account.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			FirebaseApp.initializeApp(options);
			System.out.println("========= initialize connection ========");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

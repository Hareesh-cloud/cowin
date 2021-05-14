package com.hcl.cowin.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.hcl.cowin.config.FirebaseInitialisation;
import com.hcl.dto.UserDto;

@Service
public class ManageUsersService {

	@Autowired
	FirebaseInitialisation manageConnection;
	
	public void createUser(UserDto user) {

		try {
			manageConnection.initialize();
			Firestore dbConnection = FirestoreClient.getFirestore();
			System.out.println("got the connection");
			ApiFuture<WriteResult> collectionApiFuture = dbConnection.collection("TestCollection")
					.document(UUID.randomUUID().toString()).set(user);
			System.out.println("processs");
			String timeStamp = collectionApiFuture.get().getUpdateTime().toString();
			System.out.println("================ Saved ====== " + timeStamp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

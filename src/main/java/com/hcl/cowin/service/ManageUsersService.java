package com.hcl.cowin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.hcl.cowin.constant.ResponseMsg;
import com.hcl.cowin.response.UserResponse;
import com.hcl.dto.UserDto;

@Service
public class ManageUsersService {

	public UserResponse createUser(UserDto user) {
		UserResponse response = new UserResponse();
		try {

			Firestore dbConnection = FirestoreClient.getFirestore();
			ApiFuture<WriteResult> collectionApiFuture = dbConnection.collection("Users")
					.document((user.getDate() + user.getDistrict())).set(user);
			String timeStamp = collectionApiFuture.get().getUpdateTime().toString();
			if (timeStamp != null && !timeStamp.isEmpty()) {
				response.setResponseCode(200);
				response.setResponseMsg(ResponseMsg.SUCCESS);
				response.setEmail(user.getEmail());
				return response;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setResponseCode(500);
			response.setResponseMsg(ResponseMsg.FAILED);
		}

		response.setResponseCode(500);
		response.setResponseMsg(ResponseMsg.FAILED);
		return response;
	}

	public List<UserDto> getAllNotifiableUsers() {

		List<UserDto> users = new ArrayList<>();
		try {

			Firestore dbConnection = FirestoreClient.getFirestore();
			ApiFuture<QuerySnapshot> futurdoc = dbConnection.collection("Users").get();
			QuerySnapshot querySnapshot = futurdoc.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {

				UserDto dto = new UserDto();
				if (document.get("email") != null)
					dto.setEmail(document.get("email").toString());
				if (document.get("district") != null)
					dto.setDistrict(document.get("district").toString());
				if (document.get("payment") != null)
					dto.setPayment(document.get("payment").toString());
				if (document.get("date") != null)
					dto.setDate(document.get("date").toString());
				if (document.get("flag") != null)
					dto.setFlag((Boolean) document.get("flag"));
				if (document.get("type") != null)
					dto.setType(document.get("type").toString());
				users.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(users);
		return users;
	}

	public UserResponse updateUserDetail() {
		UserDto user = new UserDto();

		// Map<String,Object> users = getAllNotifiableUsers();

		return createUser(user);
	}
}

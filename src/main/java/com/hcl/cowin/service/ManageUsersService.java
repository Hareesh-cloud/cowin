package com.hcl.cowin.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.hcl.cowin.constant.ResponseMsg;
import com.hcl.cowin.response.UserResponse;
import com.hcl.dto.Centers;
import com.hcl.dto.CowinObjectDto;
import com.hcl.dto.CowinSession;
import com.hcl.dto.UserDto;

@Service
public class ManageUsersService {

	@Autowired
	private ManageCowinApiService manageCowinApiService;

	@Autowired
	private EmailService emailService;

	public UserResponse createUser(UserDto user) {
		UserResponse response = new UserResponse();
		try {

			Firestore dbConnection = FirestoreClient.getFirestore();
			ApiFuture<WriteResult> collectionApiFuture = dbConnection.collection("Users")
					.document((user.getDate().replaceAll("\\/", "") + user.getDistrict() + user.getEmail().replaceAll("[^A-Za-z0-9]",""))).set(user);
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
			String dateInString = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			System.out.println(dateInString);
			Firestore dbConnection = FirestoreClient.getFirestore();
			ApiFuture<QuerySnapshot> futurdoc = dbConnection.collection("Users").get();
			QuerySnapshot querySnapshot = futurdoc.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {

				if (document.get("flag") != null && !((Boolean) document.get("flag"))
						&& dateInString.equals(document.get("date"))) {
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
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(" user recieved => " + users.size());
		return users;
	}

	public void updateUserDetail() {

		try {
			List<UserDto> users = getAllNotifiableUsers();
			List<CowinObjectDto> filterdCenterDetail = new ArrayList<>();
			for (UserDto userDetail : users) {

				Centers centers = manageCowinApiService.processCowinApi(userDetail.getDistrict(), userDetail.getDate());

				if (centers != null && centers.getCenters().size() > 0) {

					for (CowinObjectDto center : centers.getCenters()) {

						if (userDetail.getPayment().equals(center.getFee_type())) {

							if (center.getSessions() != null)
								for (CowinSession session : center.getSessions()) {

									if (userDetail.getType().equals(session.getMin_age_limit())) {

										filterdCenterDetail.add(center);
									}
								}
						}
					}
				}
				if (filterdCenterDetail != null && filterdCenterDetail.size() > 0) {
					String mailBody = createMailBody(filterdCenterDetail);
					System.out.println("===========> sending to email ========== " + userDetail.getEmail());
					emailService.sendMail(userDetail.getEmail(), "Vaccine Center Update", mailBody);
					filterdCenterDetail.clear();
				}
				Gson gson = new Gson();
				String tmp = gson.toJson(userDetail);
				UserDto myObject = gson.fromJson(tmp, UserDto.class);
				myObject.setFlag(true);
				createUser(myObject);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private String createMailBody(List<CowinObjectDto> filterdCenterDetail) {
		String body = "<tr><td>Address</td><td>Name</td><td>District Name</td><td>Pincode</td></tr>";
		for (CowinObjectDto obj : filterdCenterDetail) {

			body = body + "<tr><td>" + obj.getAddress() + "</td><td>" + obj.getName() + "</td><td>"
					+ obj.getDistrict_name() + "</td><td>" + obj.getPincode() + "</td>";
		}

		body = "<html><head></head><body><table border='2px'>" + body + "</table></body></html>";

		return body;
	}
}

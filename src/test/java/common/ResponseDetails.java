package common;

import io.restassured.response.Response;

public class ResponseDetails

{

	public static Response responseDetails;

	public static void setResponse(Response response) {
		ResponseDetails.responseDetails = response;
	}

	public static Response getResponse() {
		return responseDetails;
	}
}

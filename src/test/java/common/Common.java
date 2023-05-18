package common;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class Common {

	public static Response sending_GetRequest(String endPointURL) {

		Response response = given().contentType(ContentType.JSON).log().all().when().get(endPointURL).prettyPeek()
				.thenReturn();
		ResponseDetails.setResponse(response);
		return response;

	}

	public static Response sending_PostRequest(String endPointURL) {

		Response response = given().contentType(ContentType.JSON).pathParam(endPointURL, endPointURL).log().all().when().post(endPointURL).prettyPeek()
				.thenReturn();
		ResponseDetails.setResponse(response);
		return response;

	}

}

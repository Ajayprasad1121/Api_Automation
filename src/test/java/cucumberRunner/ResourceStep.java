package cucumberRunner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import common.ResponseDetails;

public class ResourceStep {

	@Given("I have the base URL {string}")
	public void setBaseUrl(String url) {
		RestAssured.baseURI = "https://ae.almosafer.com/en/";
	}

	@When("I search for hotels in {string} with the following filters:")
	public void searchHotelsWithFilters(String location, DataTable dataTable) {
		String searchType = dataTable.cell(1, 1).replace("\"", "");
		String checkinDate = dataTable.cell(2, 1).replace("\"", "");
		String checkoutDate = dataTable.cell(3, 1).replace("\"", "");
		String numberOfGuests = dataTable.cell(4, 1).replace("\"", "");
		Response response = given().contentType(ContentType.JSON).pathParam("searchType", searchType)
				.pathParam("location", location).pathParam("checkinDate", checkinDate)
				.pathParam("checkoutDate", checkoutDate).pathParam("numberOfGuests", numberOfGuests).log().all().when()
				.get("{searchType}/{location}/{checkinDate}/{checkoutDate}/{numberOfGuests}").prettyPeek().thenReturn();
		ResponseDetails.setResponse(response);

	}

	@Then("I should receive a response with status code {int}")
	public void i_should_receive_a_response_with_status_code(int statusCode) {
		int response = ResponseDetails.getResponse().statusCode();
		Assert.assertEquals("Status code should be matching", statusCode, response);
	}

	@Then("the response should contain hotels in {string}")
	public void verifyHotelsInLocation(String location) {
		String responseBody = ResponseDetails.getResponse().getBody().asString();
		Assert.assertTrue(responseBody.contains(location));
	}

	@Then("the response should contain hotels in {string} with the specified filters")
	public void verifyHotelsWithFilters(String location) {
		String responseBody = ResponseDetails.getResponse().getBody().asString();
		Assert.assertTrue(responseBody.contains(location));

	}

	@When("I search for flights with the following details:")
	public void searchFlights( DataTable dataTable) {
		String searchType = dataTable.cell(1, 0).replace("\"", "");
		String origin_destination = dataTable.cell(1, 1).replace("\"", "");
		String departureDate = dataTable.cell(1, 2).replace("\"", "");
		String returnDate = dataTable.cell(1, 3).replace("\"", "");
		String cabinClass = dataTable.cell(1, 4).replace("\"", "");
		String passengerDetails = dataTable.cell(1, 5).replace("\"", "");

		Response response = given().contentType(ContentType.JSON).pathParam("searchType", searchType)
				.pathParam("origin_destination", origin_destination).pathParam("departureDate", departureDate)
				.pathParam("returnDate", returnDate).pathParam("cabinClass", cabinClass)
				.pathParam("passengerDetails", passengerDetails).log().all().when()
				.get("{searchType}/{origin_destination}/{departureDate}/{returnDate}/{cabinClass}/{passengerDetails}")
				.prettyPeek().thenReturn();
		ResponseDetails.setResponse(response);
	}
	
	@Then("the response should contain flights departing returning on {string} and {string}")
	public void the_response_should_contain(String origin, String destination) {
		String responseBody = ResponseDetails.getResponse().getBody().asString();
		Assert.assertTrue(responseBody.contains(origin));
		Assert.assertTrue(responseBody.contains(destination));
	 
	}
}

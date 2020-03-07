package io.billie.rest.request;

import io.billie.rest.model.Booking;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingRequest {

	static String baseURI = "https://restful-booker.herokuapp.com/booking";

	static RequestSpecification httpRequest = RestAssured
			.given()
			.baseUri(baseURI)
			.contentType("application/json");

	public static Response createBooking(Booking booking) {
		Response response = httpRequest.body(booking).post();
		return response;
	}
	
	public static Response getBookingById(int id) {
		Response response = httpRequest.pathParam("id", id)
				            .get("/{id}");
		return response;
	}
}

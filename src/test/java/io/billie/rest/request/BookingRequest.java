package io.billie.rest.request;

import io.billie.rest.model.AuthCredential;
import io.billie.rest.model.Booking;
import io.billie.rest.utils.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingRequest {
	
	private static final String BOOKING_ENDPOINT="/booking";
	private static final String AUTH_ENDPOINT="/auth";
	
	
	BaseRequest baseRequest = new BaseRequest();

	public Response createBooking(Booking booking) {
		return baseRequest.create(booking,BOOKING_ENDPOINT); 				
	}

	public Response updateBookingById(int bookingId, Booking booking) {
		;
		String authToken = setupAuth().jsonPath().getString("token");
		return baseRequest.updateById(bookingId, booking,BOOKING_ENDPOINT,authToken);
	}

	public Response getBookingById(int bookingId) {
		return baseRequest.getById(bookingId,BOOKING_ENDPOINT);
	}
	
	public Response deleteBookingById(int bookingId) {
		String authToken = setupAuth().jsonPath().getString("token");
		return baseRequest.deleteById(bookingId, BOOKING_ENDPOINT, authToken);
	}
	
	public Response setupAuth() {
		PropertiesHelper propertiesHelper = new PropertiesHelper();
		AuthCredential auth = new AuthCredential(propertiesHelper.readEnvironmentPropertyFile().getProperty("authuser"),
				propertiesHelper.readEnvironmentPropertyFile().getProperty("authpassword"));
		return baseRequest.getAuthToken(auth,AUTH_ENDPOINT);
	}
}

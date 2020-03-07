package io.billie.rest.request;

import io.billie.rest.model.Booking;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingRequest {

	BaseRequest baseRequest = new BaseRequest("/booking");

	public Response createBooking(Booking booking) {
		return baseRequest.create(booking); 				
	}

	public Response updateBookingById(int bookingId, Booking booking) {
		return baseRequest.updateById(bookingId, booking);
	}

	public Response getBookingById(int bookingId) {
		return baseRequest.getById(bookingId);
	}
}

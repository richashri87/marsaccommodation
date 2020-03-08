package io.billie.rest.test;

import static io.billie.rest.common.CommonAssertions.assertStatusCode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.billie.rest.model.Booking;
import io.billie.rest.model.CreatedBooking;
import io.billie.rest.request.BookingFaker;
import io.billie.rest.request.BookingRequest;
import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
@TestMethodOrder(OrderAnnotation.class)
public class AppTest

{
	static CreatedBooking createdBooking;
	BookingFaker bookingFaker = new BookingFaker();
	BookingRequest bookingRequest = new BookingRequest();

	@Test
	@Order(1)
	public void CreateBooking() {
		
		Booking requestedBooking = bookingFaker.createFakeBookingData();
		
		Response response = bookingRequest.createBooking(requestedBooking);
		createdBooking = response.as(CreatedBooking.class);
		
		assertStatusCode(response.getStatusCode(), 200);
		assertThat("Booking id is null", createdBooking.getBookingid(), notNullValue());
		assertThat("firstname is invalid", createdBooking.getBooking().getFirstname(), equalTo(requestedBooking.getFirstname()));
		assertThat("lastname is invalid", createdBooking.getBooking().getLastname(), equalTo(requestedBooking.getLastname()));
		assertThat("additional needs is invalid", createdBooking.getBooking().getAdditionalneeds(),
				equalTo(requestedBooking.getAdditionalneeds()));
		assertThat("deposit paid is invalid", createdBooking.getBooking().isDepositpaid(),
				equalTo(requestedBooking.isDepositpaid()));

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

	}

	@Test
	@Order(2)
	public void UpdateBookingById() {
		System.out.println(createdBooking);
		Booking requestedBooking = bookingFaker.createFakeBookingData();
		Response response = bookingRequest.updateBookingById(createdBooking.getBookingid(), requestedBooking);
		
		
		createdBooking = response.as(CreatedBooking.class);
		assertStatusCode(response.getStatusCode(), 200);
		assertThat("firstname is invalid", createdBooking.getBooking().getFirstname(), equalTo(requestedBooking.getFirstname()));
		assertThat("lastname is invalid", createdBooking.getBooking().getLastname(), equalTo(requestedBooking.getLastname()));
		assertThat("additional needs is invalid", createdBooking.getBooking().getAdditionalneeds(),
				equalTo(requestedBooking.getAdditionalneeds()));
		assertThat("deposit paid is invalid", createdBooking.getBooking().isDepositpaid(),
				equalTo(requestedBooking.isDepositpaid()));
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body after update is =>  " + responseBody);
	}

	@Test
	@Order(3)
	public void GetBookingById() {
		Response response = bookingRequest.getBookingById(createdBooking.getBookingid());
		
		CreatedBooking retrievedBooking = response.as(CreatedBooking.class);
		assertStatusCode(response.getStatusCode(), 200);
		assertThat("firstname is invalid", createdBooking.getBooking().getFirstname(), equalTo(retrievedBooking.getBooking().getFirstname()));
		assertThat("lastname is invalid", createdBooking.getBooking().getLastname(), equalTo(retrievedBooking.getBooking().getLastname()));
		assertThat("additional needs is invalid", createdBooking.getBooking().getAdditionalneeds(),
				equalTo(retrievedBooking.getBooking().getAdditionalneeds()));
		assertThat("deposit paid is invalid", createdBooking.getBooking().isDepositpaid(),
				equalTo(retrievedBooking.getBooking().isDepositpaid()));
		
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}
	
	@Test
	@Order(4)
	public void DeleteBookingById() {
		Response response = bookingRequest.deleteBookingById(createdBooking.getBookingid());
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}
}

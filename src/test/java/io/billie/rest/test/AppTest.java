package io.billie.rest.test;

import static io.billie.rest.common.CommonAssertions.assertStatusCode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
		
		System.out.println("Created Check in Date"+createdBooking.getBooking().getBookingdates().getCheckin());
		
		assertStatusCode(response.getStatusCode(), 200);
		assertBooking(createdBooking.getBooking(), requestedBooking);
	}

	@Test
	@Order(2)
	public void UpdateBookingById() {
		System.out.println(createdBooking);
		Booking requestedBooking = bookingFaker.createFakeBookingData();
		Response response = bookingRequest.updateBookingById(createdBooking.getBookingid(), requestedBooking);
		
		
		createdBooking.setBooking(response.as(Booking.class));
		assertStatusCode(response.getStatusCode(), 200);
		assertBooking(createdBooking.getBooking(), requestedBooking);
	}

	@Test
	@Order(3)
	public void GetBookingById() {
		Response response = bookingRequest.getBookingById(createdBooking.getBookingid());
		
		Booking retrievedBooking = response.as(Booking.class);
		assertStatusCode(response.getStatusCode(), 200);
		assertBooking(createdBooking.getBooking(), retrievedBooking);	
	}
	
	@Test
	@Order(4)
	public void DeleteBookingById() {
		Response response = bookingRequest.deleteBookingById(createdBooking.getBookingid());
		
		assertStatusCode(response.getStatusCode(), 201);
		
		response = bookingRequest.getBookingById(createdBooking.getBookingid());
		
		assertStatusCode(response.getStatusCode(), 404);
	}
	
	public void assertBooking(Booking actual, Booking expected) {
		
		
		assertThat("firstname is invalid", actual.getFirstname(), equalTo(expected.getFirstname()));
		assertThat("lastname is invalid", actual.getLastname(), equalTo(expected.getLastname()));
		assertThat("deposit paid is invalid", actual.isDepositpaid(),
				equalTo(expected.isDepositpaid()));
		
		assertThat("Check in Date is invalid",actual.getBookingdates().getLocalCheckinDate(),equalTo(expected.getBookingdates().getLocalCheckinDate()));
		assertThat("Check Out Date is invalid",actual.getBookingdates().getLocalCheckoutDate(),equalTo(expected.getBookingdates().getLocalCheckoutDate()));
		
		assertThat("additional needs is invalid", actual.getAdditionalneeds(),
				equalTo(expected.getAdditionalneeds()));
	}
}

package io.billie.rest.test;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.billie.rest.model.Booking;
import io.billie.rest.model.BookingDate;
import io.billie.rest.model.CreatedBooking;
import io.billie.rest.request.BookingFaker;

import static io.billie.rest.request.BookingRequest.createBooking;
import static io.billie.rest.request.BookingRequest.getBookingById;
import static io.billie.rest.common.CommonAssertions.assertStatusCode;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
/**
 * Unit test for simple App.
 */
@TestMethodOrder(OrderAnnotation.class)
public class AppTest 
    
{
	static int bookingId;
	static Booking lastCreatedBooking;

    @Test
    @Order(1)
    public void CreateBooking()
    {   
    	BookingFaker bookingFaker = new BookingFaker();
    	lastCreatedBooking = bookingFaker.createFakeBookingData();
    	Response response = createBooking(lastCreatedBooking);
    	
    	
    	assertStatusCode(response.getStatusCode(),200);
    	
    	CreatedBooking createdBooking = response.as(CreatedBooking.class);
    	assertThat("Booking id is null",createdBooking.getBookingid(),notNullValue());
    	bookingId=createdBooking.getBookingid();
    	
    	Booking actualBooking = createdBooking.getBooking();
    	
    	assertThat("firstname is invalid",actualBooking.getFirstname(),equalTo(lastCreatedBooking.getFirstname()));
    	assertThat("lastname is invalid",actualBooking.getLastname(),equalTo(lastCreatedBooking.getLastname()));
    	assertThat("additional needs is invalid",actualBooking.getAdditionalneeds(),equalTo(lastCreatedBooking.getAdditionalneeds()));
    	assertThat("deposit paid is invalid",actualBooking.isDepositpaid(),equalTo(lastCreatedBooking.isDepositpaid()));
    	
    	String responseBody = response.getBody().asString();
    	System.out.println("Response Body is =>  " + responseBody);
    
    }
    
    @Test
    @Order(2)
    public void GetBookingById() {
    	Response response = getBookingById(bookingId);
    	String responseBody = response.getBody().asString();
    	System.out.println("Response Body is =>  " + responseBody);
    }
}

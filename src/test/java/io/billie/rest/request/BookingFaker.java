package io.billie.rest.request;

import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import io.billie.rest.model.Booking;
import io.billie.rest.model.BookingDate;

public class BookingFaker {
	
	Faker faker = new Faker();
	
	public Booking createFakeBookingData() {
		
		return Booking.builder()
	    		.firstname(faker.name().firstName())
	    		.lastname(faker.name().lastName())
	    		.totalprice(faker.random().nextInt(10))
	            .depositpaid(true)
	            .bookingdates(
	            		BookingDate.builder()
	            			.checkin(faker.date().future(15,TimeUnit.DAYS))
	            			.checkout(faker.date().future(15,TimeUnit.DAYS))
	            			.build())
	            .additionalneeds("No additional Needs")
	            .build();
		
	}
	

}

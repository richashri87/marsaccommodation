package io.billie.rest.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import io.billie.rest.model.Booking;
import io.billie.rest.model.BookingDate;

public class BookingFaker {
	
	Faker faker = new Faker();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");		
	
	
	public Booking createFakeBookingData() {
		
		Date date= faker
					.date()
					.future(15,TimeUnit.DAYS);
		try {
			date = simpleDateFormat.parse(simpleDateFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Booking.builder()
	    		.firstname(faker.name().firstName())
	    		.lastname(faker.name().lastName())
	    		.totalprice(faker.random().nextInt(10))
	            .depositpaid(true)
	            .bookingdates(
	            		BookingDate.builder()
	            			.checkin(date)
	            			.checkout(date)
	            			.build())
	            .additionalneeds("No additional Needs")
	            .build();
		
	}
	

}

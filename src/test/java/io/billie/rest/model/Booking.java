package io.billie.rest.model;

import com.github.javafaker.Number;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
public class Booking extends BaseModel{
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDate bookingdates;
	private String additionalneeds;

}

package io.billie.rest.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseModel{
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDate bookingdates;
	private String additionalneeds;

}

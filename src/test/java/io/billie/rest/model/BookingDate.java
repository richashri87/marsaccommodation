package io.billie.rest.model;

import java.util.Date;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
public class BookingDate extends BaseModel{
	private Date checkin;
	private Date checkout;
	
}

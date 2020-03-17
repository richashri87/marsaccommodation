package io.billie.rest.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDate extends BaseModel{
	private Date checkin;
	private Date checkout;
	
	public LocalDate getLocalCheckinDate() {
		return checkin.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
	}
	
	public LocalDate getLocalCheckoutDate() {
		return checkout.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
	}
}

package io.billie.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedBooking extends BaseModel {
  private int bookingid;
  private Booking booking;
}

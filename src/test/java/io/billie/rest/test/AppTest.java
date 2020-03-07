package io.billie.rest.test;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.billie.rest.model.Booking;
import io.billie.rest.model.BookingDate;
/**
 * Unit test for simple App.
 */
public class AppTest 
    
{

    @Test
    public void CreateBooking()
    {   
    // Specify the base URL to the RESTful web service
    RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
    
    // Get the RequestSpecification of the request that you want to sent
    // to the server. The server is specified by the BaseURI that we have
    // specified in the above step.
    Faker faker = new Faker();
    
    Booking booking = Booking.builder()
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
            .build(); //new Booking();
            
    RequestSpecification httpRequest = RestAssured.given().contentType("application/json").body(booking);
    
    // Make a request to the server by specifying the method Type and the method URL.
    // This will return the Response from the server. Store the response in a variable.
    Response response = httpRequest.request(Method.POST);
    
    // Now let us print the body of the message to see what response
    // we have recieved from the server
    String responseBody = response.getBody().asString();
    System.out.println("Response Body is =>  " + responseBody);
    
    }
}

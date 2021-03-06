package io.billie.rest.request;

import io.billie.rest.utils.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
	private String baseURI;
	private RequestSpecification httpRequest;

	BaseRequest() {
		init();
	}

	void init() {
		PropertiesHelper propertiesHelper = new PropertiesHelper();
		baseURI = propertiesHelper.readEnvironmentPropertyFile().getProperty("baseURI");
		httpRequest = RestAssured.given()
				.baseUri(baseURI)
				.contentType("application/json")
				.filter(new ResponseLoggingFilter())
				.filter(new RequestLoggingFilter());
	}

	public Response create(Object object,String endpoint) {
		return httpRequest.basePath(endpoint).body(object).post();
		
	}

	public Response getById(int id,String endpoint) {
		return httpRequest.basePath(endpoint).pathParam("id", id).get("/{id}");
	}

	public Response updateById(int id, Object object,String endpoint,String authToken) {
		return httpRequest
				.basePath(endpoint)
				.header("Cookie","token="+authToken)
				.pathParam("id", id)
				.body(object)
				.put("/{id}");
		
	}

	public Response deleteById(int id,String endpoint,String authToken) {
		return httpRequest
				.basePath(endpoint)
				.header("Cookie","token="+authToken)
				.pathParam("id", id)
				.delete("/{id}");

	}
	
	public Response getAuthToken(Object object, String endpoint) {
		return httpRequest.basePath(endpoint).body(object).post();
	}
	
	
}

package io.billie.rest.request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
	private String baseURI;
	private RequestSpecification httpRequest;
	private String basePath;

	BaseRequest(String basePath) {
		this.basePath = basePath;
		init();
	}

	void init() {
		baseURI = "https://restful-booker.herokuapp.com";
		httpRequest = RestAssured.given().baseUri(baseURI).contentType("application/json").basePath(basePath);
	}

	public Response create(Object object) {
		Response response = httpRequest.body(object).post();
		return response;
	}

	public Response getById(int id) {
		Response response = httpRequest.pathParam("id", id).get("/{id}");
		return response;
	}

	public Response updateById(int id, Object object) {
		Response response = httpRequest.pathParam("id", id).body(object).put("/{id}");
		return response;
	}

	public Response deleteById(int id) {
		return null;

	}
}

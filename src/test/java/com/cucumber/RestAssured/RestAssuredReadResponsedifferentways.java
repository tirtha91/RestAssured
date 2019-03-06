package com.cucumber.RestAssured;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.InputStream;

public class RestAssuredReadResponsedifferentways {

	//Method to read a response as String
	
	//@Test
	public void testGetResponseAsString() {
		
		String responseAsString = get("http://services.groupkt.com/state/get/IND/all").asString();
		System.out.println("My response is "+responseAsString);
	}
	
	//Method to get response as an input Stream
	
	//@Test
	public void testGetResponseAsInputstream() {
		
		InputStream ResponseStream = get("http://services.groupkt.com/state/get/IND/all").asInputStream();
		System.out.println("The length of the String is "+ResponseStream.toString().length());
	}
	
	// Method to extract details using path
	
	//@Test
	public void testExtraxtDetailsUsingPath() {
		
		String href  = when().
						get("http://jsonplaceholder.typicode.com/photos/1").
					  then().
					  	body("albumId", Matchers.is(1)).extract().path("url");
		System.out.println("The extracted path is "+href);
		
		when().get(href).then().statusCode(200);
		
	}
	
	//Method to extract path in one line
	//@Test
	public void testExtractPathInSingleLine() {
	String thumbnailURL = get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
	
	System.out.println("the thumbnail URL is "+thumbnailURL);
	when().get(thumbnailURL).then().statusCode(200);
	}

	// Extract details as Response for further use
	
	@Test
	public void testExtractResponse() {
		
		Response response = given().get("http://jsonplaceholder.typicode.com/photos/1").
							then().extract().response();
		
		System.out.println("The response type is "+response.contentType());
		System.out.println("The href from response is "+response.path("thumbnailUrl"));
		System.out.println("The response status is "+response.statusCode());
		
	}
}

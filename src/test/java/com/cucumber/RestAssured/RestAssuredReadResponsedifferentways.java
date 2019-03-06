package com.cucumber.RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class RestAssuredReadResponsedifferentways {

	//Method to read a response as String
	
	@Test
	public void testGetResponseAsString() {
		
		String responseAsString = get("http://services.groupkt.com/state/get/IND/all").asString();
		System.out.println("My response is "+responseAsString);
	}
}

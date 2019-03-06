package com.cucumber.RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class RestAssuredPostRequestTest {

	// Test Post Request with dummy value
	
	@Test
	public void testPostRequest()
	{
		given().headers("ID" , "Name").
			param("1", "Tirtha").
			param("2", "Ricky").
		when().
			post("http://api.fonts.com/rest/json/accounts").
		then().
			statusCode(401);
	}
	
}

package com.cucumber.RestAssured;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;


public class RestAssuredSettingRootTest {

	//Basic way to test all parameters	
	@Test
	public void testWithoutRoot() {
		
		given().
			get("http://services.groupkt.com/country/get/iso3code/IND").
		then().
			body("RestResponse.result.name", Matchers.is("India")).
			body("RestResponse.result.alpha3_code", Matchers.is("IND"));
	}
	
	//Recommended way to test  all parameters with setting root
	@Test
	public void testWithRoot() {
		
		given().
			get("http://services.groupkt.com/country/get/iso3code/IND").
		then().
			root("RestResponse.result").
			body("name", Matchers.is("India")).
			body("alpha3_code", Matchers.is("IND"));
	}
	
	// Detaching root in between
	@Test
	public void testDetachingRoot() {
		
		given().
		get("http://services.groupkt.com/country/get/iso3code/IND").
	then().
		root("RestResponse.result").
		body("name", Matchers.is("India")).
		body("alpha3_code", Matchers.is("IND")).
		detachRoot("result").
		body("result.alpha2_code", Matchers.is("IN"));		
	}
}

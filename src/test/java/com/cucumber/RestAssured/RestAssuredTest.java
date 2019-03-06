package com.cucumber.RestAssured;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*; // required for given()

public class RestAssuredTest {

	// simple checking of status code and print complete response
	
	final static String Root_URI = "http://jsonplaceholder.typicode.com/users" ;
	
	//@Test
	public void statusCodetest()
	{
		given().
			get(Root_URI).
		then().statusCode(200).log().all().and().contentType(ContentType.JSON);
	}
	
	//Method to verify hasItems matcher function to verify different users
	
	//@Test
	public void testhasItemsFunctions() {
		
		given().
			get(Root_URI).
		then().body("name", Matchers.hasItems("Leanne Graham" , "Ervin Howell"));
		
		given().
			get(Root_URI).
		then().body("address.zipcode", Matchers.hasItems("90566-7771" , "59590-4157"));
	}
	
	@Test
	public void testXpath() {
		
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
		then().
			body(Matchers.hasXPath("/CUSTOMER/FIRSTNAME"), Matchers.containsString("Sue")).
		and().
			body(Matchers.hasXPath("/CUSTOMER/LASTNAME"), Matchers.containsString("Fuller")).
		and().
			body(Matchers.hasXPath("/CUSTOMER/STREET"), Matchers.containsString("135 Upland Pl.")).
		and().
			body(Matchers.hasXPath("/CUSTOMER/CITY")).log().all();
		
	}
}

package com.cucumber.RestAssured;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.List;

import org.hamcrest.Matchers;

public class RestAssuredGroovtTestFeatures {
	
	// assert the response type
	//@Test
	public void testContentType() {
			
		given().
			get("http://services.groupkt.com/country/search?text=lands").
		then().
			contentType(ContentType.JSON);

		}
	
	//@Test
	public void testPresenceOfElements() {
		
		given().
			get("http://services.groupkt.com/country/search?text=lands").
		then().body("RestResponse.result.name", Matchers.hasItems("Faroe Islands" , "Falkland Islands (Malvinas)"));
	}
	
	// Here we will add all the 'alpha3_code' coming in response
	//@Test
	public void testLengthOfResponse() {
		
		when().
			get("http://services.groupkt.com/country/search?text=lands").
		then().
			body("RestResponse.result.alpha3_code*.length().sum()", Matchers.greaterThan(45));
	}
	
	// To get all attributes as list and verify one attribute using JSON Path
	//@Test
	public void testGetResponseAsList() {
		
		Response response = get("http://services.groupkt.com/country/search?text=lands");
		
//		List<String> ls = from(response).getList("RestResponse.result.name");
		List<String> ls = response.jsonPath().getList("RestResponse.result.name");
		System.out.println("List size is: "+ls.size());
		for (String country : ls) {
			System.out.println("The Country names are: "+country);
				if(country.equals("Solomon Islands")) 
			{
				System.out.println("Found my place");
			};
		}
	}
	
	/** To get response as list only when it mets a certain condition
	   Here the condition is - Country names > 30 Char
	 Groovy has an implicit variable called 'it' which represent the current line item in List 
	**/
	
	@Test
	public void getResponseAsListCondition() {
		
		Response response = get("http://services.groupkt.com/country/search?text=lands");
		List<String> ls = response.jsonPath().getList("RestResponse.result.findAll {it.name.length() > 20}.name");
		System.out.println(ls);
	}
	
	
	
}

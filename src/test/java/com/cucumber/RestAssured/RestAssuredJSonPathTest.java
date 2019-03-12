package com.cucumber.RestAssured;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;

public class RestAssuredJSonPathTest {

	/* The following method is to get list from response using JSON Path 
	 */

	@Test
	public void testJsonPath() {
		Response response = when().
								get("http://jsonplaceholder.typicode.com/photos").
							then().extract().response();
		List<Integer> albumIds = response.jsonPath().getList("albumId");
		System.out.println("The number of albums are :"+albumIds.size());
	}
	
	//Extract details as String and then further results using JSON Path
	
	@Test
	public void testJSONPath2() {
		
		String json = when().
						get("http://jsonplaceholder.typicode.com/photos").
					  then().extract().asString();
	}
	}

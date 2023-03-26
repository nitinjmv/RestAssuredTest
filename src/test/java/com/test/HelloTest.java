package com.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class HelloTest {

	private String endpoint = "https://jsonplaceholder.typicode.com/posts";

	@Test
	private void printAPIResponse() {

		given().when().get(endpoint).then().log().all();
	}

	private void testAPIStatusCode() {
		given().when().get(endpoint).then().assertThat().statusCode(200);
	}

}

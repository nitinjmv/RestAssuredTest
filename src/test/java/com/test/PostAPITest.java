package com.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.StringJoiner;

public class PostAPITest {

	private String endpoint = "https://jsonplaceholder.typicode.com/posts";

	@Test
	private void printAPIResponse() {

		given().when().get(endpoint).then().log().all();
	}

	@Test
	private void testAPIStatusCode() {
		given().when().get(endpoint).then().assertThat().statusCode(200);
	}
	
	@Test
	private void testPostById() {
		String postId = "1";
		StringJoiner sj = new StringJoiner("/");
		sj.add(endpoint).add(postId);
		
		given()
		.when()
		.get(sj.toString())
		.then()
		.body("id", is(1)); // is() because id is coming as  int in response
		//.body("title", containsString("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")); // to compare string
	}
	
	

}

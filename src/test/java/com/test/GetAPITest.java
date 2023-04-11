package com.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAPITest
{
    private String endpoint = "https://jsonplaceholder.typicode.com/posts/1";

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
	/*	String postId = "1";
		StringJoiner sj = new StringJoiner("/");
		sj.add(endpoint).add(postId);
		 */
        //endpoint=endpoint+"/1";

        given()
                .when()
                //.get(sj.toString())
                .get(endpoint);
        //.then()
        //.body("id", is(1)); // is() because id is coming as  int in response
        //.body("title", containsString("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")); // to compare string
    }

}

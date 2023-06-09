package com.test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.test.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsersAPITest {

	RequestSpecification request;

	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "http://localhost:3000";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	@Test
	public void getUsersTest() {
		Response response = request.get("/users");
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void createUsersTest() {
		User user = buildUserObject();
		request.body(user);
		Response response = request.post("/users");
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals("Leanne Graham", response.jsonPath().getString("name"));
	}

	@Test
	public void updateUsersTest() {
		User user = buildUpdatedUserObject();
		request.body(user);
		Response response = request.put("/users/123");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals("123", response.jsonPath().getString("id"));
        Assert.assertEquals("UpdatedSincere@april.biz", response.jsonPath().getString("email"));

	}

	@Test
	public void deleteUsersTest() {
		int statusCode = request.delete("/users/123")
				.thenReturn()
				.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	private User buildUserObject() {
		return User.builder().id("123").name("Leanne Graham")
				.email("Sincere@april.biz").build();
	}

	private User buildUpdatedUserObject() {
		return User.builder().id("123").name("Leanne Graham")
				.email("UpdatedSincere@april.biz").build();
	}

}

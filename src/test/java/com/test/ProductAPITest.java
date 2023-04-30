package com.test;

import com.test.model.Product;
import com.test.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProductAPITest {

	RequestSpecification request;

	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "http://localhost:3000";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	@Test
	public void getProductsTest() {
		Response response = request.get("/products");
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void createProductsTest() {
		Product product = buildProductObject();
		request.body(product);
		Response response = request.post("/products");
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals("laptop", response.jsonPath().getString("item"));
	}

	@Test
	public void updateProductsTest() {
		Product product = buildUpdatedProductObject();
		request.body(product);
		Response response = request.put("/products/100");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals("100", response.jsonPath().getString("id"));
		Assert.assertEquals("laptop1", response.jsonPath().getString("item"));
		Assert.assertEquals("12013", response.jsonPath().getString("price"));

	}

	@Test
	public void deleteProductsTest() {
		int statusCode = request.delete("/products/100")
				.thenReturn()
				.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	private Product buildProductObject() {
		return Product.builder().id("100").item("laptop")
				.price("12012").build();
	}

	private Product buildUpdatedProductObject() {
		return Product.builder().id("100").item("laptop1")
				.price("12013").build();
	}

}

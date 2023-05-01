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

	@Test(priority=1)
	public void getProductsTest() {
		Response response = request.get("/products");
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority=2)
	public void createProductsTest() {
		Product product = buildProductObject();
		request.body(product);
		Response response = request.post("/products");
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals("laptop", response.jsonPath().getString("item"));
	}

	@Test(priority=3)
	public void updateProductsTest() {
		Product product = buildUpdatedProductObject();
		request.body(product);
		Response response = request.put("/products/100");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals("100", response.jsonPath().getString("id"));
		Assert.assertEquals("laptop1", response.jsonPath().getString("item"));
		Assert.assertEquals("12013", response.jsonPath().getString("price"));

	}

	@Test(priority=4)
	public void patchProductsTest() {
		Product product = buildPatchProductObject();
		request.body(product);
		Response response = request.put("/products/100");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals("100", response.jsonPath().getString("id"));
		Assert.assertEquals("laptop1", response.jsonPath().getString("item"));
		Assert.assertEquals("12014", response.jsonPath().getString("price"));

	}

	@Test(priority=5)
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
		return Product.builder().id("101").item("laptop1")
				.price("12013").build();
	}

	private Product buildPatchProductObject() {
		return Product.builder().id("101").item("laptop1")
				.price("12014").build();
	}

}

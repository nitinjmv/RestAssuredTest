package com.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class TestUsers {
    /*
    GET API
    1.Total number of users.
    2.Id should not be null,blank
    3.Email validation
    4.Phone number validation it should be numeric
    5.Website url validation
    6.
    7.
    8....
    */
    static Response response = null;

    @BeforeTest
    public void setup() {
        String endpoint = "https://jsonplaceholder.typicode.com/users";
        response = given().when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }

    @Test
    public static void validateNumberOfRecords() {
        List<String> jsonResponse = response.jsonPath().getList("$");
        // System.out.println(jsonResponse);
        // System.out.println("/");
        System.out.println(jsonResponse.size());
        Assert.assertEquals(jsonResponse.size(), 10);
    }

    @Test
    public static void validateNumberOfUsers() {
        List<String> jsonResponse = response.jsonPath().getList("username");
        for (int id = 0; id < jsonResponse.size(); id++) {
            //     System.out.println("json = "+jsonResponse.get(id).toString());
            Pattern VALID_username_REGEX =
                    Pattern.compile("[A-Z]", Pattern.CASE_INSENSITIVE);
            Matcher m = VALID_username_REGEX.matcher(jsonResponse.get(id).toString());
            System.out.println("Users are validated:    " + jsonResponse.get(id).toString());
            Assert.assertTrue(m.find(), "Users are validated correctly");

        }
    }


    @Test
    public static void emptyCheck() {
        List<Integer> jsonResponse = response.jsonPath().getList("id");
        for (int i = 1; i <= jsonResponse.size(); i++) {
            System.out.println("id = " + i);
            if (i != 0) {
                Assert.assertNotEquals(jsonResponse.size(), 0);
            }
        }
    }

    @Test
    public static void validateEmail() {
        List<String> jsonResponse = response.jsonPath().getList("email");
        for (int id = 0; id < jsonResponse.size(); id++) {
            //     System.out.println("json = "+jsonResponse.get(id).toString());
            Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(jsonResponse.get(id).toString());
            System.out.println("Email ids are validated:    " + jsonResponse.get(id).toString());
            Assert.assertTrue(m.find(), "Email are validated correctly");

        }
    }

    @Test
    public static void validatePhone() {
        List<String> jsonResponse = response.jsonPath().getList("phone");
        for (int id = 0; id < jsonResponse.size(); id++) {
            //  System.out.println("json = "+jsonResponse.get(id).toString());
            Pattern VALID_PHONE_REGEX =
                    Pattern.compile("[0-9A-Z(-)]", Pattern.CASE_INSENSITIVE);
            Matcher m = VALID_PHONE_REGEX.matcher(jsonResponse.get(id).toString());
            System.out.println("Phone numbers are validated:    " + jsonResponse.get(id).toString());
            Assert.assertTrue(m.find(), "Phone are validated correctly");
        }
    }

    @Test
    public static void validateWebsiteURL() {
        List<String> jsonResponse = response.jsonPath().getList("website");
        for (int id = 0; id < jsonResponse.size(); id++) {
            //   System.out.println("json = "+jsonResponse.get(id).toString());
            Pattern VALID_PHONE_REGEX =
                    Pattern.compile("[0-9A-Z.-.)]", Pattern.CASE_INSENSITIVE);
            Matcher m = VALID_PHONE_REGEX.matcher(jsonResponse.get(id).toString());
            System.out.println("Website are validated:    " + jsonResponse.get(id).toString());
            Assert.assertTrue(m.find(), "websites are validated correctly");
        }
    }
}
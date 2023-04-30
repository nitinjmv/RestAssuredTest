package com.test;

import com.file.ReusableMethods;
import com.file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestUsers1 {

    public static void main(String[] args)
    {
        //content of the file to string ->content of file can be converted into Byte->Byte data to string
        RestAssured.baseURI= "https://jsonplaceholder.typicode.com/users/";


    }





}

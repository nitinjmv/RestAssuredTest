package com.file;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson

{
    @Test
    public void addBook(){
        RestAssured.baseURI="http://216.10.245.166";
        String response=given().header("Content-Type","application/json").body(payload.Addbook("asdf","6789")).
                when().post("/Library/Addbook.php").
                then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js=ReusableMethods.rawToJason(response);
        String id=js.get("ID");
        System.out.println(id);

        //delete book


    }

}

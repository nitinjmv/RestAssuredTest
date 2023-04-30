package com.file;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class DynamicJson

{
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";

        String response=given().header("Content-Type","application/json")
                .body(payload.Addbook(isbn,aisle)).
                when().post("/Library/Addbook.php").
                then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js=ReusableMethods.rawToJason(response);
        String id=js.get("ID");
        System.out.println(id);
        //delete book
    }
       @DataProvider(name = "BooksData")
       public Object[][] getData(){

         return new Object[][] { {"amcd1","31034"},{"l9ocd","19a39"},{"kebkd","13038"} };
       }
}

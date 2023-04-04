package com.test;
import com.files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//import org.testng.Assert;
//import org.testng.annotations.Test;
import com.files.ReusableMethod;
import com.files.payload;
import org.testng.Assert;

//Add place-> update place with new address->Get place to validate if new address is present in response
public class Basic {

    public static void main(String[] args)
     {
         RestAssured.baseURI= "https://rahulshettyacademy.com";
          String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                 .body(payload.AddPlace()).when().post("maps/api/place/add/json")
                 .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                  .header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
                  System.out.println(response);
                  JsonPath js=new JsonPath(response);//for parsing json
                  String placeId=js.getString("place_id");
                  System.out.println(placeId);

                 // update place
                 String newAddress = "Summer Walk, Africa";
                 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                         .body("{\n" +
                                 "    \"place_id\": \""+placeId+"\",\r\n" +
                                 "    \"address\": \""+newAddress+"\",\r\n" +
                                 "    \"key\": \"qaclick123\"\n" +
                                 "}").when().put("maps/api/place/update/json").then()
                         .assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

                 //get place
                 String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
                 .queryParam("place_id",placeId)
                 .when().get("maps/api/place/get/json")
                 .then().assertThat().log().all().statusCode(200).extract().response().asString();
                  //JsonPath js1=new JsonPath(getPlaceResponse);
                  JsonPath js1= ReusableMethod.rawToJason(getPlaceResponse);
                  String actualAddress =js1.getString("address");
                  System.out.println(actualAddress);
                  Assert.assertEquals(actualAddress,newAddress);
                  //cucumber Junit testng



     }



}

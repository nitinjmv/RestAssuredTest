package com.files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {
    public static JsonPath rawToJason(String response){
        JsonPath js1=new JsonPath(response);
        return js1;

    }
}

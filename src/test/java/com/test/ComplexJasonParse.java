package com.test;

import io.restassured.path.json.JsonPath;
import com.file.payload;
import com.file.userpayload;

public class ComplexJasonParse {

    public static void main(String[] args) {

        JsonPath js=new JsonPath(payload.CoursePrice());
        //print no of courses returned by API
        int count=	js.getInt("courses.size()");
        System.out.println(count);

        //Print purchase amount
        int totalAmount= js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //Print Title of the first course
        String titleFirstCourse=js.get("courses[2].title");
        System.out.println(titleFirstCourse);

        //Print all courses title and their respective prices
        for(int i=0;i<count;i++)
        {
            String courseTitles=js.get("courses["+i+"].title");
            System.out.println(js.get("courses["+i+"].price").toString());
            System.out.println(courseTitles);
        }

        //Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course");
        for(int i=0;i<count;i++)
        {
            String courseTitles=js.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA"))
            {
                int copies=js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }}

        JsonPath js1=new JsonPath(userpayload.getusers());
        //print no of id returned by API
        int count1=	js1.getInt("id.size()");
        System.out.println("No of ids are displaying as  " +count1);

        //print no of id returned by API
        int tusres=	js1.getInt("username.size()");
        System.out.println("No of users are displaying as  " +tusres);







    }


}

package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payloads.User;
import  io.restassured.response.Response;
import  io.restassured.http.ContentType;
import java.util.*;


// Created for o=perform CRUD Operations


public class UserEndPoints_properties 
{
	public static ResourceBundle get_url()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	public static Response create_user(User payload)
	{
		String post_url = get_url().getString("post_url");
		Response response= given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON).
				body(payload).
				when().
				post(post_url);// we are using this url from Routes Class 
		return response;		
	}
	public static Response get_user(String username)
	{
		String geturl = get_url().getString("get_url");
		Response response= given().log().all().
				pathParam("username", username).		
				when().
				get(geturl);// we are using this url from Routes Class 
		return response;		
	}
	public static Response update_user(String username,User payload)
	{
		String puturl = get_url().getString("update_url");
		Response response= given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON).
				body(payload).
				pathParam("username", username).
				when().
				put(puturl);// we are using this url from Routes Class 
		return response;		
	}
	public static Response delete_user(String username)
	{
		String deleteurl = get_url().getString("delete_url");
		Response response= given().log().all().
				pathParam("username", username).				
				when().
				delete(deleteurl);// we are using this url from Routes Class 
		return response;		
	}
}

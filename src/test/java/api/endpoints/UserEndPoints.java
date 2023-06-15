package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payloads.User;
import  io.restassured.response.Response;
import  io.restassured.http.ContentType;


// Created for o=perform CRUD Operations


public class UserEndPoints 
{

	public static Response create_user(User payload)
	{
		Response response= given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON).
				body(payload).
				when().
				post(Routes.post_url);// we are using this url from Routes Class 
		return response;		
	}
	public static Response get_user(String username)
	{
		Response response= given().log().all().
				pathParam("username", username).		
				when().
				get(Routes.get_url);// we are using this url from Routes Class 
		return response;		
	}
	public static Response update_user(String username,User payload)
	{
		Response response= given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON).
				body(payload).
				pathParam("username", username).
				when().
				put(Routes.update_url);// we are using this url from Routes Class 
		return response;		
	}
	public static Response delete_user(String username)
	{
		Response response= given().log().all().
				pathParam("username", username).				
				when().
				delete(Routes.delete_url);// we are using this url from Routes Class 
		return response;		
	}
}

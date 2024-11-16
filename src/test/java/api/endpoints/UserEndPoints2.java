package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;

public class UserEndPoints2 
{
	//Method to get url's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("routes");  //Load properties file
	return routes;
	}
	
	//UserEndPoints.java file
	//Created to perform CRUD operations

	public static Response createUser(User Payload)
	{
		String post_url = getURL().getString("post_url");
	Response res=
	given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(Payload)

	.when()
	.post(post_url);

	return res;
	}


	public static Response readUser1(String userName)
	{
		String get_url = getURL().getString("get_url");
	Response res=
	given()
	.pathParam("username",userName)

	.when()
	.get(get_url);

	return res;

	}
	
	public static Response updateUser(String userName,User Payload) //We need 2 param
	{
		String update_url = getURL().getString("update_url");
	Response res=
	given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username",userName)
	.body(Payload)

	.when()
	.put(update_url);

	return res;
	}


	public static Response deleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");
	Response res=
	given()
	.pathParam("username",userName)

	.when()
	.delete(delete_url);
	
	// Debugging output

	System.out.println("DELETE Response Status: " + res.getStatusCode());
    System.out.println("DELETE Response Body: " + res.getBody().asString());
	return res;
	}

}

package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import api.payload.User;

public class UserEndPoints 
{
	//UserEndPoints.java file
	//Created to perform CRUD operations

	public static Response createUser(User Payload)
	{
	Response res=
	given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(Payload)

	.when()
	.post(Routes.post_url);

	return res;
	}


	public static Response readUser1(String userName)
	{
	Response res=
	given()
	.pathParam("username",userName)

	.when()
	.get(Routes.get_url);

	return res;

	}
	
	public static Response updateUser(String userName,User Payload) //We need 2 param
	{
	Response res=
	given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username",userName)
	.body(Payload)

	.when()
	.put(Routes.update_url);

	return res;
	}


	public static Response deleteUser(String userName)
	{
	Response res=
	given()
	.pathParam("username",userName)

	.when()
	.delete(Routes.delete_url);
	
	// Debugging output

	System.out.println("DELETE Response Status: " + res.getStatusCode());
    System.out.println("DELETE Response Body: " + res.getBody().asString());
	return res;
	}

}

package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests 
{
	//if the data provider is not present in same class we have to mention the class name as well
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void tesPostUser(String userID, String userName, String fname, String lname, String useremail, String password, String phone) throws InterruptedException
	{
	    // Create User payload
	    User userPayload = new User();
	    
	    Thread.sleep(3000);
	    userPayload.setId(Integer.parseInt(userID));
	    userPayload.setUsername(userName);
	    userPayload.setFirstname(fname);
	    userPayload.setLastname(lname);
	    userPayload.setEmail(useremail);
	    userPayload.setPassword(password);
	    userPayload.setPhone(phone);


	Response res=UserEndPoints.createUser(userPayload);
	System.out.println("Create User Status: " + res.getStatusCode());
	Assert.assertEquals(res.getStatusCode(),200);
	}

	//@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username)
	{
	Response res=UserEndPoints.deleteUser(username);
	Assert.assertEquals(res.getStatusCode(),200);

	}

}

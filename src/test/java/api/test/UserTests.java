package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests 
{
	Faker faker;	//Variable
	User userPayload;	//Variable
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
	faker=new Faker();
	userPayload=new User();
	
	
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstname(faker.name().firstName());
	userPayload.setLastname(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	userPayload.setPassword(faker.internet().password(5,10));
	
	//logs
	logger= LogManager.getLogger(this.getClass());
	}


	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******Creating User**********");
	Response res=UserEndPoints.createUser(userPayload);
	res.then().log().all();
	Assert.assertEquals(res.getStatusCode(),200);
	
	logger.info("****** User created**********");
	}
	

@Test(priority=2)
public void testGetUserByName()

{
	logger.info("******Fetching UserByName**********");
Response res=UserEndPoints.readUser1(this.userPayload.getUsername());
res.then().log().all();

//2 ways to get statuscode
//res.statusCode(200);
Assert.assertEquals(res.getStatusCode(),200);
logger.info("******Fetched details UserByName**********");
}

@Test(priority=3)
public void testUpdateUserByName()

{
	logger.info("******Update UserByName**********");
//Update data using payload
	userPayload.setFirstname(faker.name().firstName());
	userPayload.setLastname(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());

Response res=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
res.then().log().body();

//2 ways to get statuscode
//res.statusCode(200);
res.then().log().body();
Assert.assertEquals(res.getStatusCode(),200);

//Checking data after update 
Response resAfterUpdate=UserEndPoints.readUser1(this.userPayload.getUsername());
Assert.assertEquals(resAfterUpdate.getStatusCode(),200);
logger.info("******Updated UserByName**********");
}


@Test(priority=4)
public void testDeleteUserByName()
{
	logger.info("******Delete UserByName**********");
Response res=UserEndPoints.deleteUser(this.userPayload.getUsername());
Assert.assertEquals(res.getStatusCode(),200);
logger.info("******Deleted UserByName**********");
}
}

package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class User_test
{
	Faker fake;
	User user_payload;
	public Logger logger;
	
	
	@BeforeClass	
	public void setup()
	{
		fake = new Faker();
		user_payload = new User();
		
		user_payload.setId(fake.idNumber().hashCode());
		user_payload.setUsername(fake.name().username());
		user_payload.setFirst_name(fake.name().firstName());
		user_payload.setLast_name(fake.name().lastName());
		user_payload.setEmail(fake.internet().safeEmailAddress());
		user_payload.setPassword(fake.internet().password(5, 10));
		user_payload.setPhone(fake.phoneNumber().cellPhone());
		
		//Applying logs
		logger = LogManager.getLogger(this.getClass());

	}
	@Test(priority=1)
	public void test_post_user()
	{
		logger.info("----------Posting the user------------ ");
		Response response=UserEndPoints.create_user(user_payload);
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("----------User Created------------ ");
	}
	@Test(priority=2)
	public void test_get_user()
	{
		logger.info("----------get the user------------ ");
		Response response = UserEndPoints.get_user(this.user_payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("----------User achiverd------------ ");
		
	}
	@Test(priority=3 )
	public void test_update_user()
	{
		//update data using payload
		logger.info("----------Updateing data------------ ");
		user_payload.setFirst_name(fake.name().firstName());
		user_payload.setLast_name(fake.name().lastName());
		user_payload.setEmail(fake.internet().safeEmailAddress());
		
		Response response= UserEndPoints.update_user(this.user_payload.getUsername(),user_payload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("---------After Updattion------------ ");
		
		// cheking data after update
		logger.info("----------After Updattion----------- ");
		Response response_after_update = UserEndPoints.get_user(this.user_payload.getUsername());
		response_after_update.then().log().all();
		Assert.assertEquals(response_after_update.getStatusCode(), 200);
		logger.info("----------After Updation Getting user------------ ");
		}
	@Test(priority =4)
	public void test_delete_user()
	{
		logger.info("----------Deleting user------------ ");
		Response response = UserEndPoints.delete_user(this.user_payload.getUsername());
		
		response .then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("----------USer Deleted------------ ");
	}

}

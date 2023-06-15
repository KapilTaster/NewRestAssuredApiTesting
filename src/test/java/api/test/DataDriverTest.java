package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import io.restassured.response.Response;
import  api.utilities.DataProviders;
import api.payloads.User;

public class DataDriverTest 
{
	@Test(priority=1,dataProvider = "Data",dataProviderClass=DataProviders.class)
	public void mulitple_post_request(String UserId,String UserName,String FristName,String LastName,String Email,String password,String Phone)
	{
		User user_payload = new User();

		user_payload.setId(Integer.parseInt(UserId));
		user_payload.setUsername(UserName);
		user_payload.setFirst_name(FristName);
		user_payload.setLast_name(LastName);
		user_payload.setEmail(Email);
		user_payload.setPassword(password);
		user_payload.setPhone(Phone);

		Response response=UserEndPoints.create_user(user_payload);
		response.then();

		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority =2,dataProvider = "UserNames",dataProviderClass=DataProviders.class)
	public void delete_request(String username)
	{
		Response response = UserEndPoints.delete_user(username);

		response .then();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}

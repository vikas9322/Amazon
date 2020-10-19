package com.vikas.testcases;

import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vikas.base.TestBase;
import com.vikas.base.utils.TestUtils;
import com.vikas.pages.HomePage;

public class HomePageTest extends TestBase {

	HomePage homePage;
	
	TestUtils utils= new TestUtils();
	JSONObject adddetails;


	@BeforeClass
	public void beforeClass() throws Exception {

		InputStream details= null;
		try {
			String datafileNmae = "data\\addressDetails.json";
			details = getClass().getClassLoader().getResourceAsStream(datafileNmae);
			JSONTokener tokner = new JSONTokener(details);
			adddetails = new JSONObject(tokner);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (details != null) {
				details.close();

			}
		}

	}

	@AfterClass
	public void afterClass() {
		quitDriver();
	}

	@BeforeMethod
	public void beforeMethod() {

		homePage = new HomePage();
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void addNewAddressTest() {
		
		utils.log("************Starting addNewAddressTest *************************");

		homePage.newAddress();
		utils.log("************filling address details*************************");
		homePage.addAddressDetails(adddetails.getJSONObject("addNewAddressTest").getString("name"),
				
				adddetails.getJSONObject("addNewAddressTest").getString("mobileNumber"),
				adddetails.getJSONObject("addNewAddressTest").getString("pinCode"),
				adddetails.getJSONObject("addNewAddressTest").getString("addressline1"),
				adddetails.getJSONObject("addNewAddressTest").getString("addressLine2"),
				adddetails.getJSONObject("addNewAddressTest").getString("addressLine3"),
				adddetails.getJSONObject("addNewAddressTest").getString("City"),
				adddetails.getJSONObject("addNewAddressTest").getString("txt"));
		homePage.mainPage();
		String expectedTitle = getStrings().get("Home_Title");
		utils.log("expectedTitle Title is---"+expectedTitle);
		String actualTitle = homePage.getTitle() + "cdc";
		utils.log("actualTitle Title is---"+actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Some Thing is wrong with the title -- ");
		
		utils.log("************addNewAddressTest passed*************************");
																									

	}

}

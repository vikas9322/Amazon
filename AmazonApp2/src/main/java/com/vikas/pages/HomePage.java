package com.vikas.pages;

import com.vikas.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends TestBase{
	
	
	
	
	
		@AndroidFindBy ( id = "in.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon") private MobileElement menu;
	
		@AndroidFindBy (xpath = "//android.widget.TextView[contains(@text,'Your Account')]") private MobileElement yourAccount;
		
		@AndroidFindBy (xpath = "//android.widget.TextView[contains (@text,'Your Addresses')]") private MobileElement yourAddress;
		
		@AndroidFindBy (xpath = "//android.widget.TextView[contains (@text,'new address')]") private MobileElement newAddress;
		
	//	@AndroidFindBy (className = "(android.widget.EditText)")  private ArrayList<MobileElement> name;
		@AndroidFindBy (xpath = "(//android.widget.EditText)[1]")  private MobileElement name;
		
		@AndroidFindBy (xpath = "(//android.widget.EditText)[2]")  private MobileElement mobNumber;
		
		@AndroidFindBy (xpath = "(//android.widget.EditText)[3]")  private MobileElement pincode;
		
		@AndroidFindBy (xpath = "(//android.widget.EditText)[4]")  private MobileElement addLine1;
		
		@AndroidFindBy (xpath = "(//android.widget.EditText)[5]")  private MobileElement addLine2;
		
		@AndroidFindBy (xpath = "(//android.widget.EditText)[6]")  private MobileElement adddLine3;
		
		@AndroidFindBy (xpath = "(//android.widget.EditText)[7]")  private MobileElement city;
		
		@AndroidFindBy (xpath = "//android.widget.Spinner[contains (@text,'Select an Address Type')]") private MobileElement addressType;
		
		@AndroidFindBy (xpath = "//android.widget.TextView[contains (@text,'Home')]") private MobileElement selectAddressType;
		
		@AndroidFindBy (xpath = "//android.widget.Button[contains (@text,'Add address')]") private MobileElement addAddress;
		
		@AndroidFindBy (accessibility = "in.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon") private MobileElement menu2;
		
		@AndroidFindBy (xpath = "//android.widget.TextView[contains(@text,'Home')]") private MobileElement home;
		
		@AndroidFindBy ( id = "in.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo") private MobileElement amazomLogo;
		
		@AndroidFindBy ( id = "in.amazon.mShop.android.shopping:id/rs_search_src_text") private MobileElement search;
		
		public MenuPage menuPage = new MenuPage();
		

		
		public HomePage newAddress()
		{

			menuPage.clickOnMenuButton();
			click(yourAccount);
			click(yourAddress);
			click(newAddress);
			
			return this;
			}
		
		public HomePage addAddressDetails(String userName, String mobileNumber, String pinCode, String addressline1, String addressLine2, String addressLine3, String City,String txt)
		{
			sendKeys(name, userName);
			sendKeys(mobNumber, mobileNumber);
			sendKeys(pincode, pinCode);
			sendKeys(addLine1, addressline1);
			sendKeys(addLine2, addressLine2);
			sendKeys(adddLine3, addressLine3);
			sendKeys(city,City);
			click(addressType);
			click(selectAddressType);
			scrollDown(txt);
			//click(addAddress);
			return this;
		}
		
		
		public HomePage mainPage()
		{
			click(menu);
			click(home);
			
			return this;
		}
		
		public String getTitle()
		{
		
			waitForVisibility(search);
			return getText(search, "title");
			
		}

		
		
		
		
	

}

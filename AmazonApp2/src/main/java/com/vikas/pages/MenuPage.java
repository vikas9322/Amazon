package com.vikas.pages;

import com.vikas.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MenuPage extends TestBase{
	
	//TestBase base;
	
	
	
		@AndroidFindBy ( id = "in.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon") private MobileElement menu;
		
		public HomePage clickOnMenuButton()
		{

			click(menu);
			
			return new HomePage();
			}
		
		
		
	
		
		
		
		
		
		
	

}

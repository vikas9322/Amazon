package com.vikas.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.vikas.base.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;

public class TestBase {

	public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	public static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	public static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal<String> platform = new ThreadLocal<String>();
	protected static ThreadLocal<String> platformVersion = new ThreadLocal<String>();
	protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
	public ThreadLocal<Properties> prop = new ThreadLocal<Properties>();
	TestUtils utils;

	public TestBase() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public AppiumDriver<MobileElement> getDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver<MobileElement> driver2) {
		driver.set(driver2);
	}

	public Properties getProp() {
		return prop.get();
	}

	public void setProp(Properties prop2) {
		prop.set(prop2);
	}

	public HashMap<String, String> getStrings() {
		return strings.get();
	}

	public void setStrings(HashMap<String, String> strings2) {
		strings.set(strings2);
	}
	
	public String getPlatform() {
		return platform.get();
	}

	public void setPlatform(String platform2) {
		platform.set(platform2);
	}

	public String getPlatformVersion() {
		return platformVersion.get();
	}

	public void setPlatformVersion(String platformVersion2) {
		platformVersion.set(platformVersion2);
	}
	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	@BeforeMethod
	public void beforeMethod() {

	
		((CanRecordScreen) getDriver()).startRecordingScreen();
	}

	@AfterMethod
	public synchronized void afterMethod(ITestResult result) {

	
		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
		if (result.getStatus() == 2) {
			Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
			String dirPath = "videos" + File.separator + params.get("platformName") + "_"
					+ params.get("platformVerison") + "_" + params.get("deviceName") + File.separator + getDateTime()
					+ File.separator + result.getTestClass().getRealClass().getSimpleName();

			File videoDir = new File(dirPath);
			synchronized (videoDir) {
				if (!videoDir.exists()) {
					videoDir.mkdirs();
				}
			}

			try {

				FileOutputStream stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
				stream.write(Base64.decodeBase64(media));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Parameters({ "platformName", "platformVersion", "deviceName","UDID" })
	@BeforeTest
	public void initializeDriver(String platformName, String platformVersion, String UDID, String deviceName) throws IOException {
		File file;
		File Str;
		FileInputStream propFile;
		FileInputStream String = null;
		Properties prop = new Properties();
		AppiumDriver driver;

		try {
			file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			utils = new TestUtils();
			propFile = new FileInputStream(file);

			prop.load(propFile);
			setProp(prop);
			setDateTime(utils.getDateTime());
			Str = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Strings\\strings.xml");
			String = new FileInputStream(Str);

			setStrings(utils.parseStringXML(String));

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			cap.setCapability(MobileCapabilityType.UDID, UDID);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
			cap.setCapability(MobileCapabilityType.NO_RESET, "true");
			cap.setCapability(MobileCapabilityType.FULL_RESET, "false");
			cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
			cap.setCapability("appPackage", prop.getProperty("androidAppPackage"));
			cap.setCapability("appActivity", prop.getProperty("androidAppActivity"));
			cap.setCapability("autoDismissAlerts", true);

			driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("appiumUrl")), cap);
			setDriver(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (String != null) {
				String.close();
			}
		}

	}

	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void click(MobileElement e) {
		waitForVisibility(e);
		e.click();
	}

	public void sendKeys(MobileElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}

	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public String getText(MobileElement e, String msg) {
		String txt = null;

		txt = getAttribute(e, "text");
		return txt;
	}

	public void scrollDown(String txt) {
		getDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
						+ txt + "\").instance(0))"))
				.click();
		;
	}

	// public String getDateTime() {
	// return dateTime;
	// }

	public void quitDriver() {
		getDriver().quit();
	}

}

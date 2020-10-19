package com.vikas.listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.vikas.base.TestBase;

public class TestListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {

		if (result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
		}

		TestBase tb = new TestBase();
		File file = tb.getDriver().getScreenshotAs(OutputType.FILE);

		String imgPath = "Screenshots" + File.separator + tb.getDateTime() + File.separator
				+ result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + "png";
		String completeImagePath = System.getProperty("user.dir") + File.separator + imgPath;
		try {
			
			FileUtils.copyFile(file, new File(imgPath));
			Reporter.log("This is a failed test screen shot....");
			Reporter.log("<a href='" +completeImagePath+"'><img src='"+completeImagePath+"'height='100' width='100'/></a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}

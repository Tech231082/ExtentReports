import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class FreeCRMTest {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	@BeforeTest
	public void setExtent() {
		extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/extentReport.html",true);
		extent.addSystemInfo("Host name", "Parmod Kumar");
		extent.addSystemInfo("User name", "Parmod Kumar");
		
	}
	
	@AfterTest
	public void endReprt() {
		extent.flush();
		extent.close();
	}
	
	public static  String getScreenShot(WebDriver driver,String screenhotName) throws Exception{
		String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String desti=System.getProperty("user.dir")+"/failedTestScreenshots/"+screenhotName+dateName+".png";
		File finaldesti=new File(desti);
		FileUtils.copyFile(src,finaldesti);
		return desti;
		
	}
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/mytools/chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://freecrm.com/");
		
		
		
	}
	
	@Test
	public void freeCRMLogoTest() {
		logger=extent.startTest("freeCRMLogoTest");
		boolean b=driver.findElement(By.xpath("//img[@title='free crm app for itunes']")).isDisplayed();
		Assert.assertTrue(b);
	}
	
	@Test
	public void getCRMTitleTest() {
		logger=extent.startTest("getCRMTitleTest");
		String title=driver.getTitle();
		Assert.assertEquals(title, "Free CRM #1 cloud software for any business large or small1");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test case failed is"+result.getName());
			logger.log(LogStatus.FAIL, "Test case failed is"+result.getThrowable());
			
			String screenShotpath=FreeCRMTest.getScreenShot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreencast(screenShotpath));
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenShotpath));
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test case got skipped"+result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test case got passed"+result.getName());
		}
		extent.endTest(logger);
		driver.quit();
	}

}

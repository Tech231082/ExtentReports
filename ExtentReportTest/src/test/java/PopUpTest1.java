import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PopUpTest1 {
public static WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		
		
		System.setProperty("webdriver.chrome.driver", "C:/mytools/chromedriver.exe");
		ChromeOptions op=new ChromeOptions();
		op.addArguments("--disable-notifications");
		
		driver=new ChromeDriver(op);
		driver.get("https://www.facebook.com/");
		
		
	}
	@Test
    public void popUpTest() {
		
		driver.findElement(By.id("email")).sendKeys("sush.rinwa@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("sushila231082");
		driver.findElement(By.id("u_0_2")).click();
		
	}
	
	
	@AfterMethod
    public void tearDown() {
		//driver.quit();
	}


}

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatPopUpTest {
	public static WebDriver driver;

	@BeforeMethod
	public void setUp() {
		
		
		System.setProperty("webdriver.chrome.driver", "C:/mytools/chromedriver.exe");
		ChromeOptions op=new ChromeOptions();
		op.addArguments("--disable-notifications");
		
		driver=new ChromeDriver(op);
		driver.get("https://www.whoson.com/live-chat-best-practice/inline-pop-chat-windows-use/");
		
		
	}
	@Test
    public void popUpTest() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		
		WebElement chat=driver.findElement(By.xpath("//span[contains(text(),'Leave A Message')]"));
		
		js.executeScript("arguments[0].click", chat);
		//Actions action=new Actions(driver);
		//action.moveToElement(chat).click();
		//chat.click();
	}
	
	@AfterMethod
    public void tearDown() {
		//driver.quit();
	}

}

package dataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderProgramme1
{
	public WebDriver driver;
	@BeforeClass
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
	}
	
	@Test(dataProvider = "dp")
	public void login(String un,String pwd) throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		
		
	}
	@AfterClass
	public void teradown()
	{
		driver.quit();
	}
	@DataProvider(name="dp")

	String [] [] logindata()
	{
		String data1 [] [] = {
				               {"admin","sagar"},
				               {"Admin","admin123"},
				               {"sagar","sajjan"}
				
				
				
		                    };
		return data1;
		
	}

}

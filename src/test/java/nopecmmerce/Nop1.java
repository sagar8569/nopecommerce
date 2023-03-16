package nopecmmerce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nop1
{
	public WebDriver driver;
	SoftAssert sa=new SoftAssert();
	@BeforeClass
	@Parameters({"browser"})
	public void setup(String br)
	{
		if(br.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
	}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
	
	
		
	
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

		
	}

	@Test
	public void login() throws InterruptedException
	{
		try
		{
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
		boolean cart = driver.findElement(By.xpath("//span[@class='cart-label']")).isDisplayed();
		sa.assertEquals(cart, true);
		}
		catch(Exception e)
		{
			sa.assertAll();
		}
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("sagarrsajjan@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Dboss@$24");
		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		Thread.sleep(2000);
		
		boolean logout = driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed();
		sa.assertEquals(logout, true);
		
		sa.assertAll();
		
		
		
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
		
	}

}

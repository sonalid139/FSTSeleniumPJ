package hooks;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;


public class hookclass {

	public static WebDriver driver;
	@Before
	public void init_browser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\EclipseWS2022\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	@After
	public void close_browser()
	{
		driver.quit();
	}
}

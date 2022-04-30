package stepdefinitions;

import hooks.hookclass;
import junit.framework.Assert;


import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class stepdefinitionclass {

	WebDriver driver = hookclass.driver; 
	String CouponName = "SndCN1"; //+ RandomString.randomAlphabetic(3);
	String CouponCode = "SndCD1"; 
	String ChngCN=CouponName+"Mod", ChngCD=CouponCode+"Mod";
	
	@Given("User is navigated to Login Page")
	public void User_is_navigated_to_Login_Page()
	{
		driver.get("http://retailm1.upskills.in/admin");
		driver.manage().window().maximize();
		System.out.println("User is navigated to Login Page");
	}
	
	@Given("User logs in with {string} & {string}")
	public void user_login(String username, String password)
	{
		driver.findElement(By.id("input-username")).sendKeys(username);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//i[@class='fa fa-key']")).click();
		System.out.println("User entered username and password");
	}
	@Then ("User should be on dashboard page")
	public void verify_user_login()
	{
		if(driver.findElement(By.linkText("Dashboard")).isDisplayed()== true)
		{
			System.out.println("User login successful");
		}
		else
		{
			System.out.println("User login failed");
		}
	}

	@Given("User is navigated to Coupons page")
	public void user_is_navigated_to_Coupons_page() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//i[@class='fa fa-share-alt fw']")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Coupons")).click();
		Thread.sleep(1000);
		System.out.println("User is navigated to Coupons page");
	}

	@Given("User adds New coupon")
	public void user_adds_New_coupon() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("User clicks Add New button");
		driver.findElement(By.xpath("//i[@class='fa fa-plus']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("input-name")).sendKeys(CouponName); //enter coupon name
		Thread.sleep(1000);
		driver.findElement(By.id("input-code")).sendKeys(CouponCode); //enter coupon code
		/*
		Select Type = new Select(driver.findElement(By.id("input-type")));
		driver.findElement(By.id("input-type")).click();
		Type.selectByValue("Fixed Amount"); // Select Type as Fixed Amount
		*/
		
		driver.findElement(By.id("input-discount")).sendKeys("1"); // enter discount
		Thread.sleep(1000);
		driver.findElement(By.id("input-total")).sendKeys("1000"); // enter total amount
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='logged'][@value='1']")).click(); //select Yes for 'Customer Login'
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='shipping'][@value='1']")).click(); //select Yes for 'Free Shipping'
		Thread.sleep(1000);
		/*
		Select Products = new Select(driver.findElement(By.id("input-product")));
		driver.findElement(By.id("input-product")).click();
		Products.selectByValue("1712"); //select 3rd row data
		
		Select Category = new Select(driver.findElement(By.id("input-category")));
		driver.findElement(By.id("input-category")).click();
		Category.selectByValue("1124"); //select Rings	
		*/
		
		driver.findElement(By.xpath("//i[@class='fa fa-save']")).click(); //click save button
		Thread.sleep(1000);	
		System.out.println("User clicks Save button");
		
	}
	
	@Then("Coupon should be visible on Coupons List page")
		public void verify_coupon_is_visible_on_Coupons_List_page() {
		    // Write code here that turns the phrase above into concrete actions
			List<WebElement> TableRows = driver.findElements(By.xpath("//table/tbody/tr"));
			int NumOfRows = TableRows.size();
			int i;
			String DispCN, DispCD;
			System.out.println("There are " +NumOfRows+ " rows in the coupon list, finding newly added coupon..");
			for (i=1 ; i<=NumOfRows ; i++)
			{
				// look for coupon name
				DispCN = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[2]")).getText();
				if(DispCN.equals(CouponName))	
					{
						//look for coupon code
						DispCD = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[3]")).getText();
						if(DispCD.equals(CouponCode))
						{
							System.out.println("Coupon was added successfully & listed on page");
							break;
						}
					}
			}
		}

	@Given("User edits the coupon")
		public void user_edits_the_coupon() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
			List<WebElement> TableRows = driver.findElements(By.xpath("//table/tbody/tr"));
			int NumOfRows = TableRows.size();
			int i;
			String DispCN, DispCD;
			for (i=1 ; i<=NumOfRows ; i++)
			{
				// look for coupon name
				DispCN = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[2]")).getText();
				if(DispCN.equals(CouponName))	
					{
						//look for coupon code
						DispCD = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[3]")).getText();
						if(DispCD.equals(CouponCode))
						{
							System.out.println("Coupon found on the page, clicking Edit, "+i+"th Row");
							break;
						}
					}
			}
			Thread.sleep(1000);	
			//click edit on matched row
			driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[8]/a[@data-original-title='Edit']/i[@class='fa fa-pencil']")).click(); //click edit button
			Thread.sleep(1000);					
			//clear text box
			driver.findElement(By.id("input-name")).clear();
			driver.findElement(By.id("input-name")).sendKeys(ChngCN); //modify coupon name
			Thread.sleep(1000);
			//clear text box
			driver.findElement(By.id("input-code")).clear();
			driver.findElement(By.id("input-code")).sendKeys(ChngCD); //modify coupon code
			Thread.sleep(1000);
			driver.findElement(By.id("input-discount")).clear();
			driver.findElement(By.id("input-discount")).sendKeys("2"); // modify discount
			Thread.sleep(1000);
			driver.findElement(By.id("input-total")).clear();
			driver.findElement(By.id("input-total")).sendKeys("2000"); // modify total amount
			Thread.sleep(1000);
			driver.findElement(By.xpath("//i[@class='fa fa-save']")).click(); //click save button
			Thread.sleep(1000);
			//validate the success message is displayed
			Assert.assertTrue(driver.findElement(By.xpath("//div[text()=' Success: You have modified coupons!      ']")).isDisplayed());
			System.out.println("Coupon is modified successfully");
			Thread.sleep(1000);
	}

	@Given("User deletes the coupon")
	public void user_deletes_the_coupon() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    List<WebElement> TableRows = driver.findElements(By.xpath("//table/tbody/tr"));
		int NumOfRows = TableRows.size();
		int i;
		String DispCN, DispCD;
		for (i=1 ; i<=NumOfRows ; i++)
		{
			// look for coupon name
			DispCN = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[2]")).getText();
			if(DispCN.equals(ChngCN))	
				{
					//look for coupon code
					DispCD = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[3]")).getText();
					if(DispCD.equals(ChngCD))
					{
						System.out.println("Coupon found on the page, clicking Delete, "+i+"th Row");
						break;
					}
				}
		}
		Thread.sleep(1000);	
		//select checkbox for matched row
		driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[1]")).click();	
		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click(); //click Delete button
		Thread.sleep(1000);
		driver.switchTo().alert().accept(); // accept confirmation
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=' Success: You have modified coupons!      ']")).isDisplayed());
	}	

	
	@Then("Coupon should be deleted from Coupons List")
	public void coupon_should_be_deleted_from_Coupons_List() {
	    // Write code here that turns the phrase above into concrete actions
	    List<WebElement> TableRows = driver.findElements(By.xpath("//table/tbody/tr"));
			int NumOfRows = TableRows.size();
			int i;
			String DispCN, DispCD, ChngCN=CouponName+"Mod", ChngCD=CouponCode+"Mod";
			for (i=1 ; i<=NumOfRows ; i++)
			{
				// look for coupon name
				DispCN = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[2]")).getText();
				if(DispCN.equals(ChngCN))	
					{
						//look for coupon code
						DispCD = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[" +i+ "]/td[3]")).getText();
						if(DispCD.equals(ChngCD))
						{
							System.out.println("Coupon Not deleted successfully, found on the page");
							break;
						}
					}
			}
			System.out.println("Coupon deleted successfully, Not found on the page");		
	}
	@Given("user clicks Logout")
	public void user_clicks_Logout() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out fa-lg']")).click(); //click Delete button
		Thread.sleep(1000);
		
	}

	@Then("User should be logged out")
	public void user_should_be_logged_out() {
	    // Write code here that turns the phrase above into concrete actions
		String LogoutMsg = driver.findElement(By.xpath("//h1[text()=' Please enter your login details.']")).getText();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()=' Please enter your login details.']")).isDisplayed());  
		System.out.println("User is logged out successfully, " +LogoutMsg+ " message is displayed");
	}

}


package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	@Before
	public void setup() throws IOException
    {

        configProp=new Properties();
        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);
        //Loading Config.properties file is done


        String br=configProp.getProperty("browser");

        if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }

        else if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }

        else if (br.equals("ie")) {
            System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
            driver = new InternetExplorerDriver();
        }

    }

	
	@Given("^User Launch Chrome browser$")
	public void user_Launch_Chrome_browser() {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * System.getProperty("user.dir")+"//Drivers/chromedriver.exe"); driver = new
		 * ChromeDriver();
		 */
	    lp=new LoginPage(driver);
	   
	}

	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String url) {
	    driver.get(url);
	    driver.manage().window().maximize();
	}

	

	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		lp.setUserName(email);
		lp.setPassword(password);
	   
	}

	@When("^Click on Login$")
	public void click_on_Login() throws InterruptedException  {
		lp.clickLogin();
		Thread.sleep(3000);
	    
	}
	@Then("^Page titleshould be \"([^\"]*)\"$")
	public void page_titleshould_be(String title) throws InterruptedException  
	{
    if (driver.getPageSource().contains("Login was unsuccessful.")) {
    	
    	driver.close();
    	Assert.assertTrue(false);
    	
    }else {
    	
    	Assert.assertEquals(title, driver.getTitle());
    }
    Thread.sleep(3000);
	}

	@When("^User click on Log out link$")
	public void user_click_on_Log_out_link() throws InterruptedException {
		lp.clickLogout();
		Thread.sleep(3000);
	    
	}

	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String title1) {
	   
		 if (driver.getPageSource().contains("Login was unsuccessful.")) {
		    	
		    	driver.close();
		    	Assert.assertTrue(false);
		    	
		    }else {
		    	
		    	Assert.assertEquals(title1, driver.getTitle());
		    }
			
			}


	@Then("^close browser$")
	public void close_browser() {
		driver.quit();

	}
	
// Customer Dashboard......................................................

	@Then("^User can view Dashboad$")
	public void user_can_view_Dashboad() {
     addCust=new AddcustomerPage(driver);
     // String  abc =  driver.getTitle();
     Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	
	}

	@When("^User click on customers Menu$")
	public void user_click_on_customers_Menu() throws InterruptedException  {
		 Thread.sleep(3000);
	        addCust.clickOnCustomersMenu();
	}

	@When("^click on customers Menu Item$")
	public void click_on_customers_Menu_Item() throws InterruptedException  {

		Thread.sleep(2000);
        addCust.clickOnCustomersMenuItem();
	}

	@When("^click on Add new button$")
	public void click_on_Add_new_button()  {
		 addCust.clickOnAddnew();
	}

	@Then("^User can view Add new customer page$")
	public void user_can_view_Add_new_customer_page()  {
		 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("^User enter customer info$")
	public void user_enter_customer_info() throws InterruptedException  {
		 String email = randomestring() + "@gmail.com";
	        addCust.setEmail(email);
	        addCust.setPassword("test123");
	        // Registered - default
	        // The customer cannot be in both 'Guests' and 'Registered' customer roles
	        // Add the customer to 'Guests' or 'Registered' customer role
	        addCust.setCustomerRoles("Guest");
	        Thread.sleep(3000);

	        addCust.setManagerOfVendor("Vendor 2");
	        addCust.setGender("Male");
	        addCust.setFirstName("Pavan");
	        addCust.setLastName("Kumar");
	        addCust.setDob("7/05/1985"); // Format: D/MM/YYY
	        addCust.setCompanyName("busyQA");
	        addCust.setAdminContent("This is for testing.........");
	}

	@When("^click on Save button$")
	public void click_on_Save_button()  {
		addCust.clickOnSave();
	}

	@Then("^User can view confirmation message \"([^\"]*)\"$")
	public void user_can_view_confirmation_message(String arg1) throws Throwable {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
	}
	
	  //Searching customers using EMail ID ...................................
	
	
	@When("^Enter customer EMail$")
	public void enter_customer_EMail()  {
		searchCust=new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("^Click on search button$")
	public void click_on_search_button() throws InterruptedException  {
		searchCust.clickSearch();
        Thread.sleep(3000);
	}

	@Then("^User should found Email in the Search table$")
	public void user_should_found_Email_in_the_Search_table()  {
		 boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	        Assert.assertEquals(true, status);
	}
	//Searching customers using Name ...................................
	
	
	@When("^Enter customer FirstName$")
	public void enter_customer_FirstName(){
		 searchCust=new SearchCustomerPage(driver);
	        searchCust.setFirstName("Victoria");
	}

	@When("^Enter customer LastName$")
	public void enter_customer_LastName()  {
		searchCust.setLastName("Terces");
	}

	@Then("^User should found Name in the Search table$")
	public void user_should_found_Name_in_the_Search_table() {

        boolean status=searchCust.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(true, status);
	}


}

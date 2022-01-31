package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver ldriver ;
	
	
	public LoginPage(WebDriver rdriver ) {
		
		ldriver= rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	
  @FindBy(xpath=".//input[@id='Email']")
  @CacheLookup
  WebElement txtEmail;
  
  @FindBy(xpath=".//input[@id='Password']")
  @CacheLookup
  WebElement txtPassword;
  
  @FindBy(xpath =".//button[normalize-space()='Log in']")
  @CacheLookup
  WebElement btnLogin;
  
  @FindBy(xpath ="//*[@id='navbarText']/ul/li[3]/a")
  @CacheLookup
  WebElement btnLogout;
  
  
  public void setUserName(String uname) {
	  txtEmail.clear();
	  txtEmail.sendKeys(uname);
	  
	  
  }
	
  
  public void setPassword(String pwd) {
	  txtPassword.clear();
	  txtPassword.sendKeys(pwd);
	 
	  
  }
  
  public void clickLogin() {
	  btnLogin.click();
	  
  }
	
  public void clickLogout() {
	  btnLogout.click();
	  
  }
	
  
  
	
}

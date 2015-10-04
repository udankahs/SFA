package com.email.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class SFDCLogin
{
	private WebDriver driver;
	
	@FindBy(id="username")
	private WebElement unTextBox;
	
	@FindBy(id="password")
	private WebElement pwTextBox;
	
	@FindBy(id="Login")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[@id='error']")
	private WebElement Error;
	
	
	public SFDCLogin(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	public void login(String un,String pw) throws InterruptedException
	{
		driver.get("https://test.salesforce.com");
		unTextBox.sendKeys(un);
		pwTextBox.sendKeys(pw);
		loginButton.click();
	}
	public boolean verifyLogin()
	{
		boolean login = false;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean errorDisplayed = driver.findElements(By.xpath("//div[@id='error']")).size() > 0;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if (errorDisplayed)
		{
			Reporter.log("Login Failed", false);
			login= false;
		}
		else 
		{
			Reporter.log("Login Sucessfull", true);
			login= true;
		}
		return login;
	}
}

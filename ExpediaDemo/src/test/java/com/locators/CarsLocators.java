package com.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.expedia.init.Base;
import com.expedia.init.ConfigFileReader;

public abstract class CarsLocators extends Base {
		/**
	 * Initialize CarsLocators.
	 * 
	 * @param Driver
	 */
	public CarsLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// locators of the Cars
	ConfigFileReader configFileReader = new ConfigFileReader();
	
	@FindBy(xpath = "//a[@aria-controls='search_form_product_selector_cars']" )
	public static WebElement carstab;
	
	@FindBy(xpath = "//button[@aria-label='Pick-up']")
	public static WebElement pickup;
	
	@FindBy(xpath = "//input[@id='pick_up_location']")
	public static WebElement pickupInput;
	
	@FindBy(xpath = "//button[@aria-label='Same as pick-up']")
	public static WebElement sameaspickup;
	
	@FindBy(xpath = "//input[@id='drop_off_location']")
	public static WebElement dropInput;
	
	@FindBy(xpath = "//button[@data-testid='uitk-date-selector-input1-default']")
	public static WebElement datePicker;
	
	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement datePickerDone;
	
	@FindBy(xpath = "//select[@id='pick_up_time']")
	public static WebElement pickUpTime;
	
	@FindBy(xpath = "//select[@id='drop_off_time']")
	public static WebElement dropOffTime;
	
	@FindBy(xpath = "//button[@id='search_button']")
	public static WebElement searchBtn;
	



}
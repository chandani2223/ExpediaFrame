package com.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.init.Base;
import com.expedia.init.ConfigFileReader;

public abstract class StaysLocators extends Base {
	/**
	 * Initialize StaysLocators.
	 * 
	 * @param Driver
	 */

	public StaysLocators(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// locators of the Stays
	ConfigFileReader configFileReader = new ConfigFileReader();

	@FindBy(xpath = "//a[@href='/Hotels']//span[text()='Stays']" )
	public static WebElement stays;
	
//	@FindBy(xpath = "//button[contains(@aria-label,'Going to')]" )
//	public static WebElement goingto;
//	
//	@FindBy(xpath = "//input[@id='destination_form_field']" )
//	public static WebElement goingtoInput;
//	
//	@FindBy(xpath = "//button[contains(@aria-label,'Search places, hotels, and more')]" )
//	public static WebElement placehotel;
//	
//	@FindBy(xpath = "//input[@id='destination_form_field']" )
//	public static WebElement placehotelInput;
//	
	@FindBy(xpath = "//button[contains(@aria-label,'Where to?')]" )
	public static WebElement whereto;
	
	@FindBy(xpath = "//input[@id='destination_form_field']" )
	public static WebElement wheretoInput;
	
	
	@FindBy(xpath = "//button[@data-testid='uitk-date-selector-input1-default']")
	public static WebElement datePicker;
	
	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement datePickerDone;
	
	@FindBy(xpath = "//button[@data-stid='open-room-picker']")
	public static WebElement travellers;
	
	@FindBy(xpath = "//*[@aria-label='Increase the number of adults in room 1']")
	public static WebElement adultsRoomIncrease;
	
	@FindBy(xpath = "//*[@aria-label='Increase the number of children in room 1']")
	public static WebElement ChildrenRoomIncrease;
	
	@FindBy(xpath = "//select[@id='age-traveler_selector_children_age_selector-0-0']")
	public static WebElement ageofChildren;
	
	@FindBy(xpath = "//button[@id='traveler_selector_done_button']")
	public static WebElement StaytravelersDone;
	
	@FindBy(xpath = "//button[@id='search_button']")
	public static WebElement searchBtn;
	

}

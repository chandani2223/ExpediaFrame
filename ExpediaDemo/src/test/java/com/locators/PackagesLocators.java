package com.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.init.Base;
import com.expedia.init.ConfigFileReader;

public abstract class PackagesLocators extends Base {

	/**
	 * Initialize PackagesLocators.
	 * 
	 * @param Driver
	 */

	public PackagesLocators(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}
	
	// locators of the Packages
	ConfigFileReader configFileReader = new ConfigFileReader();
	
	@FindBy(xpath = "//a[@aria-controls='search_form_product_selector_packages']" )
	public static WebElement packages;	
	
	@FindBy(xpath = "//button[contains(@aria-label,'Leaving from')]" )
	public static WebElement leavingfromPack;
	
	@FindBy(xpath = "//input[@id='origin_select']")
	public static WebElement LeavingFromPackInput;
	
	@FindBy(xpath = "//button[@aria-label='Going to']" )
	public static WebElement goingtoPack;
	
	@FindBy(xpath = "//input[@id='destination_select']")
	public static WebElement GoingFromPackInput;
	
	@FindBy(xpath = "//button[@data-testid='uitk-date-selector-input1-default']")
	public static WebElement datePicker;
	
	@FindBy(xpath = "//button[text()='Done']")
	public static WebElement datePickerDone;
	
	//@FindBy(xpath = "//button[@data-stid='open-room-picker']")
	@FindBy(xpath = "//button[contains(@data-stid,'open-room-picker')]")
	public static WebElement travellers;
	
	@FindBy(xpath = "(//*[@class='uitk-icon uitk-step-input-icon'])[2]")
	public static WebElement adultsIncreaseInPackages;
	
	@FindBy(xpath = "(//*[@class='uitk-icon uitk-step-input-icon'])[4]")
	public static WebElement childrenIncreaseInPackages;

	//@FindBy(xpath = "//div[@class='uitk-field uitk-field-select-field uitk-field-select-empty-state has-floatedLabel-label is-required']//*[text()='Child 1 age'][1]")
	@FindBy(xpath = "//div//select[@id='age-null-0-0']")
	public static WebElement selectAgeofChild;
	
	@FindBy(xpath = "(//*[name()='svg'][@class='uitk-icon uitk-step-input-icon'])[6]")
	public static WebElement infantsIncreaseInPackages;
	
	@FindBy(xpath = "//div//select[@id='age-null-0-0'][@name='child-null-0-0']")
	public static WebElement selectAgeofInfants;
	
	//radio buttons id#locators
	@FindBy(xpath = "//input[@id='lap']")
	public static WebElement infantlap;
	
	@FindBy(xpath = "//input[@id='seat']")
	public static WebElement infantseat;
	
	@FindBy(xpath = "//button[@id='search_form_primary_button']")
	public static WebElement travelDone;
	
	@FindBy(xpath = "//button[@id='search_button']")
	public static WebElement searchBtn;
}

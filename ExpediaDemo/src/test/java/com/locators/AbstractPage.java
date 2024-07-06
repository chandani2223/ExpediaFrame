package com.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.expedia.init.Base;
import com.expedia.init.ConfigFileReader;

public abstract class AbstractPage extends Base {

	/**
	 * Initialize UserAbstractPage.
	 * 
	 * @param Driver
	 */
	public AbstractPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	ConfigFileReader configFileReader = new ConfigFileReader();
	@FindBy(xpath = "//a[@aria-controls='search_form_product_selector_flights']")
	public static WebElement flightsTab;

	@FindBy(xpath = "//button[@aria-label='Leaving from']")
	public static WebElement leavingFromField;

	@FindBy(xpath = "//input[@id='origin_select']")
	public static WebElement searchInput;

	@FindBy(xpath = "//button[@aria-label='Going to']")
	public static WebElement goingToField;

	@FindBy(xpath = "//input[@id='destination_select']")
	public static WebElement destinationInput;

	//@FindBy(xpath = "//ul[@class='uitk-action-list no-bullet']//li//div//span//strong[contains(.,'Indianapolis (IND - Indianapolis Intl.)')]")
	//public static WebElement firstFlight;

	//@FindBy(xpath = "//ul[@class='uitk-action-list no-bullet']//li//div//span//strong[contains(.,'New York (NYC - All Airports)')]")
	//public static WebElement firstDestFlight;

	@FindBy(xpath = "//button[@data-testid='uitk-date-selector-input1-default']")
	public static WebElement flightsdates;

	@FindBy(xpath = "//button[@data-stid='apply-date-selector']")
	public static WebElement donedates;

	@FindBy(xpath = "//button[@data-stid='open-room-picker']")
	public static WebElement travelers;
	
	@FindBy(xpath = "//*[@aria-label='Increase the number of adults']")
	public static WebElement adultsIncrease;
	
	@FindBy(xpath = "//*[@aria-label='Increase the number of children']")
	public static WebElement childrenIncrease;
	
	@FindBy(xpath = "//select[@id='age-traveler_selector_children_age_selector-0']")
	public static WebElement ageofChild;
	
	@FindBy(xpath = "//*[@aria-label='Increase the number of infants on lap']")
	public static WebElement infantsOnLap;
	
	@FindBy(xpath = "//select[@id='age-traveler_selector_infant_age_selector-0']")
	public static WebElement infantsOfAge;
	
	@FindBy(xpath = "//*[@aria-label='Decrease the number of infants on lap']")
	public static WebElement decreaseInfantsOnLap;
	
	@FindBy(xpath = "//*[@aria-label='Increase the number of infants in seat']")
	public static WebElement infantsSeat;
	
	@FindBy(xpath = "//select[@id='age-traveler_selector_infant_age_selector-0']")
	public static WebElement infantsSeatAge;
	
	@FindBy(xpath = "//button[@id='travelers_selector_done_button']")
	public static WebElement travelersDone;
	
	@FindBy(xpath = "//button[@id='search_button']")
	public static WebElement searchBtn;

}
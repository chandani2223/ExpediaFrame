package com.expedia.admin;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
//import org.junit.experimental.theories.DataPoint;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.expedia.init.Common;
import com.expedia.init.ConfigFileReader;
import com.locators.AbstractPage;

public class ExpediaAllMethodsPage extends AbstractPage {

	static Common common = new Common(driver);
	static ConfigFileReader configFileReader = new ConfigFileReader();

	/**
	 * Constructor
	 *
	 * @param Dashboard driver
	 */
	public ExpediaAllMethodsPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Redirects to flights tab to search
	 * @throws Exception 
	 */
	public static void goToFlightsSearch(String leavingfrom, String goTo) throws Exception {
		driver.get(configFileReader.getURL());


		common.logPrint("Step :: Click on flights tab.");
		common.click(flightsTab);

		common.pause(5);
		common.logPrint("Step :: Click on leaving from field.");
		common.click(leavingFromField);

		common.pause(2);		
		common.logPrint("Step :: Enter from location.");
		common.logPrint("Leaving From : "+ leavingfrom);
		common.type(searchInput, leavingfrom);


		//===========================Leaving from start==================================//

		String df = "//ul//div//li[@class='uitk-action-list-item is-nested-child uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

		// Find all suggestion elements
		WebDriverWait suggestionsWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> suggestions = suggestionsWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(df)));

		common.logPrint("Total List of City : " + suggestions.size());

		// Iterate through the autosuggestion elements and print their names for debugging
		boolean found = false;     
		// Iterate through the autosuggestion elements and click on the one containing the desired city name

		for (WebElement suggestion : suggestions) {
			String cityName = suggestion.getText();
			common.logPrint("Leaving From Name: " + cityName);
			if (cityName.contains("New York (JFK - John F. Kennedy Intl.)")) {
				common.logPrint("==============================");
				suggestion.click();
				common.logPrint("Selected Leaving From Name :\n"+ cityName);
				found = true;
				break;
			}
		}

		// Print a message if the desired city name for leaving from is not found
		if (!found) {
			common.logPrint("Desired city name for leaving from not found.");
		}
		//===========================Leaving from end==================================//
		common.logPrint("Step :: Click on going to field");
		common.click(goingToField);

		common.pause(2);
		common.logPrint("Step :: Enter Going To location.");
		common.logPrint("Going To : "+ goTo);
		common.type(destinationInput, goTo);

		common.logPrint("======Going To start=======");
		String gt = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";
		// Find all suggestion elements for going to
		WebDriverWait suggestionsWait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> suggestions1 = suggestionsWait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(gt)));

		// Iterate through the autosuggestion elements and print their names for debugging
		boolean found1 = false;
		// Iterate through the autosuggestion elements and click on the one containing the desired city name
		for (WebElement suggestion1 : suggestions1) {
			String cityName1 = suggestion1.getText();
			common.logPrint("Going To Name: " + cityName1);
			if (cityName1.contains("Los Angeles (QLA - All Airports)")) {
				common.logPrint("===================");
				suggestion1.click();
				common.logPrint("Selected Going To Name :\n"+ cityName1);
				found1 = true;
				break;
			}
		}

		// Print a message if the desired city name for going to is not found
		if (!found1) {
			common.logPrint("Desired city name for going to not found.");
		}

		/*=====================================End Going To===========================================*/
		// Click on the "DatePicker" button start

		common.pause(2);
		common.logPrint("Step :: Click on DatePicker field.");
		// Expected dates
		String expDay = "23";
		String expMon = "December 2024";
		String expNextDay = "21";
		String expNextMon = "January 2025";
		common.click(flightsdates);		 

		// Selecting the desired month and day
		while (true) {
			String actMon = driver.findElement(By.xpath("//div[contains(@class,'uitk-month-double-left')]//span[contains(@class,'uitk-month-label')]"))
					.getText();
			String actNextMon = driver.findElement(By.xpath("//div[contains(@class,'uitk-month-double-right')]//span[contains(@class,'uitk-month-label')]")).getText();

			if (expMon.equals(actMon) && expNextMon.equals(actNextMon)) {
				List<WebElement> days = driver.findElements(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-left']//tbody//tr//td"));
				List<WebElement> nextDays = driver.findElements(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-right']//tbody//tr//td"));

				for (WebElement day : days) {
					for (WebElement nextDay : nextDays) {
						if (expDay.equals(day.getText()) && expNextDay.equals(nextDay.getText())) {
							day.click();
							nextDay.click();
							common.logPrint(expMon + " / " + expDay);
							common.logPrint(expNextMon + " / " + expNextDay);
							break;
						}
					}
				}
				break;
			} else {
				common.pause(3);
				WebElement nextMonthButton = driver.findElement(By.xpath("//button[@data-stid='uitk-calendar-navigation-controls-next-button']"));
				nextMonthButton.click();
			}
		}

		// Click on done button
		common.pause(5);
		common.logPrint("Step :: Click on Done Dates .");
		common.highlightElement(donedates);
		common.click(donedates);
		// Click on the "DatePicker" button end

		//travelers fields start
		common.pause(2);
		common.logPrint("Step :: Select the Travelers.");
		common.click(travelers);
		//go ahead of travelers
		common.pause(2);
		common.logPrint("Step :: Click on Adults button.");
		common.click(adultsIncrease);
		common.pause(2);

		common.logPrint("Step :: Click on Children button.");
		common.click(childrenIncrease);
		common.pause(2);

		common.logPrint("Step :: Click on Child Age button.");
		common.click(ageofChild);
		common.pause(2);

		Select ss = new Select(ageofChild);
		ss.selectByValue("12");
		common.logPrint("Selected age of child : "+ss.getFirstSelectedOption().getText());
		common.pause(2);

		common.logPrint("Step :: Click on Infants On Lap button.");
		common.click(infantsOnLap);
		common.pause(2);

		common.logPrint("Step :: Click on Infants Of Age button.");
		common.click(infantsOfAge);
		common.pause(2);

		Select sinfantAge = new Select(infantsOfAge);
		sinfantAge.selectByValue("1");
		common.logPrint("Selected infant LapAge : "+sinfantAge.getFirstSelectedOption().getText());
		common.pause(2);

		common.logPrint("Step :: Click on Decrease Infants OnLap button.");
		common.click(decreaseInfantsOnLap);
		common.pause(2);

		common.logPrint("Step :: Click on Infants Seat button.");
		common.click(infantsSeat);
		common.pause(2);

		common.logPrint("Step :: Click on Infants Seat Age button.");
		common.click(infantsSeatAge);
		common.pause(2);

		Select ssa = new Select(infantsSeatAge);
		ssa.selectByValue("1");
		common.logPrint("Selected infant SeatAge : "+ sinfantAge.getFirstSelectedOption().getText());
		common.pause(2);

		common.logPrint("Step :: Click On The Travelers Done Button.");
		common.click(travelersDone);
		//travelers fields start

		common.pause(2);
		common.logPrint("Step :: Click on search button.");
		common.click(searchBtn);
	}
}
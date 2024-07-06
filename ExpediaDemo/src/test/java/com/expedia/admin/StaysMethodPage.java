package com.expedia.admin;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.expedia.init.Common;
import com.expedia.init.ConfigFileReader;
import com.locators.StaysLocators;

public class StaysMethodPage extends StaysLocators{

	static Common common = new Common(driver);
	static ConfigFileReader configFileReader = new ConfigFileReader();

	public StaysMethodPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Redirects to Stays tab to search
	 */
	public static void goToStaysSearch(String stayGoInput) {
		driver.get(configFileReader.getURL());

		common.logPrint("Step :: Click on Stays tab.");
		common.click(stays);
		common.highlightElement(stays);

		//		common.pause(2);
		//		common.logPrint("Step :: Click on Search places, hotels, and more field.");
		//		common.highlightElement(placehotel);
		//		common.click(placehotel);
		//		common.pause(5);		
		//		common.logPrint("Step :: Enter Going To Hotel and more.");
		//		common.logPrint("Stays Going To Hotel And More : "+ stayGoInput);
		//		common.type(placehotelInput, stayGoInput);
		//		
		//		common.pause(2);
		//		common.logPrint("Step :: Click on Going To field.");
		//		common.highlightElement(goingto);
		//		common.click(goingto);
		//		common.pause(5);		
		//		common.logPrint("Step :: Enter Going To Input.");
		//		common.logPrint("Stays Going Search : "+ stayGoInput);
		//		common.type(goingtoInput, stayGoInput);

		common.pause(2);
		common.logPrint("Step :: Click on Where To field.");
		common.highlightElement(whereto);
		common.click(whereto);
		common.pause(5);		
		common.logPrint("Step :: Enter Where To Input.");
		common.logPrint("Stays Where Search : "+ stayGoInput);
		common.type(wheretoInput, stayGoInput);

		//===========================Where To field start==================================//

		String df = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

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
			if (cityName.contains("Ahmedabad City Centre")) {
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
		
		//===========================Where To field end==================================//

		// Click on the "DatePicker" button start

		common.pause(2);
		common.logPrint("Step :: Click on DatePicker field.");
		// Expected dates
		String expDay = "23";
		String expMon = "December 2024";
		String expNextDay = "21";
		String expNextMon = "January 2025";
		common.highlightElement(datePicker);
		common.click(datePicker);		 

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
		common.highlightElement(datePickerDone);
		common.click(datePickerDone);
		// Click on the "DatePicker" button end

		//travelers fields start
		common.pause(2);
		common.logPrint("Step :: Select the Travelers.");
		common.click(travellers);
		//go ahead of travelers
		common.pause(2);
		common.logPrint("Step :: Click on Adults button.");
		common.click(adultsRoomIncrease);
		common.pause(2);

		common.logPrint("Step :: Click on Children button.");
		common.click(ChildrenRoomIncrease);
		common.pause(2);

		common.logPrint("Step :: Click on Child Age button.");
		common.click(ageofChildren);
		common.pause(2);

		Select ss = new Select(ageofChildren);
		ss.selectByValue("15");
		common.logPrint("Selected age of child : "+ss.getFirstSelectedOption().getText());
		common.pause(2);

		common.logPrint("Step :: Click On The Travelers Done Button.");
		common.click(StaytravelersDone);
		//travelers fields start

		common.pause(2);
		common.logPrint("Step :: Click on search button.");
		common.click(searchBtn);
	}

}

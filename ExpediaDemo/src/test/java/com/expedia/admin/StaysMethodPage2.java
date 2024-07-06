package com.expedia.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.expedia.init.Common;
import com.expedia.init.ConfigFileReader;
import com.locators.StaysLocators;

import java.time.Duration;
import java.util.List;

public class StaysMethodPage2 extends StaysLocators {

	static Common common = new Common(driver);
	static ConfigFileReader configFileReader = new ConfigFileReader();

	public StaysMethodPage2(WebDriver driver) {
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

		common.pause(2);
		common.logPrint("Step :: Click on Where To field.");
		common.highlightElement(whereto);
		common.click(whereto);

		common.logPrint("Step :: Enter Where To Input.");
		common.logPrint("Stays Where Search : " + stayGoInput);
		common.type(wheretoInput, stayGoInput);

		String demage = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

		//Find all suggestion elements
		WebDriverWait suggestionsWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> suggestions = suggestionsWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(demage)));

		common.logPrint("Total List of City : " + suggestions.size());


		//Iterate through the autosuggestion elements and print their names for debugging
		boolean found = false;     
		// Iterate through the autosuggestion elements and click on the one containing the desired city name

		for (WebElement suggestion : suggestions) {
			String cityName = suggestion.getText();
			common.logPrint("Where To Name: " + cityName);
			if (cityName.contains("Ahmedabad City Centre")) {
				common.logPrint("==============================");
				suggestion.click();
				common.logPrint("Selected Where To Name :\n"+ cityName);
				found = true;
				break;
			}
		}

		// Print a message if the desired city name for leaving from is not found
		if (!found) {
			common.logPrint("Desired city name for leaving from not found.");
		}
	}
}

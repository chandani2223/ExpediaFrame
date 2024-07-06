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
import com.locators.PackagesLocators;

public class PackagesMethodPage extends PackagesLocators{

	static Common common = new Common(driver);
	static ConfigFileReader configFileReader = new ConfigFileReader();

	public PackagesMethodPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Redirects to packages tab to search
	 */
	public static void goToPackagsSearch(String leavingfromPackInput, String goToPackInput) {
		driver.get(configFileReader.getURL());

		common.logPrint("Step :: Click on Packages tab.");
		common.click(packages);
		//common.scroll_To_Element(packages);
		//common.highlightElement(packages);

		common.pause(2);
		common.logPrint("Step :: Click on Leaving From field.");
		common.highlightElement(leavingfromPack);
		common.click(leavingfromPack);

		common.pause(2);		
		common.logPrint("Step :: Enter from location.");
		common.logPrint("Leaving From : "+ leavingfromPackInput);
		common.type(LeavingFromPackInput, leavingfromPackInput);

		//===========================Packages Leaving From Start==================================//

		String pl = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

		WebDriverWait pw = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> plist = pw.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(pl)));

		common.logPrint("Total Leaving Location Name :" + plist.size());

		// Iterate through the autosuggestion elements and print their names for debugging
		boolean found = false;     
		// Iterate through the autosuggestion elements and click on the one containing the desired city name

		for(WebElement pone : plist) {
			String leavFromName = pone.getText();
			common.logPrint("Pick Up Name :" + leavFromName);
			if(leavFromName.contains("Paris (PAR - All Airports)")) {
				common.logPrint("===========================================");
				pone.click();
				common.logPrint("You Selected Leaving Name : " + leavFromName);
				found = true;
				break;
			}
		}

		// Print a message if the desired city name for leaving from is not found
		if (!found) {
			common.logPrint("Desired pickUp name not found.");
		}

		//===========================Packages Leaving From End==================================//

		common.pause(2);
		common.logPrint("Step :: Click on Going To field.");
		common.highlightElement(goingtoPack);
		common.click(goingtoPack);

		common.logPrint("Step :: Enter to location.");
		common.logPrint("Leaving From : "+ goToPackInput);
		common.type(GoingFromPackInput, goToPackInput);

		//===========================Packages Going To Start==================================//

		String goinglist = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

		WebDriverWait gw = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> glist = gw.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(goinglist)));

		common.logPrint("Total Going Location Name :" + glist.size());

		// Iterate through the autosuggestion elements and print their names for debugging
		boolean foundg = false;     
		// Iterate through the autosuggestion elements and click on the one containing the desired city name

		for(WebElement gone : glist) {
			String goingToName = gone.getText();
			common.logPrint("Going To Name :" + goingToName);
			if(goingToName.contains("Fortaleza")) {
				common.logPrint("===========================================");
				gone.click();
				common.logPrint("You Selected Going Name : " + goingToName);
				foundg = true;
				break;
			}
		}

		// Print a message if the desired Packages name for leaving from is not found
		if (!foundg) {
			common.logPrint("Desired pickUp name not found.");
		}

		//===========================Packages Going To End==================================//
		//===========================Packages DatePicker Start==================================//

		common.pause(2);
		common.logPrint("Step :: Click on DatePicker field.");
		// Expected dates
		String expDay = "28";
		String expMon = "February 2025";
		String expNextDay = "10";
		String expNextMon = "March 2025";
		common.click(datePicker);

		while(true) {
			String actMon = driver.findElement(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-left']//span[@class='uitk-align-center uitk-month-label']")).getText();
			String actNextMon = driver.findElement(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-right']//span[@class='uitk-align-center uitk-month-label']")).getText();

			if(expMon.equals(actMon) && expNextMon.equals(actNextMon)) {
				List<WebElement> actDays = driver.findElements(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-left']//tbody//tr//td"));
				List<WebElement> actNextDays = driver.findElements(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-right']//table//tbody//tr//td"));

				for(WebElement days : actDays) {
					for(WebElement nextdays : actNextDays) {
						String din = days.getText();
						String nextdin = nextdays.getText();
						if(expDay.equals(din) && expNextDay.equals(nextdin)) {
							days.click();
							nextdays.click();
							common.logPrint(expMon +"/"+ expDay);
							common.logPrint(expNextMon +"/"+ expNextDay);
							break;
						}
					}}
				break;
			}
			else {
				driver.findElement(By.xpath("//button[@data-stid='uitk-calendar-navigation-controls-next-button']")).click();
			}
		}
		common.pause(2);
		common.logPrint("Step :: Click on DatePicker Done field.");
		common.highlightElement(datePickerDone);
		common.click(datePickerDone);
		//===========================Packages DatePicker End==================================//
		//===========================Travellers Start==================================//
		common.pause(2);
		common.logPrint("Step :: Click on Travellers field.");
		common.highlightElement(travellers);
		common.click(travellers);

		common.pause(2);
		common.logPrint("Step :: Click on Adults Increase Button.");
		common.click(adultsIncreaseInPackages);

		common.pause(2);
		common.logPrint("Step :: Click on Children Increase Button.");
		common.click(childrenIncreaseInPackages);

		common.pause(2);
		common.logPrint("Step :: Select Children Age.");
		common.click(selectAgeofChild);
		Select sc = new Select(selectAgeofChild);
		sc.selectByValue("5");
		common.logPrint("You selected age of child is : " + sc.getFirstSelectedOption().getText());

		//common.pause(3);
		//common.logPrint("Step :: Click on Infants.");
		//common.click(infantsIncreaseInPackages);

		//common.pause(5);
		//common.logPrint("Step :: Select Infant Age.");
		//common.click(selectAgeofInfants);
		//Select pck = new Select(selectAgeofInfants);
		//pck.selectByValue("1");  // Selecting age under 1

		//common.logPrint("You selected age of infant is : " + pck.getFirstSelectedOption().getText());

		common.pause(2);
		common.logPrint("Step :: Where will the infants sit..??");
		common.click(infantseat);
		common.pause(2);
		common.click(infantlap);

		common.pause(2);
		common.logPrint("Step :: Click On Done.");
		common.click(travelDone);
		//===========================Travellers End==================================//
		//			
		common.pause(2);
		common.logPrint("Step :: Click on Search Button.");
		common.click(searchBtn);
	}
}

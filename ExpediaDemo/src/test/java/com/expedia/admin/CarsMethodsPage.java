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
import com.locators.CarsLocators;

public class CarsMethodsPage extends CarsLocators {

	static Common common = new Common(driver);
	static ConfigFileReader configFileReader = new ConfigFileReader();

	public CarsMethodsPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Redirects to cars tab to search
	 */
	public static void goToCarsSearch(String pickCar, String dropCar) {
		driver.get(configFileReader.getURL());

		common.logPrint("Step :: Click on Cars tab.");
		common.click(carstab);

		common.pause(2);
		common.logPrint("Step :: Click on pick up field.");
		common.click(pickup);

		common.pause(2);		
		common.logPrint("Step :: Enter PickUp location.");
		common.logPrint("PickUp : "+ pickCar);
		common.type(pickupInput, pickCar);

		//===========================Car Pick-Up Start==================================//

		String cl = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

		WebDriverWait cw = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> clist = cw.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(cl)));

		common.logPrint("Total PickUp Location Name :" + clist.size());

		// Iterate through the autosuggestion elements and print their names for debugging
		boolean found = false;     
		// Iterate through the autosuggestion elements and click on the one containing the desired city name

		for(WebElement cone : clist) {
			String pickUpName = cone.getText();
			common.logPrint("Pick Up Name :" + pickUpName);
			if(pickUpName.contains("Thaltej")) {
				common.logPrint("===========================================");
				cone.click();
				common.logPrint("You Selected PickUp Name : " + pickUpName);
				found = true;
				break;
			}
		}

		// Print a message if the desired city name for leaving from is not found
		if (!found) {
			common.logPrint("Desired pickUp name not found.");
		}

		//===========================Car Pick-Up End==================================//
		//===========================Car Drop-Off Start==================================//

		common.pause(2);
		common.logPrint("Step :: Click on Same as pick up field.");
		common.click(sameaspickup);

		common.pause(2);
		common.logPrint("Step :: Enter Drop-off location.");
		common.logPrint("Drop-off : "+ dropCar);
		common.type(dropInput, dropCar);

		common.logPrint("======Drop Off start=======");

		String drop = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

		WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> dpone = wd.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(drop)));

		boolean foundd = false;

		for(WebElement drpOff : dpone) {
			String DropOffName = drpOff.getText();
			common.logPrint("Drop Off Name :" + DropOffName);
			if(DropOffName.contains("Balanagar Station")) {
				common.logPrint("==============================");
				drpOff.click();
				common.logPrint("You Selected Drop-Off Location :" + drpOff);
				foundd=true;
				break;

			}
		}
		if(!foundd) {
			common.logPrint("Desired Drop-Off name not found.");
		}
		//===========================Car Drop-Off End==================================//
		//===========================Car DatePicker Start==================================//

		common.pause(2); 
		common.logPrint("Step :: Click on DatePicker field.");
		// Expected dates
		String expDay = "26";
		String expMon = "January 2025";
		String expNextDay = "20";
		String expNextMon = "February 2025";
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
		//===========================Car DatePicker End==================================//

		//===========================Car PickUp Time Start==================================//
		common.pause(2);
		common.logPrint("Step :: Click on Pick-Up Time field.");
		common.highlightElement(pickUpTime); 
		common.click(pickUpTime);

		// Select pickup time
		Select stime = new Select(pickUpTime);
		List<WebElement> sst = stime.getOptions();
		for(int i = 0; i < sst.size(); i++) {
			String smt = sst.get(i).getText();
			if(smt.trim().equalsIgnoreCase("05:00")) {
				sst.get(i).click();
				common.pause(2); // This sleep is for demonstration, avoid using it in production
				common.logPrint("Selected Time : " + smt);
				break;
			}
		}
		//===========================Car PickUp Time End==================================//

		//===========================Car Drop-Off Time Start==================================//
		common.pause(2); 
		common.logPrint("Step :: Click on Drop-Off Time field.");
		common.click(dropOffTime);

		Select sof = new Select(dropOffTime);
		List<WebElement> ssf = sof.getOptions();
		for(int i=0; i<ssf.size(); i++) {
			String dot = ssf.get(i).getText();
			if(dot.trim().equalsIgnoreCase("08:45")) {
				ssf.get(i).click();
				common.pause(2);
				common.logPrint("Selected Time : " + dot);
				break;
			}
		}
		//===========================Car Drop-Off Time End==================================//

		common.pause(2); 
		common.logPrint("Step :: Click on Search Button.");
		common.click(searchBtn);

	}

}

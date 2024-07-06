package program;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class flight1 {
	WebDriver driver;

	@Test
	public void flights() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\ExpediaDemo\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.expedia.co.in/");

		// Click on the flight search button
		driver.findElement(By.xpath("//a[@aria-controls='search_form_product_selector_flights']")).click();

		// Click on the "Leaving from" button = 17/5/24 start
		WebDriverWait leavingFromWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		leavingFromWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Leaving from']")))
		.click();

		// Enter "newyork" in the input field
		WebDriverWait originInputWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement originInput = originInputWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='origin_select']")));
		originInput.sendKeys("newyork");

		String df = "//ul//div//li[@class='uitk-action-list-item is-nested-child uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";

		// Find all suggestion elements
		WebDriverWait suggestionsWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> suggestions = suggestionsWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(df)));

		System.out.println("Total List of City : " + suggestions.size());

		// Iterate through the autosuggestion elements and print their names for debugging
		boolean found = false;     
		// Iterate through the autosuggestion elements and click on the one containing the desired city name

		for (WebElement suggestion : suggestions) {
			String cityName = suggestion.getText();
			System.out.println("Leaving From Name: " + cityName);
			if (cityName.contains("New York (JFK - John F. Kennedy Intl.)")) {
				System.out.println("===================");
				System.out.println("Selected Leaving From Name :\n"+ cityName);
				suggestion.click();
				found = true;
				break;
			}
		}

		// Print a message if the desired city name for leaving from is not found
		if (!found) {
			System.out.println("Desired city name for leaving from not found.");
		}



		// Click on the "Going To" button
		System.out.println("=================Going To================");
		WebDriverWait goingToButtonWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement goingToButton = goingToButtonWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Going to']")));
		goingToButton.click();

		// Wait for the destination input field to be visible
		WebDriverWait destinationInputWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement destinationInput = destinationInputWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='destination_select']")));

		// Enter "california" in the input field for destination
		destinationInput.sendKeys("california");

		String gt = "//ul//div//li[@class='uitk-action-list-item uitk-typeahead-result-item has-subtext uitk-action-list-item-relaxed']";
		// Find all suggestion elements for going to
		WebDriverWait suggestionsWait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> suggestions1 = suggestionsWait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(gt)));

		// Iterate through the autosuggestion elements and print their names for debugging
		boolean found1 = false;
		// Iterate through the autosuggestion elements and click on the one containing the desired city name
		for (WebElement suggestion1 : suggestions1) {
			String cityName1 = suggestion1.getText();
			System.out.println("Going To Name: " + cityName1);
			if (cityName1.contains("Los Angeles (QLA - All Airports)")) {
				System.out.println("===================");
				System.out.println("Selected Going To Name :\n"+ cityName1);
				suggestion1.click();
				found1 = true;
				break;
			}
		}

		// Print a message if the desired city name for going to is not found
		if (!found1) {
			System.out.println("Desired city name for going to not found.");
		}


	}
}

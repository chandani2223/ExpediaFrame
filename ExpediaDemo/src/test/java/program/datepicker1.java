package program;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class datepicker1 {
	WebDriver driver;

	@Test
	public void datePickerExpedia() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\ExpediaDemo\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.expedia.co.in/");

		// Click on the flight search button
		driver.findElement(By.xpath("//a[@aria-controls='search_form_product_selector_flights']"))
		.click();

		// expectedDate
		String expDay = "19";
		String expMon = "December 2024";

		// Click on the "DatePicker" button
		System.out.println("=================Date Picker================");
		WebDriverWait dateToButtonWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dateToButton = dateToButtonWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='uitk-date-selector-input1-default']")));
		dateToButton.click();

		while (true) {

			String actMon = driver
					.findElement(By.xpath("//span[contains(text(),'December 2024')]"))
					.getText();

			if (expMon.equals(actMon)) {

				List<WebElement> din = driver.findElements(
						By.xpath("//div[contains(@class,'uitk-month uitk-month-double uitk-month-double-right')]//tbody//tr//td"));

				for (WebElement Days : din) {

					String actDays = Days.getText();
					if (expDay.equals(actDays)) {
						Days.click();
						System.out.println(expMon + "/" + Days.getText());
						break;
					}
				}
				break;
			} else {
				WebElement nextMonthButton = driver.findElement(By.xpath("//button[contains(@data-stid,'uitk-calendar-navigation-controls-next-button')]"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(nextMonthButton)).click();
				
				Actions actions = new Actions(driver);
				actions.moveToElement(nextMonthButton).perform();
				nextMonthButton.click();

			}
		}
		Thread.sleep(2000);
		WebElement done = driver.findElement(By.xpath("//button[@data-stid='apply-date-selector']"));
		done.click();

	}
}

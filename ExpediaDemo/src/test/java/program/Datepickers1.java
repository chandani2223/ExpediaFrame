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

public class Datepickers1 {
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

		// Expected dates
		String expDay = "23";
		String expMon = "December 2024";
		String expNextDay = "21";
		String expNextMon = "January 2025";

		// Click on the "DatePicker" button
		System.out.println("=================Date Picker================");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dateToButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='uitk-date-selector-input1-default']")));
		dateToButton.click();

		// Selecting the desired month and day
        while (true) {
            String actMon = driver.findElement(By.xpath("//div[contains(@class,'uitk-month-double-left')]//span[contains(@class,'uitk-month-label')]")).getText();
            String actNextMon = driver.findElement(By.xpath("//div[contains(@class,'uitk-month-double-right')]//span[contains(@class,'uitk-month-label')]")).getText();

            if (expMon.equals(actMon) && expNextMon.equals(actNextMon)) {
                List<WebElement> days = driver.findElements(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-left']//tbody//tr//td"));
                List<WebElement> nextDays = driver.findElements(By.xpath("//div[@class='uitk-month uitk-month-double uitk-month-double-right']//tbody//tr//td"));
                
                for (WebElement day : days) {
                    for (WebElement nextDay : nextDays) {
                        if (expDay.equals(day.getText()) && expNextDay.equals(nextDay.getText())) {
                            day.click();
                            nextDay.click();
                            System.out.println(expMon + " / " + expDay);
                            System.out.println(expNextMon + " / " + expNextDay);
                            break;
                        }
                    }
                }
                break;
            } else {
                WebElement nextMonthButton = driver.findElement(By.xpath("//button[@data-stid='uitk-calendar-navigation-controls-next-button']"));
                nextMonthButton.click();
            }
        }

        // Click on done button
        WebElement done = driver.findElement(By.xpath("//button[@data-stid='apply-date-selector']"));
        done.click();
    }
}
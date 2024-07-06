package program;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class travelers {
	WebDriver driver;

	@Test
	public void travelersExpedia() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\ExpediaDemo\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.expedia.co.in/");

		// Click on the flight search button
		driver.findElement(By.xpath("//a[@aria-controls='search_form_product_selector_flights']"))
		.click();
		
		//start travelers fields
		WebDriverWait tt = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement travel = tt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-stid='open-room-picker']")));
		travel.click();
		
		Thread.sleep(2000);
		
		WebElement adults = driver.findElement(By.xpath("//*[@aria-label='Increase the number of adults']"));
		adults.click();
		Thread.sleep(2000);
		
		WebElement Children = driver.findElement(By.xpath("//*[@aria-label='Increase the number of children']"));
		Children.click();
		
		WebElement ageofChild = driver.findElement(By.xpath("//select[@id='age-traveler_selector_children_age_selector-0']"));
		Thread.sleep(2000);
		ageofChild.click();
		
		Select ss = new Select(ageofChild);
		ss.selectByValue("12");
		System.out.println("Selected age of child : "+ss.getFirstSelectedOption().getText());
		Thread.sleep(2000);		
		
		WebElement infantofLap = driver.findElement(By.xpath("//*[@aria-label='Increase the number of infants on lap']"));
		infantofLap.click();
		Thread.sleep(5000);
		
		WebElement infantAge = driver.findElement(By.xpath("//select[@id='age-traveler_selector_infant_age_selector-0']"));
		infantAge.click();
		Thread.sleep(2000);
		
		Select sinfantAge = new Select(infantAge);
		sinfantAge.selectByValue("1");
		System.out.println("Selected infant Age : "+sinfantAge.getFirstSelectedOption().getText());
		Thread.sleep(2000);
		
		WebElement decreaseinfantofLap = driver.findElement(By.xpath("//*[@aria-label='Decrease the number of infants on lap']"));
		decreaseinfantofLap.click();
		Thread.sleep(5000);
		
		WebElement infantseat = driver.findElement(By.xpath("//*[@aria-label='Increase the number of infants in seat']"));
		infantseat.click();
		Thread.sleep(2000);
		
		WebElement infantSeatAge = driver.findElement(By.xpath("//select[@id='age-traveler_selector_infant_age_selector-0']"));
		infantSeatAge.click();
		Thread.sleep(2000);
		
		Select ssa = new Select(infantSeatAge);
		ssa.selectByValue("1");
		System.out.println("Selected infant of Seat : "+ssa.getFirstSelectedOption().getText());
		Thread.sleep(2000);
		
		
		
		
		
		
		
		WebElement travelDone = driver.findElement(By.xpath("//button[@id='travelers_selector_done_button']"));
		travelDone.click();

	}

}

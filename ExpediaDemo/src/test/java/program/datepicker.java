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

public class datepicker {
	WebDriver driver;

	@Test
	public void datePickerofGoibibo() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\ExpediaDemo\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.goibibo.com/");

		// Click on the close button
		WebDriverWait LoginButtonWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement LoginToButton = LoginButtonWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='sc-gsFSXq bGTcbn']")));
		LoginToButton.click();
		
		//driver.findElement(By.xpath("//span[@class='sc-gsFSXq bGTcbn']")).click();

		// Click on the "Date-Picker" button = 17/5/24 start
		WebDriverWait DepartureButtonWait = new WebDriverWait(driver, Duration.ofSeconds(30));		
		WebElement departure = DepartureButtonWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'jPzQOy')]//span[contains(@class,'fswDownArrow')]")));
		departure.click();
		
		String expectedMY = "November 2024";
		String expectedD = "23";
		
		while(true) 
		{
			String actMY = driver.findElement(By.xpath("(//div[@role='heading'])[2]")).getText();
			//String actD = driver.findElement(By.xpath("//div[@class='DayPicker-Body']//div[@class='DayPicker-Day']")).getText();
			
			if(expectedMY.equals(actMY)) {
				
				List<WebElement> actD = driver.findElements(By.xpath("//div[@class='DayPicker-Body']//div[@class='DayPicker-Day']"));
				
				for(WebElement e : actD) {
					
					String din = e.getText();
					if(expectedD.equals(din)) {
						e.click();
						break;
					}
				}
				break;
			}
			else {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		}
		
		
		// Click on the "Date-Picker" button = 17/5/24 end
		
	}
}

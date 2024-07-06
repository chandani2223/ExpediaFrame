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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pickUpTime {
	WebDriver driver;
	@Test
	public void pickUptimes() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\ExpediaDemo\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.expedia.co.in/");

		// Click on the flight search button
		driver.findElement(By.xpath("//a[@aria-controls='search_form_product_selector_cars']")).click();

		//click on pickup time
		
		
		WebDriverWait pickUpTime = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ppt = pickUpTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='pick_up_time']")));
		ppt.click();
		
		// Select pickup time
		Select stime = new Select(ppt);
		List<WebElement> sst = stime.getOptions();
		for(int i = 0; i < sst.size(); i++) {
		    String smt = sst.get(i).getText();
		    if(smt.trim().equalsIgnoreCase("05:00")) {
		        sst.get(i).click();
		        Thread.sleep(2000); // This sleep is for demonstration, avoid using it in production
		        System.out.println("Selected Time : " + smt);
		        break;
		    }
		}
		

	}
}

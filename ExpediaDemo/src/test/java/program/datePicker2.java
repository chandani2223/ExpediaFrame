package program;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class datePicker2 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\ExpediaDemo\\drivers\\chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(op);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://jqueryui.com/datepicker/");
		//expectedDate
		String expDay = "19";
		String expMon = "December";
		String expYr = "2024";
		
		driver.switchTo().frame(0);
		
		WebElement dp = driver.findElement(By.id("datepicker"));
		dp.click();
		
		while(true) {
			
			String actMon = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String actYr = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			
			if(expMon.equals(actMon) && expYr.equals(actYr)) {
				
				List<WebElement> din = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
				
				for(WebElement Days : din) {
					
					String actDays = Days.getText();
					if(expDay.equals(actDays)) {
						Days.click();
						System.out.println(expMon.toString() +"/"+ Days.getText() +"/"+ actYr.toString() );
						break;
					}
				}
				break;
			}
			else {
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			}
		}
			
	}
}

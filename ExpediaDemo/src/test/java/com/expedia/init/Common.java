package com.expedia.init;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.locators.AbstractPage;

public class Common extends AbstractPage {

	public static ThreadLocal<Integer> steps = new ThreadLocal<Integer>();

	public Common(WebDriver driver) {
		super(driver);
	}

	public Wait<WebDriver> getFluentWait() {
		return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
	}

	public WebDriverWait getWait() {
		// Set time in second to wait for elements
		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public WebElement waitUntilElementToBeClickable(By by) {
		

		try {
			return getWait().ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(by));

		} catch (StaleElementReferenceException e) {
			return getWait().ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(by));
		}

	}

	public WebElement waitUntilElementToBeClickable(WebElement element) {
		try {
			return getWait().ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(element));
		} catch (StaleElementReferenceException e) {
			return getWait().ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(element));
		}
	}

	public WebElement waitUntilElementToBeVisible(By by) {

		try {
			return getWait().ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(by));

		} catch (StaleElementReferenceException e) {
			return getWait().ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		}

	}

	public WebElement waitUntilPresenceOfElementLocated(By by) {
		return getWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public WebElement waitUntilElementToBeVisible(WebElement element) {
		return getWait().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
	}

	public List<WebElement> waitUntilElementsToBeVisible(By by) {
		return getWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	public List<WebElement> waitUntilElementsToBeVisible(List<WebElement> elements) {
		return getWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitUntilFrameToBeAvailableAndSwitchToIt(WebElement element) {
		getWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public void waitUntilFrameToBeAvailableAndSwitchToIt(String locator) {
		getWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitUntilFrameToBeAvailableAndSwitchToIt(int index) {
		getWait().ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitUntilAlertIsPresent() {
		getWait().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * <p>
	 * Log the passed string to the HTML reports.
	 * </p>
	 * 
	 * <p>
	 * Print the passed string and then terminates the line.
	 * </p>
	 * 
	 * @param message the message to log and to print
	 */
	public void logPrint(String print) {
		if (print.startsWith("Step")) {
			int stepcount = steps.get();
			String msg[] = print.split("::");
			Reporter.log("<br>Step " + stepcount + " : " + msg[1].trim());
			System.out.println("Step " + stepcount + " : " + msg[1].trim());
			steps.set(stepcount + 1);
		} else {
			Reporter.log("<br>Message : " + print);
			System.out.println("Message : " + print);
		}

	}

	public void takeSnapShot(WebDriver webdriver, String screenshotName) throws Exception {

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		// Copy file at destination
		String nameWithExtention = screenshotName + ".png";
		/* Copy screenshot to specific folder */
		try {
			String reportFolder = "target" + File.separator + "failsafe-reports" + File.separator + "chrome"
					+ File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			File DestFile = new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile();
			FileUtils.copyFile(SrcFile, DestFile);
			Reporter.log("<a href='" + "https://app.testreport.io/qa-java-api/api/v1/user/getImage/SCREENSHOT/"
				       + DestFile.getName() + "'> <img src='"
				       + "https://app.testreport.io/qa-java-api/api/v1/user/getImage/SCREENSHOT/" + DestFile.getName()

				       + "' height='250' width='500'/> </a>");
//			Reporter.log("<a href='" + DestFile.getAbsolutePath() + "'> <img src='" + DestFile.getAbsolutePath()
//					+ "' height='250' width='500'/> </a>");
//			FileUtils.copyFile(screenshot,
//					new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			Reporter.log("Failed to capture screenshot: " + e.getMessage());
		}

	}

	/**
	 * If given element is a form entry element, this will reset its value first
	 * then simulate typing into an element, which may set its value and press ENTER
	 * key.
	 * 
	 * 
	 * @param locator    The locator of element where to send keys
	 * @param keysToSend the character sequence to send to the element
	 * @throws InterruptedException
	 * 
	 */

	public void typeAndEnter(String locator, String keysToSend) throws InterruptedException {

		WebElement element = waitUntilElementToBeClickable(findBy(locator));
		highlightElement(element);
		element.click();
		element.sendKeys(keysToSend);
		Thread.sleep(2000);
		element.sendKeys(Keys.ENTER);
	}
	
	
	 /**
     * If given element is a form entry element, this will reset its value first
     * then simulate typing into an element, which may set its value.
     *
     * @param locator    The locator of element where to send keys
     * @param keysToSend the character sequence to send to the element
     */
    public void type(String locator, String keysToSend) {

        WebElement element = waitUntilElementToBeClickable(findBy(locator));
        scroll_To_Element(element);
        element.clear();
        element.sendKeys(keysToSend);
    }

    /**
     * If given element is a form entry element, this will reset its value first
     * then simulate typing into an element, which may set its value.
     * @param element    the element where to send keys
     * @param keysToSend the character sequence to send to the element
     */
    public void type(WebElement element, String keysToSend) {
        waitUntilElementToBeClickable(element);
        element.clear();
        element.sendKeys(keysToSend);
    }
    

	public void makeScreenshot(WebDriver driver, String screenshotName) {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";

		/* Copy screenshot to specific folder */
		try {
			String reportFolder = "target" + File.separator + "failsafe-reports" + File.separator + "chrome"
					+ File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot,
					new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			Reporter.log("Failed to capture screenshot: " + e.getMessage());
		}
	}

	/**
	 * Causes the currently executing thread to sleep (temporarily cease execution)
	 * for the specified number of seconds, subject to the precision and accuracy of
	 * system timers and schedulers. The thread does not lose ownership of any
	 * monitors.
	 * 
	 * @param seconds the time in second to pause execution
	 */

	public void pause(int seconds) {
//		Duration sec = Duration.ofSeconds(seconds);
//		driver.manage().timeouts().implicitlyWait(sec);
		
		 try {
	            Thread.sleep(seconds * 1000L);
	        } catch (InterruptedException interruptedException) {
	        }
	}

	public void makeScreenshotForReport(WebDriver driver, String screenshotName) {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";
		/* Copy screenshot to specific folder */
		try {
			String reportFolder = "target" + File.separator + "failsafe-reports" + File.separator;
			File screenshotFolder = new File(reportFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot,
					new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			logPrint("Failed to capture screenshot : " + e.getMessage());
		}
	}

	/**
	 * Scroll page to element for given locator.
	 *
	 * @param locator the locator of element where to scroll
	 * 
	 */
	public void scroll_To_Element(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	/**
	 * <p>
	 * Creates a random number integer whose length is the number of characters
	 * specified.
	 * </p>
	 *
	 * <p>
	 * Characters will be chosen from the set of numeric characters.
	 * </p>
	 *
	 * @param length the length of random number integer to create
	 * @return The random number integer
	 */
	public int generateRandomInteger(int max) {
		Random random = new Random();
		return random.nextInt(max) + 1;
	}

	/**
	 * Highlight given element
	 * 
	 * @param element the element to be highlighted
	 * 
	 */
	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='4px solid red'", element);
	}

	/**
	 * Get the system OS name.
	 * 
	 * @return The string value of the system OS name
	 */
	public String getOS() {
		String OS = System.getProperty("os.name").toLowerCase();
		logPrint("-----------------------------------------------------------------------------------");
		logPrint("System OS :: " + OS);
		logPrint("-----------------------------------------------------------------------------------");
		return OS;
	}

	public By findBy(String locator) {
		if (locator.startsWith("link=") || locator.startsWith("LINK=")) {
			locator = locator.substring(5); // remove "link=" from locator
			if (locator.contains(" "))
				return By.partialLinkText(locator);
			return By.linkText(locator);
		} else if (locator.startsWith("id=")) {
			locator = locator.substring(3); // remove "id=" from locator
			return By.id(locator);
		} else if (locator.startsWith("//")) {
			return By.xpath(locator);
		} else if (locator.startsWith("#")) {
			return By.cssSelector(locator);
		} else if (locator.startsWith("name=")) {
			locator = locator.substring(5); // remove "name=" from locator
			return By.name(locator);
		} else if (locator.startsWith("class=")) {
			locator = locator.substring(6); // remove "class=" from locator
			return By.className(locator);
		} else if (locator.equalsIgnoreCase("body")) {
			return By.cssSelector(locator);
		} else {
			return By.id(locator);
		}
	}

	/**
	 * Find the first {@link WebElement} using the given method. This method is
	 * affected by the 'implicit wait' times in force at the time of execution. The
	 * findElement(..) invocation will return a matching row, or try again
	 * repeatedly until the configured timeout is reached.
	 * 
	 * 
	 * @param locator the locator to be used by locating mechanism to find element
	 * @return The first matching element on the current page
	 * @throws NoSuchElementException If no matching elements are found
	 * 
	 */
	public WebElement findElement(String locator) {
		if (locator.startsWith("link=") || locator.startsWith("LINK=")) {
			locator = locator.substring(5); // remove "link=" from locator
			if (locator.contains(" "))
				return driver.findElement(By.partialLinkText(locator));
			return driver.findElement(By.linkText(locator));
		} else if (locator.startsWith("id=")) {
			locator = locator.substring(3); // remove "id=" from locator
			return driver.findElement(By.id(locator));
		} else if (locator.startsWith("//") || locator.startsWith("(//")) {
			return driver.findElement(By.xpath(locator));
		} else if (locator.startsWith("#")) {
			return driver.findElement(By.cssSelector(locator));
		} else if (locator.startsWith("name=")) {
			locator = locator.substring(5); // remove "name=" from locator
			return driver.findElement(By.name(locator));
		} else if (locator.startsWith("class=")) {
			locator = locator.substring(6); // remove "class=" from locator
			return driver.findElement(By.className(locator));
		} else if (locator.equalsIgnoreCase("body")) {
			return driver.findElement(By.cssSelector(locator));
		} else {
			return driver.findElement(By.id(locator));
		}
	}

	/**
	 * If given element is a form entry element, this will reset its value first
	 * then simulate typing into an element, which may set its value.
	 * 
	 * 
	 * @param locator    The locator of element where to send keys
	 * @param keysToSend the character sequence to send to the element
	 * 
	 */

	public void typeAndTab(String locator, String keysToSend) {

		WebElement element = waitUntilElementToBeClickable(findBy(locator));
		highlightElement(element);
		element.clear();
		element.sendKeys(keysToSend);
		element.sendKeys(Keys.TAB);
	}

	/**
	 * Click on given element. If this causes a new page to load, you should discard
	 * all references to given element and any further operations performed on given
	 * element will throw a StaleElementReferenceException.
	 * 
	 * There are some preconditions for an element to be clicked. the element must
	 * be visible and it must have a height and width greater then 0.
	 * 
	 * @param locator the locator of element to be clicked.
	 */
	public void click(String locator) {
		WebElement element = waitUntilElementToBeClickable(findBy(locator));
		highlightElement(element);
		try {
			element.click();
		} catch (Exception e) {
		}
	}

	/**
	 * Get the visible (i.e. not hidden by CSS) text of given element, including
	 * sub-elements.
	 * 
	 * @param locator the locator of element from where to get visible text
	 * @return The visible text of given element.
	 */

	public String getText(String locator) {
		pause(2);
		WebElement element = this.findElement(locator);
		highlightElement(element);
		return element.getText();
	}
	public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
        }
    }
	
	
}

package com.expedia.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.expedia.admin.CarsMethodsPage;
import com.expedia.admin.ExpediaAllMethodsPage;
import com.expedia.admin.PackagesMethodPage;
import com.expedia.admin.StaysMethodPage;
import com.locators.AbstractPage;
import com.locators.CarsLocators;
import com.locators.PackagesLocators;
import com.locators.StaysLocators;

public class Base implements ILoggerStatus {
	/* Minimum requirement for test configuration */
	static Properties configProperties = null;
	static Properties xpathProperties = null;
	protected static String test_data_folder_path = null;
	// screen-shot folder
	protected static String screenshot_folder_path = null;
	public static String currentTest; // current running test
	protected static Logger logger = Logger.getLogger("testing.");
	protected static WebDriver driver;

	/* Page's declaration */
	public AbstractPage abstractPage;
	public ExpediaAllMethodsPage expediaAllMethodsPage;
	public ConfigFileReader configFileReader;
	public Common common;
	
	/* chandani - 28/04/2024 */
	
	public CarsLocators carslocator;
	public CarsMethodsPage carMethodPage;
	
	public PackagesLocators packageslocator;
	public PackagesMethodPage packMethodPage;
	
	public StaysLocators stayslocator;
	public StaysMethodPage stayMethodPage;
	
	/*============================================================*/
	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) {

	}

	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws Exception
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) throws Exception {

		currentTest = method.getName(); // get Name of current test.

		String SCREENSHOT_FOLDER_NAME = "screenshots";
		String TESTDATA_FOLDER_NAME = "test_data";

		test_data_folder_path = new File(TESTDATA_FOLDER_NAME).getAbsolutePath();
		screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME).getAbsolutePath();

		String targetBrowser = getPropertyValue("browser");
		String driverPathWindow = getPropertyValue("driverPathWindow");
		String driverPathMac = getPropertyValue("driverPathMac");
		String driverPathFirefox = getPropertyValue("driverPathFirefox");
		String driverPathEdge = getPropertyValue("driverPathEdge");

		// Check if parameter passed from TestNG is 'firefox'
		if (targetBrowser.equalsIgnoreCase("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			System.setProperty("webdriver.gecko.driver", driverPathFirefox);
			LogManager.getLogger("Log4j");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			// options.addArguments("--headless");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new FirefoxDriver(options);
		}

		else if (targetBrowser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", driverPathEdge);
			LogManager.getLogger("Log4j");
			EdgeOptions options = new EdgeOptions();
			// options.addArguments("--headless");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.setAcceptInsecureCerts(true);

			options.merge(capabilities);
			options.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(options);

		}

		// Check if parameter passed as 'chrome private browser'
		else if (targetBrowser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPathWindow); // for windows OS
//			 System.setProperty("webdriver.chrome.driver",driverPathMac); // for mac OS

			LogManager.getLogger("Log4j");
			ChromeOptions options = new ChromeOptions();
			   options.addArguments("start-maximized");            // open Browser in maximized mode
	            options.addArguments("--disable-dev-shm-usage");    // overcome limited resource problems
	            options.addArguments("--no-sandbox");               // Bypass an OS security model
	            options.addArguments("--remote-allow-origins=*");
	           // options.addArguments(
	             //       "user-agent=Mozilla/5.0 (Linux; Android 8.0.0; TA-1053 Build/OPR1.170623.026) AppleWebKit/537.36 (HTML, like Gecko) Chrome/73.0.3683.0 Mobile Safari/537.36");
	            driver = new ChromeDriver(options);

		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		System.out.println("Test started.");

		expediaAllMethodsPage = new ExpediaAllMethodsPage(driver);
		common = new Common(driver);
		Common.steps.set(1);
		
		/* chandani - 28/04/2024 */
		
		carMethodPage = new CarsMethodsPage(driver);
		common = new Common(driver);
		Common.steps.set(1);
		
		packMethodPage = new PackagesMethodPage(driver);
		common = new Common(driver);
		Common.steps.set(1);
		
		stayMethodPage = new StaysMethodPage(driver);
		common = new Common(driver);
		Common.steps.set(1);
		/*============================================================*/
	}

	protected Properties getConfigProperties() {
		if (configProperties == null) {
			configProperties = this.loadProperties(
					Paths.get("config").toAbsolutePath().normalize().toString() + "//Configurations.properties");
		}
		return configProperties;
	}

	protected String getPropertyValue(String value) {
		Properties properties = getConfigProperties();
		return properties.getProperty(value);
	}

	private Properties loadProperties(String filePath) {
		File file = new File(filePath);
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();

		try {
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * After Method {TearDown}
	 * 
	 * @param testResult
	 * @throws Exception
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult) throws Exception {
		Reporter.setCurrentTestResult(testResult);
		try {
			String testName = testResult.getName();
			Reporter.setCurrentTestResult(testResult);
			File img = new File("target" + File.separator + "failsafe-reports" + File.separator + testName + ".png");
			if (testResult.getStatus() == 1) {
				Reporter.log("<font color=238E23><strong><i> PASS : " + testName + "</i></strong></font>");
			}
			if (testResult.getStatus() == 2) {
				Reporter.log("<font color=#FF0000><strong><i> FAIL : " + testName + "</i></strong></font>");
				takeSnapShot(driver, testName);
			}
		} catch (Throwable throwable) {

		}
		driver.manage().deleteAllCookies();
//		driver.quit();
		//driver.close();
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
	 * Test Data Token For 2
	 */
	public Object[][] testDatacsv(String data) {
	    String[][] csvParsingData = null;
	    String fileName = null;
	    if (data.equals("flights")) {
	        fileName = "flightDetails.csv";
	    } else if(data.equals("packages")) {
	        fileName = "packageDetails.csv";
	    } else if(data.equals("cars")) {
	        fileName = "carDetails.csv";	    	
	    } else {
	    	fileName = "stayDetails.csv";
	    }
	    try {
	        String strFile = "testDataCSV" + File.separator + fileName;
	        BufferedReader br = new BufferedReader(new FileReader(strFile));
	        String strLine = "";
	        StringTokenizer st = null;
	        int lineNumber = 0, tokenNumber = 0;
	        int numberOfLines = 0;
	        while ((strLine = br.readLine()) != null)
	            lineNumber++;
	        numberOfLines = lineNumber;
	        System.out.println("Number of lines :: " + numberOfLines);
	        if (data.equals("flights")) {
	            csvParsingData = new String[lineNumber][2];
	        } else if(data.equals("packages")){
	            csvParsingData = new String[lineNumber][2];
	        } else if(data.equals("cars")){
	            csvParsingData = new String[lineNumber][2];
	        }else {
	        	csvParsingData = new String[lineNumber][1];
	        }
	        br.close();
	        br = new BufferedReader(new FileReader(strFile));
	        lineNumber = 0;
	        while ((strLine = br.readLine()) != null) {
	            lineNumber++;
	            st = new StringTokenizer(strLine, "|");
	            while (st.hasMoreTokens()) {
	                tokenNumber++;
	                String token = st.nextToken();
	                System.out.println("Line # " + lineNumber + ", Token # " + tokenNumber + ", Token : " + token);
	                csvParsingData[lineNumber - 1][tokenNumber - 1] = token;
	            }
	            // reset token number
	            tokenNumber = 0;
	        }
	    } catch (Exception e) {
	        System.out.println("Exception while reading csv file :: " + e);
	    }
	    return csvParsingData;
	}
	
	
}
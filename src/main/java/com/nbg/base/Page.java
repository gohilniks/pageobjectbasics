package com.nbg.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.nbg.utilities.ExcelReader;
import com.nbg.utilities.ExtentManager;
import com.nbg.utilities.Utilities;

public class Page {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static String path = System.getProperty("user.dir");
	public static Logger log = LogManager.getLogger();
	public static ExcelReader excel = new ExcelReader(path + "\\src\\test\\resources\\com\\nbg\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;

	public Page() {
		if (driver == null) {
			try {
				fis = new FileInputStream(path + "\\src\\test\\resources\\com\\nbg\\properties\\config.properties");
				config.load(fis);
				log.debug("Config file loaded");

				fis = new FileInputStream(path + "\\src\\test\\resources\\com\\nbg\\properties\\or.properties");
				or.load(fis);
				log.debug("Or file loaded");

				// Jenkins browser filter configuration
				if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
					browser = System.getenv("browser");
				} else {
					browser = config.getProperty("browser");
				}
				config.setProperty("browser", browser);

				switch (config.getProperty("browser")) {
				case "firefox":
					System.setProperty("webdriver.gecko.driver", "D:\\SQA\\Nikunj\\Work\\Frameworks\\PageObjectModelBasics\\geckodriver.exe");
					driver = new FirefoxDriver();
					log.debug("Firefox launched");
					break;
				case "chrome":
					System.setProperty("webdriver.chrome.driver", "D:\\SQA\\Nikunj\\Work\\Frameworks\\PageObjectModelBasics\\chromedriver.exe");
					Map<String, Object> prefs = new HashMap<String, Object>();
					prefs.put("profile.default_content_setting_values.notifications", 2);
					prefs.put("credentials_enable_service", false);
					prefs.put("profile.password_manager_enabled", false);
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", prefs);
					options.addArguments("--disable-extensions");
					options.addArguments("--disable-infobars");

					driver = new ChromeDriver(options);
					log.debug("Chrome launched");
					break;
				default:
					break;
				}

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitwait")),
						TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("pageloadwait")),
						TimeUnit.SECONDS);
				driver.get(config.getProperty("testsiteurl"));
				log.debug("Navigated to " + config.getProperty("testsiteurl"));

				wait = new WebDriverWait(driver, 5);

				menu = new TopMenu(driver);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void quit() {
		driver.quit();
	}

	public static void click(String locator) {
		if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(or.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(or.getProperty(locator))).click();
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(or.getProperty(locator))).click();
		} else if (locator.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(or.getProperty(locator))).click();
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(or.getProperty(locator))).click();
		} else {
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		}
		log.debug("Clicking on an Element : " + locator);
		test.log(Status.INFO, "Clicking on : " + locator);
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CLASS")) {
			driver.findElement(By.className(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(or.getProperty(locator))).sendKeys(value);
		} else {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
		}
		log.debug("Type in an Element : " + locator + " entered value as : " + value);
		test.log(Status.INFO, "Type in : " + locator + " entered value as " + value);
	}

	static WebElement dropdown;

	public static void select(String locator, String value) {
		if (locator.endsWith("_TAGNAME")) {
			dropdown = driver.findElement(By.tagName(or.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(or.getProperty(locator)));
		} else if (locator.endsWith("_NAME")) {
			dropdown = driver.findElement(By.name(or.getProperty(locator)));
		} else if (locator.endsWith("_CLASS")) {
			dropdown = driver.findElement(By.className(or.getProperty(locator)));
		} else if (locator.endsWith("_LINKTEXT")) {
			dropdown = driver.findElement(By.linkText(or.getProperty(locator)));
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			dropdown = driver.findElement(By.partialLinkText(or.getProperty(locator)));
		} else {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.debug("Selecting from an Element : " + locator + " value as " + value);
		test.log(Status.INFO, "Selecting from dropdown : " + locator + " value as " + value);
	}

	public static void verifyEquals(String actual, String expected) throws Exception {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			Utilities.captureScreenshot();
			/* ReportNG */
			Reporter.log("</br>" + "Verification failure : " + t.getMessage() + "</br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img height=200 width=200 src="
					+ Utilities.screenshotName + "></img> </a>");
			Reporter.log("</br>");
			Reporter.log("</br>");

			test.log(Status.FAIL, " Verification Failed with exception : " + t.getMessage());
			test.log(Status.FAIL, "Test Fail",
					MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());
		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}

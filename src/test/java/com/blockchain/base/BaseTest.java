package com.blockchain.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.blockchain.pageobjects.XaltPortalPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public WebDriverWait wait;
	public XaltPortalPage portalObjects;

	protected WebDriver createWebDriver() throws IOException {

		try {
			// Load properties
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "//src//main//java//com//blockchain//resources//GlobalData.properties");
			prop.load(fis);

			String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");

			if (browserName.contains("chrome")) {

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");

				options.addArguments("--remote-allow-origins=*");
				WebDriverManager.chromedriver().setup();
				if (browserName.contains("headless")) {
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900)); // full screen
			} else if (browserName.equalsIgnoreCase("firefox")) {

				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--width=1920");
				options.addArguments("--height=1080");
				driver = new FirefoxDriver(options);

			}

			else if (browserName.equalsIgnoreCase("edge")) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
				driver = new EdgeDriver(options);

			} else {
				throw new IllegalArgumentException("Browser not supported: " + browserName);
			}

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			System.out.println("WebDriver initialized successfully.");
		} catch (Exception e) {
			System.out.println("Error initializing WebDriver: " + e.getMessage());
			e.printStackTrace();
			throw new IOException("Error initializing WebDriver", e);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException, InterruptedException {
		// Create the WebDriver and initialize required objects
		driver = createWebDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		portalObjects = new XaltPortalPage(driver);
		portalObjects.goTo();

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.close();
		}
	}

	// Method without global properties
	/*
	 * / protected WebDriver createWebDriver() {
	 * 
	 * 
	 * // Chrome options ChromeOptions options = new ChromeOptions();
	 * options.addArguments("--remote-allow-origins=*");
	 * options.addArguments("--no-sandbox");
	 * options.addArguments("--disable-dev-shm-usage");
	 * options.addArguments("--window-size=1920,1080");
	 * 
	 * // Initialize WebDriver with ChromeOptions WebDriver driver = new
	 * ChromeDriver(options);
	 * 
	 * FirefoxOptions options = new FirefoxOptions();
	 * options.addArguments("--no-sandbox");
	 * options.addArguments("--disable-dev-shm-usage");
	 * options.addArguments("--width=1920"); options.addArguments("--height=1080");
	 * driver = new FirefoxDriver(options);
	 * 
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); return
	 * driver; } /
	 */

	protected String captureScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(filePath));
		return filePath;
	}

}

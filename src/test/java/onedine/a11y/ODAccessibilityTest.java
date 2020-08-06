
package onedine.a11y;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sridharbandi.AccessibilityRunner;
import io.github.sridharbandi.util.Standard;

public class ODAccessibilityTest {
	private WebDriver driver;
	private static AccessibilityRunner accessibilityRunner;
	public WebDriverWait wait;

	@BeforeEach
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("use-fake-ui-for-media-stream");

		driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
		Dimension d = new Dimension(375, 936);
		// Resize the current window to the given dimension
		driver.manage().window().setSize(d);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		accessibilityRunner = new AccessibilityRunner(driver);
		accessibilityRunner.setStandard(Standard.WCAG2AA);

//		accessibilityRunner.setStandard(Standard.WCAG2AAA);
	}

	@org.junit.jupiter.api.Test
	public void oneDineLandingPageTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/");
		wait = new WebDriverWait(driver, 30);
		Thread.sleep(5000L);
		takeScreenShot("oneDineLandingPage");
		accessibilityRunner.execute("OneDine - Landing Page");
	}

	@org.junit.jupiter.api.Test
	public void scanQRCode() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/");
		wait = new WebDriverWait(driver, 30);
		WebElement scanQRCode = waitForElementToBeClickable("//img[@alt='QR Code']");

		try {
			scanQRCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", scanQRCode);
		}
		Thread.sleep(5000L);
		takeScreenShot("ScanQRCode");
		accessibilityRunner.execute("OneDine - Scan QR Code");
	}

	@org.junit.jupiter.api.Test
	public void loginOptionsTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");

		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		Thread.sleep(5000L);

		takeScreenShot("LoginOptions-VerifyYourPhone");
		accessibilityRunner.execute("OneDine - Login Options - Verify Your Phone");
	}

	@org.junit.jupiter.api.Test
	public void loginOptions() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		Thread.sleep(5000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("loginOptions-SignInPhoneNo");
		accessibilityRunner.execute("OneDine - Login Option - Sign in with phone number");
	}

	@org.junit.jupiter.api.Test
	public void loginPageTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}
		Thread.sleep(5000L);

		takeScreenShot("oneDineLoginPage");
		accessibilityRunner.execute("OneDine - Login Page");
	}

	@org.junit.jupiter.api.Test
	public void afterLogin_SpotNumberPageTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		Thread.sleep(5000L);

		takeScreenShot("spotNumberScreen");
		accessibilityRunner.execute("OneDine - Spot Number Screen");
	}

	@org.junit.jupiter.api.Test
	public void storesScreen() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11MA");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		Thread.sleep(5000L);

		takeScreenShot("storesScreen");
		accessibilityRunner.execute("OneDine - Stores Screen");
	}

	@org.junit.jupiter.api.Test
	public void orderScreenBeveragesTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		Thread.sleep(5000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("orderBeveragesScreenPage");
		accessibilityRunner.execute("OneDine - Order Beverages Screen");
	}

	@org.junit.jupiter.api.Test
	public void orderScreenEntreesTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement entrees = waitForElementToBeClickable("//button[text()='Entrees']");
		try {
			entrees.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", entrees);
		}

		Thread.sleep(3000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("orderEntreesScreenPage");
		accessibilityRunner.execute("OneDine - Order Entrees Screen");
	}

	@org.junit.jupiter.api.Test
	public void orderScreenDessertsTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement desserts = waitForElementToBeClickable("//button[text()='Desserts']");
		try {
			desserts.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", desserts);
		}

		Thread.sleep(5000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("orderDessertsScreenPage");
		accessibilityRunner.execute("OneDine - Order Desserts Screen");
	}

	@org.junit.jupiter.api.Test
	public void accountScreenTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement account = waitForElementToBeClickable("//*[@data-icon='user-circle']");
		try {
			account.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", account);
		}

		Thread.sleep(5000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("accountScreen");
		accessibilityRunner.execute("OneDine - Accounts Screen");
	}

	@org.junit.jupiter.api.Test
	public void profileScreenTest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement account = waitForElementToBeClickable("//*[@data-icon='user-circle']");
		try {
			account.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", account);
		}

		WebElement profile = waitForElementToBeClickable("//div[contains(text(),'Profile')]");
		try {
			profile.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", profile);
		}

		Thread.sleep(5000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("profileScreenTest");
		accessibilityRunner.execute("OneDine - Profile Screen");
	}

	@org.junit.jupiter.api.Test
	public void changePasswordScreen() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement account = waitForElementToBeClickable("//*[@data-icon='user-circle']");
		try {
			account.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", account);
		}

		WebElement profile = waitForElementToBeClickable("//div[contains(text(),'Profile')]");
		try {
			profile.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", profile);
		}

		WebElement changePassword = waitForElementToBeClickable("//a[@href='/profile/changePassword']");
		try {
			changePassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", changePassword);
		}

		Thread.sleep(3000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("changePasswordScreen");
		accessibilityRunner.execute("OneDine - Change Password Screen");
	}

	@org.junit.jupiter.api.Test
	public void orderHistoryScreen() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement account = waitForElementToBeClickable("//*[@data-icon='user-circle']");
		try {
			account.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", account);
		}
		WebElement orderHistory = waitForElementToBeClickable("//div[text()='Order History']");
		orderHistory.click();
		Thread.sleep(6000L);
		System.out.println(driver.getCurrentUrl());
		takeScreenShot("orderHistoryScreen");
		accessibilityRunner.execute("OneDine - Order History Screen");
	}

	@org.junit.jupiter.api.Test
	public void menuDetailsWithModifiersSliders() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement entrees = waitForElementToBeClickable("//button[text()='Entrees']");
		try {
			entrees.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", entrees);
		}
		Thread.sleep(1500L);

		WebElement cheeseBurger = waitForElementToBeClickable("//div[text()='Big cheeseburger']/ancestor::div[2]");
		try {
			cheeseBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", cheeseBurger);
		}
		Thread.sleep(1500L);

		WebElement orderBurger = waitForElementToBeClickable(
				"//div[text()='Big cheeseburger']/ancestor::div[1]/descendant::button[@type='button']");
		try {
			orderBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", orderBurger);
		}

		Thread.sleep(5000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("menuDetailsWithModifiersSliders");
		accessibilityRunner.execute("OneDine - Menu Details with Modifiers and Sliders Screen");
	}

	@org.junit.jupiter.api.Test
	public void myBagScreentest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement entrees = waitForElementToBeClickable("//button[text()='Entrees']");
		try {
			entrees.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", entrees);
		}
		Thread.sleep(1500L);

		WebElement cheeseBurger = waitForElementToBeClickable("//div[text()='Big cheeseburger']/ancestor::div[2]");
		try {
			cheeseBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", cheeseBurger);
		}
		Thread.sleep(1500L);

		WebElement orderBurger = waitForElementToBeClickable(
				"//div[text()='Big cheeseburger']/ancestor::div[1]/descendant::button[@type='button']");
		try {
			orderBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", orderBurger);
		}
		Thread.sleep(1500L);

		WebElement addToBag = waitForElementToBeClickable("//button[contains(text(), 'Add to Bag')]");
		addToBag.click();
		Thread.sleep(1500L);

		WebElement myBag = waitForElementToBeClickable("//div[contains(text(), 'My Bag')]");
		try {
			myBag.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", myBag);
		}

		Thread.sleep(4000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("myBagScreentest");
		accessibilityRunner.execute("OneDine - My Bag Screen");
	}

	@org.junit.jupiter.api.Test
	public void cartScreentest() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement entrees = waitForElementToBeClickable("//button[text()='Entrees']");
		try {
			entrees.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", entrees);
		}
		Thread.sleep(1500L);

		WebElement cheeseBurger = waitForElementToBeClickable("//div[text()='Big cheeseburger']/ancestor::div[2]");
		try {
			cheeseBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", cheeseBurger);
		}
		Thread.sleep(1500L);

		WebElement orderBurger = waitForElementToBeClickable(
				"//div[text()='Big cheeseburger']/ancestor::div[1]/descendant::button[@type='button']");
		try {
			orderBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", orderBurger);
		}
		Thread.sleep(1500L);

		WebElement addToBag = waitForElementToBeClickable("//button[contains(text(), 'Add to Bag')]");
		addToBag.click();
		Thread.sleep(1500L);

		WebElement myBag = waitForElementToBeClickable("//div[contains(text(), 'My Bag')]");
		try {
			myBag.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", myBag);
		}
		Thread.sleep(1500L);



		WebElement nextButton = waitForElementToBeClickable("//button[contains(text(), 'Next')]");
		try {
			nextButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", nextButton);
		}

		Thread.sleep(4000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("cartScreentest");
		accessibilityRunner.execute("OneDine - Cart Screen");
	}

	@org.junit.jupiter.api.Test
	public void payLaterScreen() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement entrees = waitForElementToBeClickable("//button[text()='Entrees']");
		try {
			entrees.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", entrees);
		}
		Thread.sleep(1500L);

		WebElement cheeseBurger = waitForElementToBeClickable("//div[text()='Big cheeseburger']/ancestor::div[2]");
		try {
			cheeseBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", cheeseBurger);
		}
		Thread.sleep(1500L);

		WebElement orderBurger = waitForElementToBeClickable(
				"//div[text()='Big cheeseburger']/ancestor::div[1]/descendant::button[@type='button']");
		try {
			orderBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", orderBurger);
		}
		Thread.sleep(1500L);

		WebElement addToBag = waitForElementToBeClickable("//button[contains(text(), 'Add to Bag')]");
		addToBag.click();
		Thread.sleep(1500L);

		WebElement myBag = waitForElementToBeClickable("//div[contains(text(), 'My Bag')]");
		try {
			myBag.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", myBag);
		}
		Thread.sleep(1500L);



		WebElement nextButton = waitForElementToBeClickable("//button[contains(text(), 'Next')]");
		try {
			nextButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", nextButton);
		}

		WebElement payLater = waitForElementToBeClickable("//button[contains(text(), 'pay later')]");
		try {
			payLater.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", payLater);
		}

		Thread.sleep(4000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("payLaterScreen");
		accessibilityRunner.execute("OneDine - Pay Later Screen");
	}

	@org.junit.jupiter.api.Test
	public void orderThroughPayLaterScreen() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement entrees = waitForElementToBeClickable("//button[text()='Entrees']");
		try {
			entrees.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", entrees);
		}
		Thread.sleep(1500L);

		WebElement cheeseBurger = waitForElementToBeClickable("//div[text()='Big cheeseburger']/ancestor::div[2]");
		try {
			cheeseBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", cheeseBurger);
		}
		Thread.sleep(1500L);

		WebElement orderBurger = waitForElementToBeClickable(
				"//div[text()='Big cheeseburger']/ancestor::div[1]/descendant::button[@type='button']");
		try {
			orderBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", orderBurger);
		}
		Thread.sleep(1500L);

		WebElement addToBag = waitForElementToBeClickable("//button[contains(text(), 'Add to Bag')]");
		addToBag.click();
		Thread.sleep(1500L);

		WebElement myBag = waitForElementToBeClickable("//div[contains(text(), 'My Bag')]");
		try {
			myBag.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", myBag);
		}
		Thread.sleep(1500L);



		WebElement nextButton = waitForElementToBeClickable("//button[contains(text(), 'Next')]");
		try {
			nextButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", nextButton);
		}

		WebElement payLater = waitForElementToBeClickable("//button[contains(text(), 'pay later')]");
		try {
			payLater.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", payLater);
		}

		WebElement orderNow = waitForElementToBeClickable("//button[contains(text(), 'Order Now')]");
		try {
			orderNow.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", orderNow);
		}

		WebElement confirmButton = waitForElementToBeClickable("//button[contains(text(),'Confirm')]");
		try {
			confirmButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", confirmButton);
		}

		Thread.sleep(6000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("orderThroughPayLaterScreen");
		accessibilityRunner.execute("OneDine - Order through Pay Later Screen");
	}

	@org.junit.jupiter.api.Test
	public void payNowScreen() throws InterruptedException {
		driver.get("https://app.dev.onedine.com/dashboard");
		wait = new WebDriverWait(driver, 30);

		WebElement spotCode = waitForElementToBeClickable("//input[@name='spotCode']");
		spotCode.sendKeys("PAI11CU");

		WebElement submitSpotCode = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotCode.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotCode);
		}

		WebElement signInButton = waitForElementToBeClickable(
				"//button[@type='button'][@class='btn btn-primary w-100']");
		try {
			signInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInButton);
		}

		WebElement signInWithPassword = waitForElementToBeClickable(
				"//div[contains(text(), 'Sign in with a password')]");
		try {
			signInWithPassword.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", signInWithPassword);
		}

		WebElement userName = waitForElementToBeClickable("//input[@name='username']");
		userName.sendKeys("onedinedev1");

		WebElement password = waitForElementToBeClickable("//input[@name='password']");
		password.sendKeys("OneDine123!");

		WebElement LogInButton = waitForElementToBeClickable("//button[@type='submit'][contains(text(),'SIGN IN')]");
		try {
			LogInButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", LogInButton);
		}

		WebElement spotNumber = waitForElementToBeClickable("//input[@name='spotNumber']");
		spotNumber.sendKeys("3");

		WebElement submitSpotNumber = waitForElementToBeClickable("//button[@type='submit']");
		try {
			submitSpotNumber.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", submitSpotNumber);
		}

		WebElement entrees = waitForElementToBeClickable("//button[text()='Entrees']");
		try {
			entrees.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", entrees);
		}
		Thread.sleep(1500L);

		WebElement cheeseBurger = waitForElementToBeClickable("//div[text()='Big cheeseburger']/ancestor::div[2]");
		try {
			cheeseBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", cheeseBurger);
		}
		Thread.sleep(1500L);

		WebElement orderBurger = waitForElementToBeClickable(
				"//div[text()='Big cheeseburger']/ancestor::div[1]/descendant::button[@type='button']");
		try {
			orderBurger.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", orderBurger);
		}
		Thread.sleep(1500L);

		WebElement addToBag = waitForElementToBeClickable("//button[contains(text(), 'Add to Bag')]");
		addToBag.click();
		Thread.sleep(1500L);

		WebElement myBag = waitForElementToBeClickable("//div[contains(text(), 'My Bag')]");
		try {
			myBag.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", myBag);
		}
		Thread.sleep(1500L);



		WebElement nextButton = waitForElementToBeClickable("//button[contains(text(), 'Next')]");
		try {
			nextButton.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", nextButton);
		}

		WebElement payNow = waitForElementToBeClickable("//button[contains(text(), 'PAY NOW')]");
		try {
			payNow.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", payNow);
		}

		Thread.sleep(6000L);
		System.out.println(driver.getCurrentUrl());

		takeScreenShot("payNowScreen");
		accessibilityRunner.execute("OneDine - Pay Now Screen");
	}

	public WebElement waitForElementToBeClickable(String inputElement) {
		WebElement mobileElement = driver.findElement(By.xpath(inputElement));
		return (WebElement) new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(mobileElement));
	}

	public void takeScreenShot(String filename) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/accessibility/report/Screens/"
					+ filename + System.currentTimeMillis() + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void scrollAndClickElementUsingJS(String input) throws InterruptedException {
		WebElement element = waitForElementToBeClickable(input);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Thread.sleep(1500L);
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@AfterAll
	public static void generateReport() {
		accessibilityRunner.generateHtmlReport();
	}

}

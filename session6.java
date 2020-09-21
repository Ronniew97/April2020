package homeworks;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class session6 {

	WebDriver driver;

	@BeforeTest
	public void launchBrowser() {

		String browser = "chrome";

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.get("https://www.techfios.com/ibilling/?ng=admin/");

		By USERNAME_LOCATOR = By.xpath("//input[@id='username']");
		By PASSWORD_LOCATOR = By.xpath("//input[@id='password']");
		By LOGIN_LOCATOR = By.xpath("//button[@name='login']");

		String username = "demo@techfios.com";
		String pass = "abc123";

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.findElement(USERNAME_LOCATOR).sendKeys(username);
		driver.findElement(PASSWORD_LOCATOR).sendKeys(pass);
		driver.findElement(LOGIN_LOCATOR).click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void customerPage() {
		WebElement EXPAND_MENU_BUTTON = driver
				.findElement(By.xpath("//a[@class='navbar-minimalize minimalize-styl-2 btn btn-primary btn-flat']"));
		WebElement CUSTOMER_BUTTON = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a"));
		WebElement ADDCUSTOMER_BUTTON = driver.findElement(By.xpath("//a[text()='Add Customer']"));
		By DASHBOARD_TITLE = By.xpath("//h2[contains(text(),'Dashboard')]");

		System.out.println("DASHBOARD PAGE: " + assertion(DASHBOARD_TITLE, true));
		EXPAND_MENU_BUTTON.click();
		explicitWait(20, CUSTOMER_BUTTON);
		CUSTOMER_BUTTON.click();
		ADDCUSTOMER_BUTTON.click();

		By FULL_NAME = By.xpath("//input[@name='account']");
		WebElement COMPANY_DROPDOWN = driver.findElement(By.xpath("//select[@name='cid']"));
		By EMAIL = By.xpath("//input[@name='email']");
		By PHONE = By.xpath("//input[@name='phone']");
		By ADDRESS = By.xpath("//input[@name='address']");
		By CITY = By.xpath("//input[@name='city']");
		By STATE = By.xpath("//input[@name='state']");
		By ZIP = By.xpath("//input[@name='zip']");
		WebElement COUNTRY_DROPDOWN = driver.findElement(By.xpath("//select[@name='country']"));
		WebElement GROUP_DROPDOWN = driver.findElement(By.xpath("//select[@name='group']"));
		By PASS = By.xpath("//input[@name='password']");
		By CPASS = By.xpath("//input[@name='cpassword']");
		WebElement SAVE_BUTTON = driver
				.findElement(By.xpath("//button[@class='md-btn md-btn-primary waves-effect waves-light']"));

		String name = "Ronnie Wainaina";
		String company = "Techfios";
		String email = "rw" + random() + "@email.com";
		String phone = "254-878-1234" + random();
		String address = "321 fakestreet";
		String city = "Nairobi";
		String state = "";
		String zip = "900";
		String country = "Kenya";
		String group = "April2020";
		String pass = "password";

		explicitWait(20, driver.findElement(FULL_NAME));

		driver.findElement(FULL_NAME).sendKeys(name);
		dropDown(COMPANY_DROPDOWN, company);
		driver.findElement(EMAIL).sendKeys(email);
		driver.findElement(PHONE).sendKeys(phone);
		driver.findElement(ADDRESS).sendKeys(address);
		driver.findElement(CITY).sendKeys(city);
		driver.findElement(STATE).sendKeys(state);
		driver.findElement(ZIP).sendKeys(zip);
		dropDown(COUNTRY_DROPDOWN, country);
		dropDown(GROUP_DROPDOWN, group);
		driver.findElement(PASS).sendKeys(pass);
		driver.findElement(CPASS).sendKeys(pass);
		SAVE_BUTTON.click();

		By NAME_LOCATOR = By.xpath("//h5[text()='Ronnie Wainaina']");

		System.out.println("CONTACTS PAGE CUSTOMER NAME: " + assertion(NAME_LOCATOR, true));

	}


	public void explicitWait(int time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void dropDown(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	public int random() {
		Random rand = new Random();
		return rand.nextInt(99);
	}

	public boolean assertion(By locator, boolean expected) {

		boolean actual;

		try {
			driver.findElement(locator);
			actual = true;
		} catch (NoSuchElementException n) {
			actual = false;
		}
		Assert.assertEquals(expected, actual);
		return actual;

	}
}

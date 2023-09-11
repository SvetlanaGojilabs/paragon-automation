package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.eventscreens.EventsSearchPage;
import config.DriverConfig;
import pages.loginscreens.LoginScreen;

import java.time.Duration;


@Listeners(TestNGListener.class)
public class EventsSearchTest extends DriverConfig {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log;

	/**
	 * initializes driver
	 * 
	 */
	@BeforeTest
	public void setup() {

		driver = initializeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		log = LogManager.getLogger(this.getClass());

	}

	/**
	 * testNG data provider for tests
	 * 
	 * @return - Object[][]
	 */
	@DataProvider(name = "searchData")
	public Object[][] provideSearchData() {
		return new Object[][] {
				{ "event", "event", "Search event" },
				{"Search that cannot exist", "NO EVENTS TO SHOW", "No Events to show"}
		};
	}

	/**
	 * launches home page URL
	 * 
	 */
	@BeforeClass
	public void launchURL() {
		String url = getUrl("url");
		log.info("Launching URL: " + url);
		driver.get(url);
		LoginScreen loginScreen = new LoginScreen(driver);
		loginScreen.login("nikita@gojilabs.com", "password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.MuiTypography-root.MuiTypography-h1.css-94amt2")));
	}

	@Test(dataProvider = "searchData")
	public void validateEventsSearchResult(String searchText, String expectedResult, String testName) {
		EventsSearchPage searchPage = new EventsSearchPage(driver);
		searchPage.enterSearchText(searchText);
		String actualText;
		if(testName == "Search event") {
			 actualText = String.valueOf(searchPage.getFirstResult());

		}
		else{
			 actualText = searchPage.emptyState();
		}
		Assert.assertTrue(actualText.contains(expectedResult), "Actual: " + actualText);
		log.info("Test Case: " + testName + " - Passed");

	}

	/**
	 * closes driver
	 */
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			log.debug("End of Test. Closing driver" + System.lineSeparator());
			driver.close();
		}
	}

}

package pages.eventscreens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CommonFunctions;


public class EventsSearchPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	CommonFunctions functions;


	private Logger log = LogManager.getLogger(this.getClass());


	private By eventSearchBar = By.xpath("//input[@aria-label='search filed' and contains(@class, 'MuiInputBase-input')]");
	private By eventResults = By.className("MuiDataGrid-cell--withRenderer");
	private By emptyState = By.cssSelector("h4.MuiTypography-root.MuiTypography-h4.css-1ldfmns");

	public EventsSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * enter search text in search box
	 *
	 */


	public void enterSearchText(String text) {
		driver.findElement(eventSearchBar).clear();
		driver.findElement(eventSearchBar).sendKeys(text);
		log.info("Entered text: " + text);
		}


	public EventsSearchPage getFirstResult() {
			String text = driver.findElement(eventResults).getText();
			log.info("Result found: " + text);
			return this;
	}

	public String  emptyState(){
		String text = driver.findElement(emptyState).getText();
		log.info("Result found: " + text);
		return text;

	}


}

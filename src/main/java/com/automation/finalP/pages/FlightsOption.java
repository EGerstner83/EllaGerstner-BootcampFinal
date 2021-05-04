package com.automation.finalP.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class to handle all the request of the departure results
 *
 * @author Ella.Gerstner
 */
public class FlightsOption extends BasePage {
    /*Locator of the button to confirm the flight request is ok*/
    @FindBy(css = "button[data-stid='location-field-leg1-origin-menu-trigger']")
    private WebElement buttonFrom;
    /*Locator to input the origin*/
    @FindBy(id = "location-field-leg1-origin")
    private WebElement from;
    /*Locator are the coincidences of the origin city*/
    @FindBy(css = "ul[data-stid=location-field-leg1-origin-results] li")
    private List<WebElement> resultFrom;
    /*Locator to select the origin from the origin list*/
    @FindBy(css = "button[data-stid='location-field-leg1-destination-menu-trigger']")
    private WebElement buttonGo;
    /*Locator to input destination*/
    @FindBy(id = "location-field-leg1-destination")
    private WebElement destination;
    /*Locator to have all the destination match with the input*/
    @FindBy(css = "ul[data-stid=location-field-leg1-destination-results] li")
    private List<WebElement> resultGo;
    /*Locator to select the date*/
    @FindBy(id = "d1-btn")
    private WebElement dateOne;
    /*Locator where select next month in case the month is not showed*/
    @FindBy(css = "div[class*='uitk-flex uitk-flex-justify-content-space-between uitk-date-picker-menu-pagination-container'] button:last-child")
    private WebElement nextMonth;
    /*Locator to inform the start and end date where selected*/
    @FindBy(css = "button[data-stid='apply-date-picker']")
    private WebElement done;
    /*Locator to send the request of the flight*/
    @FindBy(css = "button[data-testid='submit-button']")
    private WebElement search;

    private ValidationsResults validationsResults = new ValidationsResults(getDriver());

    /**
     * Constructor method.
     *
     * @param driver the driver
     * @author Ella.Gerstner
     */
    public FlightsOption(WebDriver driver) {
        super(driver);
    }

    /**
     * Methos to input the data required to search a flight.
     *
     * @param origen
     * @param destino
     * @param futureGo
     * @param futureBack
     */
    public void selectFlight(String origen, String destino, LocalDate futureGo, LocalDate futureBack) {
        buttonFrom.click();
        from.sendKeys(origen);
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        for (WebElement webElement : resultFrom) {
            if (webElement.findElement(By.tagName("strong")).getText().contains(origen)) {
                webElement.click();
                break;
            }
        }
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        buttonGo.click();
        destination.sendKeys(destino);
        for (WebElement webElement : resultGo) {
            if (webElement.findElement(By.tagName("strong")).getText().contains(destino)) {
                webElement.click();
                break;
            }
        }
        dateOne.click();
        getWait().until(ExpectedConditions.elementToBeClickable(nextMonth));
        nextMonth.click();
        List<WebElement> days = getDriver().findElements(By.cssSelector("div[data-stid='date-picker-month'] tbody tr td button"));
        for (WebElement dias : days) {
            if (dias.getAttribute("aria-label").equals(futureGo.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))) {
                dias.click();
                nextMonth.click();
                break;
            }
        }
        days = getDriver().findElements(By.cssSelector("div[data-stid='date-picker-month'] tbody tr td button"));
        for (WebElement dias : days) {
            if (dias.getAttribute("aria-label").equals(futureBack.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))) {
                dias.click();
                break;
            }
        }
        done.click();
        getWait().until(ExpectedConditions.elementToBeClickable(search));
        search.click();
    }

    /**
     * Method to compare the options of the dropdown, the feeds og baggage and duration is present in all flight results
     *
     * @param price
     * @param departure
     * @param duration
     * @param arrival
     */
    public String doValidationResults(String price, String duration, String departure, String arrival) {
        String first = validationsResults.validationsDropList(price, duration, departure, arrival);
        String second = validationsResults.durationPresent();
        String third = validationsResults.detailBaggage();
        return first + "," + second + "," + third;
    }

    /**
     * Method to call the method to order the flight results
     */
    public String sortBySelectFlightGo() throws InterruptedException {
        return validationsResults.sortByResults();
    }

    /**
     * Method to call the method to select the first departure flight
     */
    public void selectDeparture() {
        validationsResults.selectFirstFlight();
    }

    /**
     * Method to call the method to select the third option of arrival flight
     */
    public void handleReturn() throws InterruptedException {
        validationsResults.chooseReturn();
    }
}

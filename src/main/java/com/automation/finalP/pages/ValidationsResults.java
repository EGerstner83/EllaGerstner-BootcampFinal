package com.automation.finalP.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Validation result class handle the request of the departure and arrival validations
 *
 * @author Ella.Gerstner
 */
public class ValidationsResults extends BasePage {
    /*locator of the dropbox element*/
    @FindBy(id = "listings-sort")
    private WebElement dropList;
    /*lacator with the options of dropbox */
    @FindBy(id = "listings-sort option")
    private List<WebElement> dropbox;
    /*locator where coul find the duration flight*/
    @FindBy(css = "ul[data-test-id='listings'] li[data-test-id='offer-listing'] div[data-test-id='journey-duration']")
    private List<WebElement> duration;
    /*Locator who retunrs the list of the arrivals retunr*/
    @FindBy(css = "ul[data-test-id='listings'] li[data-test-id='offer-listing'] button")
    private List<WebElement> details;
    /*Locator to close the details of arrival flights*/
    @FindBy(css = "button[data-icon='tool-close']")
    private WebElement close;
    /*Locator of the continue button after to select on flight*/
    @FindBy(css = "button[data-test-id='select-button']")
    private WebElement continueButton;

    private List orderLsit = new ArrayList();

    /**
     * Constructor method.
     *
     * @param driver the driver
     * @author Ella.Gerstner
     */

    public ValidationsResults(WebDriver driver) {
        super(driver);
    }

    /**
     * Method to validate if the elements price,duration,departure, and arrival are in the options to sorby
     *
     * @param arrival
     * @param departure
     * @param duration
     * @param price
     * @author Ella.Gerstner
     */
    public String validationsDropList(String price, String duration, String departure, String arrival) {
        int contado = 0;
        for (WebElement elementos : dropbox) {
            if (elementos.getText().contains(price)) {
                contado++;
            } else if (elementos.getText().contains(duration)) {
                contado++;
            } else if (elementos.getText().contains(departure)) {
                contado++;
            } else if (elementos.getText().contains(arrival)) {
                contado++;
            }
        }
        return contado + "," + dropbox.size();
    }

    /**
     * Method to validate that all departure results have duration time
     *
     * @author Ella.Gerstner
     */
    public String durationPresent() {
        int conta = 0;
        for (WebElement duracion : duration) {
            if (duracion.isEnabled()) {
                conta++;
            }
        }
        return conta + "," + duration.size();
    }

    /**
     * Method to validate that the feeds baggage are in all departure results
     *
     * @author Ella.Gerstner
     */
    public String detailBaggage() {
        int contador = 0;
        for (WebElement duracion : details) {
            getDriver().manage().timeouts().pageLoadTimeout(9, TimeUnit.SECONDS);
            duracion.click();
            getWait().until(ExpectedConditions.visibilityOf(duracion.findElement(By.xpath("//td[contains(text(),'Carry-on:')]"))));
            if (duracion.findElement(By.xpath("//td[contains(text(),'Carry-on:')]")).getText().equalsIgnoreCase("Carry-on:")) {
                getWait().until(ExpectedConditions.elementToBeClickable(close));
                close.click();
                contador++;
            }
        }
        return contador + "," + details.size();
    }

    /**
     * Method order by duration shortest
     *
     * @author Ella.Gerstner
     */
    public String sortByResults() throws InterruptedException {
        getDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        getWait().until(ExpectedConditions.elementToBeClickable(dropList));
        Select orderBy = new Select(dropList);
        orderBy.selectByVisibleText("Duration (Shortest)");
        List orderLsit = new ArrayList();
        List resultv = new ArrayList();
        Thread.sleep(6000);
        for (WebElement duracion : duration) {
            orderLsit.add(duracion.getText());
            getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
        resultv.addAll(orderLsit);
        Collections.sort(resultv);
        return resultv.size()+","+ orderLsit.size();
    }

    /**
     * Method to select the first result of the arrivals
     *
     * @author Ella.Gerstner
     */
    public void selectFirstFlight() {
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        details.get(0).click();
        getWait().until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    /**
     * Method to select the third option of the departure results
     *
     * @author Ella.Gerstner
     */
    public void chooseReturn() throws InterruptedException {
        Thread.sleep(10000);
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        details.get(2).click();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getWait().until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
}

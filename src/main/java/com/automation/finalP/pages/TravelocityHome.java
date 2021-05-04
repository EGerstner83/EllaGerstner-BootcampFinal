package com.automation.finalP.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * Class to handle all the request to main page of flights
 *
 * @author Ella.Gerstner
 */

public class TravelocityHome extends BasePage {
    FlightsOption vuelos = new FlightsOption(getDriver());
    ValidationsResults firstFlight = new ValidationsResults(getDriver());
    ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());
    InputDataTraveler inputDataTraveler = new InputDataTraveler(getDriver());
    /*locator to go to flights tab*/
    @FindBy(css = "a[href='?pwaLob=wizard-flight-pwa']")
    private WebElement flights;

    public TravelocityHome(WebDriver driver) {
        super(driver);
        driver.get("https://www.travelocity.com/");
    }

    /**
     * Method input the departure arrival and date of the flight
     *
     * @param destino
     * @param origen
     * @param futureBack
     * @param futureGo
     * @author Ella.Gerstner
     */
    public void searchFlights(String origen, String destino, LocalDate futureGo, LocalDate futureBack) {
        flights.click();
        vuelos.selectFlight(origen, destino, futureGo, futureBack);
    }

    /**
     * Method to validate to orderby the results by price, duration, departure, arrival
     *
     * @param arrival
     * @param departure
     * @param price
     * @param duration
     * @author Ella.Gerstner
     */
    public String vaidationResults(String price, String duration, String departure, String arrival) {
       return vuelos.doValidationResults(price, duration, departure, arrival);
    }

    /**
     * Method who call the method to validate that the option order by duration shortest is working
     *
     * @author Ella.Gerstner
     */
    public String orderByResult() throws InterruptedException {
        return vuelos.sortBySelectFlightGo();
    }

    /**
     * Method to call the method to select the first flight
     *
     * @author Ella.Gerstner
     */
    public void selectFirstFlight() {
        getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        firstFlight.selectFirstFlight();
    }

    /**
     * Method to call the method to select the third return flight
     *
     * @author Ella.Gerstner
     */
    public void seletReturn() throws InterruptedException {
        vuelos.handleReturn();
    }

    /**
     * Method to call the method who validate the information of the flight
     *
     * @author Ella.Gerstner
     */
    public String confirmationFlight(String origen, String destino) {
        return confirmationPage.confirmFlight(origen, destino);
    }

    /**
     * Method to call the method to confirm the flight
     *
     * @author Ella.Gerstner
     */
    public void checkOutFlight()  {
        confirmationPage.checkButton();
    }

    /**
     * Method to call the method to validate the information required to by the ticket
     *
     * @author Ella.Gerstner
     */
    public String inputForm() {
       return  inputDataTraveler.validateData();
    }
}

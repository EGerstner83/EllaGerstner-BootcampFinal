package com.automation.finalP.test;

import com.automation.finalP.pages.ConfirmationPage;
import com.automation.finalP.pages.TravelocityHome;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// TODO: Auto-generated Javadoc

/**
 * This class call all the tests
 *
 * @author Ella.Gerstner
 */

public class TravelocityTest extends BaseTest {

    /**
     * Go the main page of travelocity.com and input the information
     *
     * @param origen
     * @param destino
     * @param futureGo
     * @param futureBack
     */
    @Test(priority = 0, dataProvider = "flightData")
    public void searchFlight(String origen, String destino, LocalDate futureGo, LocalDate futureBack) {
        TravelocityHome home = getTravelocityHome();
        home.searchFlights(origen, destino, futureGo, futureBack);
    }

    /**
     * Call the class to validate listBox and duration
     *
     * @param arrival
     * @param duration
     * @param departure
     * @param price
     */
    @Test(priority = 1, dataProvider = "listoBox")
    public void validationsRes(String price, String duration, String departure, String arrival) {
        TravelocityHome home = getTravelocityHome();
        String[] compare = home.vaidationResults(price, duration, departure, arrival).split(",");
        Assert.assertEquals(compare[0], compare[1]);
        Assert.assertEquals(compare[2], compare[3]);
        Assert.assertEquals(compare[4], compare[5]);
    }

    /**
     * This class order the departure results buy duration shorter
     */
    @Test(priority = 2)
    public void sortBy() throws InterruptedException {
        TravelocityHome home = getTravelocityHome();
        String[] compare = home.orderByResult().split(",");
        Assert.assertEquals(compare[0], compare[1]);
    }

    /**
     * This class selects the first result
     */
    @Test(priority = 3)
    public void selectDeparture() {
        TravelocityHome home = getTravelocityHome();
        home.selectFirstFlight();
    }

    /**
     * This class select the third arrival's result
     */
    @Test(priority = 4)
    public void returnFlight() throws InterruptedException {
        TravelocityHome home = getTravelocityHome();
        home.seletReturn();
    }

    /**
     * This class confirm the departure and arrival are the cities inputed in the search option
     *
     * @param origen
     * @param destino
     */
    @Test(priority = 5, dataProvider = "cities")
    public void confimrFlight(String origen, String destino) {
        TravelocityHome home = getTravelocityHome();
        String[] result = home.confirmationFlight(origen, destino).split(",");
        Assert.assertEquals(result[0], result[1]);
    }

    /**
     * This class select the checkout button to continue with the buy
     */
    @Test(priority = 6)
    public void checkOutFlight() {
        TravelocityHome home = getTravelocityHome();
        home.checkOutFlight();
    }

    /**
     * This class check the form to input the information required to buy the ticket
     */
    @Test(priority = 7)
    public void formInput() {
        TravelocityHome home = getTravelocityHome();
        String[] result = home.inputForm().split(",");
        Assert.assertEquals(result[0], result[1]);
        Assert.assertEquals(result[2], result[3]);
        Assert.assertEquals(result[4], result[5]);
        Assert.assertEquals(result[6], result[7]);
        Assert.assertEquals(result[8], result[9]);
    }

    /**
     * These data is used in the first Test to search flights
     */
    @DataProvider(name = "flightData")
    public Object[][] getDays() {
        String origen = "LAS";
        String destino = "LAX";
        LocalDate today = LocalDate.now();
        LocalDate futureGo = today.plus(60, ChronoUnit.DAYS);
        LocalDate futureBack = futureGo.plus(60, ChronoUnit.DAYS);
        Object[][] data = new Object[1][4];
        data[0][0] = origen;
        data[0][1] = destino;
        data[0][2] = futureGo;
        data[0][3] = futureBack;
        return data;
    }

    /**
     * These data is used to validate the information of the ticket
     */
    @DataProvider(name = "cities")
    public Object[][] getCities() {
        String origen = "LAS";
        String destino = "LAX";
        Object[][] data = new Object[1][2];
        data[0][0] = origen;
        data[0][1] = destino;
        return data;
    }

    /**
     * These data is used to compare the information of the listbox when return the results of the departure
     */
    @DataProvider(name = "listoBox")
    public Object[][] dropDown() {
        Object[][] data = new Object[1][4];
        data[0][0] = "Price";
        data[0][1] = "Duration";
        data[0][2] = "Departure";
        data[0][3] = "Arrival";
        return data;
    }
}

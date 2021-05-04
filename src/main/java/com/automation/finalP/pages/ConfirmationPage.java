package com.automation.finalP.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

/**
 * Confirmation page class handle the request of the the ticket selected
 *
 * @author Ella.Gerstner
 */

public class ConfirmationPage extends BasePage {
    /*Locator to identify the details of the departure and arrival flight*/
    @FindBy(css = "div[class='uitk-flex uitk-flex-column uitk-flex-gap-twelve']")
    private List<WebElement> result;
    /*Locator to be used to display details of the flights*/
    @FindBy(css = "button[aria-label*='Show details of']")
    private WebElement showDetails;
    /*Locator where displays the origin and destiny of the flights*/
    @FindBy(css = "p span")
    private List<WebElement> place;
    /*Locator where let know the total price of the flight*/
    @FindBy(xpath = "//h3[contains(text(),'Trip total')]")
    private WebElement totalAmount;
    /*Locator of the continue wiht the buy process*/
    @FindBy(css = "button[data-test-id='goto-checkout-button']")
    private WebElement button;

    /**
     * Constructor of the class
     *
     * @author Ella.Gerstner
     */
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This method change the child window control to made the validations of the flight
     *
     * @param origen
     * @param destino
     */
    public String confirmFlight(String origen, String destino) {
        String parent = getDriver().getWindowHandle();
        Set<String> allWindows = getDriver().getWindowHandles();
        for (String child : allWindows) {
            if (!parent.equalsIgnoreCase(child)) {
                getDriver().switchTo().window(child);
            }
            for (WebElement respu : result) {
                respu.findElement(By.cssSelector("button[aria-label*='Show details of']")).click();

                for (WebElement cities : place) {
                    if (cities.getText().contains(origen) || cities.getText().contains(destino)) {
                        break;
                    }
                }
            }
        }
        return "Trip total," + totalAmount.getText();
    }

    /**
     * Method to continue the purchase of the ticket
     */
    public void checkButton() {
        getDriver().navigate().refresh();
        getWait().until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }
}


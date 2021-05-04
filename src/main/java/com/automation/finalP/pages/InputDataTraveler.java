package com.automation.finalP.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Input data traveler class
 *
 * @author Ella.Gerstner
 */

public class InputDataTraveler extends BasePage {
    /*Locator to identify the traveler information*/
    @FindBy(css = "#preferences h2")
    private WebElement traveling;
    /*Locator to identify the details of the flights*/
    @FindBy(css = "h2[class='product-title']")
    private WebElement roundtrip;
    /*Locator where find Protect section*/
    @FindBy(css = ".title-main")
    private WebElement protect;
    /*Locator where find the More Information section*/
    @FindBy(css = ".email-input h3")
    private WebElement sendInfo;
    /*Locator od the element to has the information of the total price*/
    @FindBy(css = ".pricing_summary_wrapper h3")
    private WebElement totalPrice;

    /**
     * Constructor of the class
     */
    public InputDataTraveler(WebDriver driver) {
        super(driver);
    }

    /**
     * Method to validate the additional information required to continue the purchase process
     */
    public String validateData() {
        String cadena;
        cadena = "Who's traveling?," + traveling.getText();
        cadena += ",Roundtrip flight," + roundtrip.getText();
        cadena += ",Protect your flight Recommended," + protect.getText().trim();
        cadena += ",Where should we send your confirmation?," + sendInfo.getText();
        cadena += ",Your price summary," + totalPrice.getText();
        return cadena;
    }
}

package com.automation.finalP.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor method.
     *
     * @param pDriver
     *
     * @author Ella.Gerstner
     */
    public BasePage(WebDriver pDriver) {
        PageFactory.initElements(pDriver, this);
        //wait implícito..
        wait = new WebDriverWait(pDriver, 10);
        driver = pDriver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void dispose() {
        if (driver != null) {
            driver.quit();
        }
    }
}

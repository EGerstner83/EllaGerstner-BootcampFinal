package com.automation.finalP.drives;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * MyDriver class
 *
 * @author Ella.Gerstner
 */
public class MyDrive {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * This method call to the browser that was sent by .xml files
     *
     * @param String browser
     *
     */
    public MyDrive(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "/Users/ella.gerstner/Documents/InstallersJava/drivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/ella.gerstner/Documents/InstallersJava/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            default:
                break;
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}

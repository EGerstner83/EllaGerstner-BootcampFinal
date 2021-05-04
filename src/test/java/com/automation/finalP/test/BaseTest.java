package com.automation.finalP.test;

import com.automation.finalP.drives.MyDrive;
import com.automation.finalP.pages.TravelocityHome;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
// TODO: Auto-generated Javadoc

/**
 *BaseTest
 *
 * @author Ella.Gerstner
 */
public class BaseTest {
    MyDrive myDriver;

    private TravelocityHome travelocityHome;

    /**
     * Method to call the url and and which browser will be used
     *
     * @author Ella.Gerstner
     *
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser"})
    public void beforeSuite(String browser) {
        myDriver = new MyDrive(browser);
        travelocityHome = new TravelocityHome(myDriver.getDriver());
    }
    /**
     * This method close the browser when the test had been executed
     *
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        travelocityHome.dispose();
    }

    public TravelocityHome getTravelocityHome() {
        return travelocityHome;
    }
}

package com.automation.finalP.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
// TODO: Auto-generated Javadoc
/**
 * CoolListener lass
 *Let you knoe when a test starts, finish, fail o passed
 */

public class CoolListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }
    /**
     * Let you know if the test passed
     *
     * @param iTestResult:ITestResut
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("The test was a success:)");
    }

    /**
     * Let you know if the test failed
     *
     * @param iTestResult:ITestResut
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The test Fail :(");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    /**
     * Let you know if begin
     *
     * @param iTestContext : ITestContext
     */
    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("The test begin:)");
    }
    /**
     * Let you know if finished
     *
     * @param iTestContext : ITestContext
     */
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("The test finish :)");
    }
}

package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("***Test started: " + iTestResult.getName());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("***Test was successful: " + iTestResult.getName());
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("***Test failed: " + iTestResult.getName());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("***Test skipped: " + iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("***Test suite started: " + iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("***All the tests are finished: " + iTestContext.getName());
    }
}

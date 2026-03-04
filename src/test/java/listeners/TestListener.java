package listeners;

import base.BaseTest;
import com.aventstack.extentreports.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String screenshotPath = ScreenshotUtil.captureScreenshot(
                BaseTest.getDriver(),
                result.getMethod().getMethodName()
        );

        test.get().pass("Test Passed")
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String screenshotPath = ScreenshotUtil.captureScreenshot(
                BaseTest.getDriver(),
                result.getMethod().getMethodName()
        );

        test.get().fail(result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String screenshotPath = ScreenshotUtil.captureScreenshot(
                BaseTest.getDriver(),
                result.getMethod().getMethodName()
        );

        test.get().skip("Test Skipped")
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.setSystemInfo("Total Tests", String.valueOf(context.getAllTestMethods().length));
        extent.setSystemInfo("Passed", String.valueOf(context.getPassedTests().size()));
        extent.setSystemInfo("Failed", String.valueOf(context.getFailedTests().size()));
        extent.setSystemInfo("Skipped", String.valueOf(context.getSkippedTests().size()));

        extent.flush();
    }
}

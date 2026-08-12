package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;
import utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG listener that integrates ExtentReports and Log4j2.
 * Captures screenshots on test failure.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished: " + context.getName());
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("STARTING TEST: " + testName);
        ExtentTest test = ExtentManager.getReporter().createTest(testName, result.getMethod().getDescription());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("PASSED: " + testName);
        ExtentManager.getTest().log(Status.PASS, "Test Passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.error("FAILED: " + testName);
        logger.error(result.getThrowable().getMessage());
        
        ExtentTest test = ExtentManager.getTest();
        test.log(Status.FAIL, "Test Failed: " + testName);
        test.log(Status.FAIL, result.getThrowable());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                new File(SCREENSHOT_DIR).mkdirs();
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = testName + "_" + timestamp + ".png";
                String relativePath = "screenshots/" + fileName;
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(src.toPath(), Paths.get(SCREENSHOT_DIR + fileName));
                logger.info("Screenshot captured: " + relativePath);
                test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            } catch (IOException e) {
                logger.error("Failed to capture screenshot", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.warn("SKIPPED: " + testName);
        ExtentManager.getTest().log(Status.SKIP, "Test Skipped: " + testName);
        if (result.getThrowable() != null) {
            ExtentManager.getTest().log(Status.SKIP, result.getThrowable());
        }
    }
}

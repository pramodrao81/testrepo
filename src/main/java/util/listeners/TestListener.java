package util.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import util.reports.ExtentManager;
import util.reports.ExtentTestManager;

import java.io.File;
import java.io.FileNotFoundException;

public class TestListener implements ITestListener {
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }


    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");

        String targetLocation = null;
        String currTime = String.valueOf(System.currentTimeMillis());
        String testClassName = result.getInstanceName().trim();
        String testMethodName = result.getName().toString().trim();
        String screenShotName = testMethodName + currTime + ".png";
        String fileSeperator = System.getProperty("file.separator");
        String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
                                     + "screenshots";
        System.out.println("Screen shots reports path - " + reportsPath);
        try {
            File file = new File(reportsPath + fileSeperator + testClassName); // Set
            // screenshots
            // folder
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
                } else {
                    System.out.println("Failed to create directory: " + file.getAbsolutePath());
                }

            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
            // location
            File targetFile = new File(targetLocation);
            System.out.println("Screen shot file location - " + screenshotFile.getAbsolutePath());
            System.out.println("Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

        } catch (FileNotFoundException e) {
            System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An exception occurred while taking screenshot " + e.getCause());
        }
    }
}

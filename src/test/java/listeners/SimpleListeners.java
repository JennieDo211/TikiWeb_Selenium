package listeners;
import Utils.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SimpleListeners implements ITestListener {
    public void onTestStart(org.testng.ITestResult result) { /* compiled code */ }
    public void onTestSuccess(org.testng.ITestResult result) { /* compiled code
     */ }
    public void onTestFailure(ITestResult result) {

        // In ra ten test case
        System.out.println("Screenshot captured for test case: " + result.getName());
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        // Ép webdriver thành takes screen shot. getScreenshot là dùng để chụp hình
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            Date date = new Date();
            String filePath = "." + File.separator + "screenshots" + File.separator + result.getName() + "-" + date.getTime() + ".png";
            File destFile = new File(filePath);
            destFile.getParentFile().mkdirs();

            FileHandler.copy(srcFile, destFile);
            System.out.println("Screenshot saved: " + filePath);

        }
        catch (IOException e)
        { e.printStackTrace();}
    }
    public void onTestSkipped(org.testng.ITestResult result) { /* compiled code */
    }
    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult
                                                               result) { /* compiled code */ }
    public void onTestFailedWithTimeout(org.testng.ITestResult result) { /*
compiled code */ }
    public void onStart(org.testng.ITestContext context) { /* compiled code */ }
    public void onFinish(org.testng.ITestContext context) { /* compiled code */ }
}

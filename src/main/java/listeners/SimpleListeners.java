package listeners;
import org.testng.ITestListener;
public class SimpleListeners implements ITestListener {
    public void onTestStart(org.testng.ITestResult result) { /* compiled code */ }
    public void onTestSuccess(org.testng.ITestResult result) { /* compiled code
     */ }
    public void onTestFailure(org.testng.ITestResult result) {
        System.out.println("This test is Failed");
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

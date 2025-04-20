package Mobile_Scripts;

import Mobile_Scripts.BaseMobileTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;

public class Mobile_Demo01 extends BaseMobileTest {
    @Test
    public void testMobileDemo(){
        var el1 = driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button"));
        el1.click();

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(466, 1881);
        var end = new Point (466, 1112);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));


        start = new Point(418, 2041);
        end = new Point (399, 1108);
        swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));


        start = new Point(515, 1735);
        end = new Point (511, 1254);
        swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

        var el2 = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"Thực phẩm bổ sung Aptamil Profutura Kid Cesarbiotik 3 Growing Up Milk Formula (Dành cho trẻ từ 24 tháng tuổi trở lên) - 800g\")"
        ));
        el2.click();


    }
}

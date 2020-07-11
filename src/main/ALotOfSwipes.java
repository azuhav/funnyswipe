package main;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Duration;

public class ALotOfSwipes extends DriverAgent {

    @Test
    public void swipeIt() throws InterruptedException {

        try {
            driver.startRecordingScreen(new AndroidStartScreenRecordingOptions().withTimeLimit(Duration.ofSeconds(65)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Closing the "yes, I've previously used iFunny" message

        Thread.sleep(5000);

        AndroidElement messageOne = (AndroidElement) driver
                .findElementByAndroidUIAutomator("new UiSelector().text(\"yes, I've previously used iFunny\")");
        messageOne.click();

        // Closing the "just slide & smile" message
        AndroidElement messageTwo = (AndroidElement) driver
                .findElementByAndroidUIAutomator("new UiSelector().text(\"just slide & smile\")");
        messageTwo.click();

        Thread.sleep(5000);

        // Closing the "add icon message" message if appeared
        try {
            AndroidElement messageThree = (AndroidElement) driver
                    .findElementByAndroidUIAutomator("new UiSelector().text(\"NO THANKS\")");
            messageThree.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        try {
            AndroidElement messageThree = (AndroidElement) driver
                    .findElementByAndroidUIAutomator("new UiSelector().text(\"CANCEL\")");
            messageThree.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TouchAction action = new TouchAction(driver);

//        List<AppiumWebElement> cells = (List<WebElement>) driver
//                .findElementsByAndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\")");
//      Variant 1
/*
        WebElement bar = driver
                .findElementByAndroidUIAutomator("new UiSelector().resourceId(\"mobi.ifunny:id/subscribeButton\")");

        Dimension barSize = bar.getSize();

        int y = barSize.getHeight() - 2;
        int x = barSize.getWidth() - 10;

        Point barLocation = bar.getLocation();

        int newX = barLocation.getX() + x;
        int newY = barLocation.getY() + y;

        long startTime = System.currentTimeMillis();

        while (false || (System.currentTimeMillis() - startTime) < 480000) {
            try {
                action.longPress(new PointOption().withCoordinates(newX, newY));
                action.moveTo(new PointOption().withCoordinates(newX - 700, newY));
                action.release();
                action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
                action.perform();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */
//      Variant 2
//      Getting x and y for postion 1

        WebElement subscribeButtonTapZone = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"mobi.ifunny:id/subscribeButtonTapZone\")");

        Point subscribeButtonTapZoneLocation = subscribeButtonTapZone.getLocation();

        int startX = subscribeButtonTapZoneLocation.getX();
        int startY = subscribeButtonTapZoneLocation.getY();

        WebElement authorNickZone = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"mobi.ifunny:id/authorNick\")");

        Point authorNickZoneLocation = authorNickZone.getLocation();

        int finishX = authorNickZoneLocation.getX();
        int finishY = startY;

        long startTime = System.currentTimeMillis();

        while (false || (System.currentTimeMillis() - startTime) < 60000) {
            try {
                action.longPress(new PointOption().withCoordinates(startX, startY));
                action.moveTo(new PointOption().withCoordinates(finishX, finishY));
                action.release();
                action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
                action.perform();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Thread.sleep(5000);
        }
    }
}

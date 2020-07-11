package main;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverAgent {

	public AndroidDriver<MobileElement> driver;

	@BeforeTest
	@Parameters({ "devName", "sysPort" })
	public void setUp(String devName, String sysPort) {

		DesiredCapabilities serverCapabilities = new DesiredCapabilities();
		serverCapabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");		
		serverCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		serverCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, devName);
		serverCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
		serverCapabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
		serverCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.usingAnyFreePort();
		builder.withCapabilities(serverCapabilities);
		AppiumDriverLocalService service = builder.build();

		DesiredCapabilities clientCapabilities = new DesiredCapabilities();
		clientCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "mobi.ifunny");
		clientCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "mobi.ifunny.main.MenuActivity");
		clientCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "30");
		clientCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, sysPort);

		driver = new AndroidDriver<>(service, clientCapabilities);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

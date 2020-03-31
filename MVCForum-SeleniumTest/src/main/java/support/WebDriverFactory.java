package support;

import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

class WebDriverFactory {

	private static final Map<String, Class<? extends RemoteWebDriver>> drivers = Map
			.of("chrome", ChromeDriver.class, "firefox", FirefoxDriver.class, "safari", SafariDriver.class);

	private static final int TIMEOUT = 500;

	static RemoteWebDriver newRemoteWebDriver(String browser) {
		Class<? extends RemoteWebDriver> driverClass = drivers.get(browser);
		if (driverClass == null) {
			throw new IllegalArgumentException("Not supported browser " + browser);
		}
		var remoteWebDriver = ReflectionUtils.newInstance(driverClass);
		remoteWebDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.MILLISECONDS);
		return remoteWebDriver;
	}
}

package support;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriverBuilder;

public class BrowserParameterResolver implements BeforeAllCallback, ParameterResolver, AfterEachCallback {

	@Override
	public void beforeAll(ExtensionContext context) {
		setupWebDriver(context);
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return parameterContext.getParameter().getType() == Browser.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return getBrowser(extensionContext);
	}

	@Override
	public void afterEach(ExtensionContext context) {
		getBrowser(context).quit();
	}

	private void setupWebDriver(ExtensionContext context) {
		try {
			var driverManagerType = DriverManagerType.valueOf(getConfigParameter(context).toUpperCase());
			WebDriverManager.getInstance(driverManagerType).setup();

		} catch (IllegalArgumentException e) {
			// driver not supported by WebDriverManager, skip and assume that it may be configured by different means
		}
	}

	private String getConfigParameter(ExtensionContext context) {
		var browser = context.getConfigurationParameter("browser");
		return browser.orElseThrow(
				() -> new ExtensionConfigurationException("'browser' configuration parameter not present"));
	}

	private Browser getBrowser(ExtensionContext context) {
		return context.getStore(ExtensionContext.Namespace.create(BrowserParameterResolver.class))
				.getOrComputeIfAbsent("browserObject", key -> {
					var browserParam = getConfigParameter(context);
					var remoteWebDriver = WebDriverFactory.newRemoteWebDriver(browserParam);
					return new Browser(remoteWebDriver);
				}, Browser.class);
	}
}

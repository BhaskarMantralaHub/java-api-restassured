import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    private static final Supplier<WebDriver> CHROME = ChromeDriverWithOptions::getDriver;

    private static final Supplier<WebDriver> FIREFOX = () -> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    private static final Map<BrowserType, Supplier<WebDriver>> WEBDRIVER_MAP = new EnumMap<>(BrowserType.class);

    static {
        WEBDRIVER_MAP.put(BrowserType.CHROME, CHROME);
        WEBDRIVER_MAP.put(BrowserType.FIREFOX, FIREFOX);
    }

    public static WebDriver getDriver(BrowserType browser) {
        return WEBDRIVER_MAP.get(browser).get();
    }

}

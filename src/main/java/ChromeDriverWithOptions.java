import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeDriverWithOptions {

    private ChromeDriverWithOptions() {
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        chromeOptions.setPlatformName("Windows");
        chromeOptions.addArguments("-window-size=1920,1080");
        return chromeOptions;
    }

    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions());
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        return chromeDriver;
    }

}

package enums;

import org.openqa.selenium.InvalidArgumentException;

import java.util.Arrays;

public enum BrowserType {
    CHROME,
    FIREFOX;

    public static BrowserType get(String name) {
        return Arrays.stream(BrowserType.values())
                .filter(browserType -> browserType.name().equals(name.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new InvalidArgumentException("Invalid browser name - " + name + ". Supported browsers - " + Arrays.toString(BrowserType.values())));
    }
}

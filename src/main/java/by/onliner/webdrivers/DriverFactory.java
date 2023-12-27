package by.onliner.webdrivers;

import java.util.EnumMap;

import org.openqa.selenium.WebDriver;

public final class DriverFactory {
    /**
     * Private constructor for {@link DriverFactory} utility class.
     */
    private DriverFactory() {
    }

    /**
     * Gets a Web Driver instances based in a Driver Type parameter.
     *
     * @param type Driver Type enum value.
     * @return Web Driver instance.
     */
    public static WebDriver getDriver(final DriverType type) {
        EnumMap<DriverType, Browser> driverMap = new EnumMap<>(DriverType.class);
        driverMap.put(DriverType.CHROME, new ChromeBrowser());
        driverMap.put(DriverType.FIREFOX, new FirefoxBrowser());
        driverMap.put(DriverType.REMOTE, new RemoteBrowser());
        return driverMap.get(type).getDriver();
    }
}

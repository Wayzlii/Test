package autotest.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetDriver {

    public static WebDriver getDriver(Browsers browser, String... options) {

        switch (browser) {
            case CHROME -> {
                ChromeOptions option = new ChromeOptions();
                option.addArguments(options);
                return new ChromeDriver(option);
            }
            case FOX -> {
                FirefoxOptions option = new FirefoxOptions();
                option.addArguments(options);
                return new FirefoxDriver(option);
            }
            case EDGE -> {
                EdgeOptions option = new EdgeOptions();
                option.addArguments(options);
                return new EdgeDriver(option);
            }
        }
        return null;
    }
    public static void getDriverManager (Browsers browser) {
        switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
            }
            case FOX -> {
                WebDriverManager.firefoxdriver().setup();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
            }
        }
    }
}

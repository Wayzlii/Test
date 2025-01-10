package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetDriver {

    public WebDriver getDriver(Browsers browser, String... options) {

        switch (browser) {
            case CHROME -> {
                ChromeOptions option = new ChromeOptions();
                option.addArguments(options);
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(option);
            }
            case FOX -> {
                FirefoxOptions option = new FirefoxOptions();
                option.addArguments(options);
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(option);
            }
            case EDGE -> {
                EdgeOptions option = new EdgeOptions();
                option.addArguments(options);
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(option);
            }
        }
        return null;
    }
}

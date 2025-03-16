package otus.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Configuration
public class WebDriverFactory {

    @Bean(name = "webDriver", destroyMethod = "quit")
    public WebDriver createWebDriver(@Value("${browser.name}") String browserName) {
        browserName = browserName.trim().toLowerCase();
        switch (browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case "fox" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            default -> throw new BrowserNotSupportedException(browserName);
        }
    }
}

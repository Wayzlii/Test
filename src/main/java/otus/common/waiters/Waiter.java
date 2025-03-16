package otus.common.waiters;

import lombok.SneakyThrows;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Waiter {

    private final WebDriverWait webDriverWait;
    private final WebDriver driver;

    public Waiter(WebDriver driver,
                  @Value("${webdriver.waiter.timeout}") int duration) {
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        this.driver = driver;
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        try {
            webDriverWait.until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public boolean waitForElementClickableByLocator(WebElement element) {
        return this.waitForCondition(ExpectedConditions.elementToBeClickable(element));
    }

    @SneakyThrows
    public boolean waitForTime() {
        Thread.sleep(5000);
        return true;
    }

}

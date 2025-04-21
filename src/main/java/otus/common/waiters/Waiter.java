package otus.common.waiters;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Waiter {

    private final WebDriverWait webDriverWait;

    public Waiter(WebDriver driver) {
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        try {
            webDriverWait.until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public boolean waitForElementClickable(WebElement element) {
        return this.waitForCondition(ExpectedConditions.elementToBeClickable(element));
    }
}

package otus.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import otus.common.waiters.Waiter;

public abstract class AbsCommon {
    protected final WebDriver driver;
    protected final Waiter waiter;

    public AbsCommon(WebDriver driver, Waiter waiter) {
        this.driver = driver;
        this.waiter = waiter;
        PageFactory.initElements(driver,this);
    }
}

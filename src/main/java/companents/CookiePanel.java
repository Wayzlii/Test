package companents;

import common.AbsCommon;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CookiePanel extends AbsCommon {
    public CookiePanel(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class=\"sc-11pdrud-1 cmIXWc\"]/div/button")
    private WebElement consentButton;
    public void clickConsentBtn() {
        try {
            consentButton.click();
        } catch (NoSuchElementException ignored){}
    }
}

package companents;

import common.AbsCommon;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CookiePanel extends AbsCommon {
    public CookiePanel(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "((//*[contains(text(),\"Посещая наш сайт, вы принимаете\")]/ancestor::div)[last()])/descendant::button")
    private WebElement consentButton;
    public void clickConsentBtn() {
        try {
            ExpectedConditions.elementToBeClickable(consentButton).apply(driver).click();
        } catch (NoSuchElementException ignored){}
    }
}

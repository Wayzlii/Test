package companents;

import common.AbsCommon;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SalePanel extends AbsCommon {

    public SalePanel(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class=\"sticky-banner__right\"]/div")
    private WebElement closeSale;
    public void clickCloseSale() {
        try {
            ExpectedConditions.elementToBeClickable(closeSale).apply(driver).click();
        } catch (NoSuchElementException ignored) {}
    }
}

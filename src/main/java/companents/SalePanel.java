package companents;

import common.AbsCommon;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalePanel extends AbsCommon {

    public SalePanel(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class=\"sticky-banner__close js-sticky-banner-close\"]")
    private WebElement closeSale;
    public void clickCloseSale() {
        try {
            closeSale.click();
        } catch (NoSuchElementException ignored) {}
    }
}

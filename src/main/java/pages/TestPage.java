package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TestPage extends AbsBasePage {

    @FindAll({@FindBy(xpath = "//div[contains(@class,\"sc-18q05a6-1\") and contains(@class,\"bwGwUO\")]/a")})
    private List<WebElement> cardElements;

    public TestPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> elementsList() {
        return cardElements;
    }

    public CardPage clickElement(String href) {
        for (WebElement element : cardElements) {
            if (element.getDomProperty("href").endsWith(href)) {
                ExpectedConditions.elementToBeClickable(element).apply(driver).click();
                return new CardPage(driver);
            }
        }
        throw new IllegalArgumentException("Элемент не найден.");
    }
}

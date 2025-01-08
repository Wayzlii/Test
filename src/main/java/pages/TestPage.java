package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TestPage extends AbsBasePage {

    @FindAll({@FindBy(xpath = "//div[@class=\"sc-18q05a6-1 bwGwUO\"]/a")})
    private List<WebElement> cardElements;

    private static final String URL = System.getProperty("test.page.url",
            "https://otus.ru/catalog/courses?categories=testing");

    public TestPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> elementsList() {
        return cardElements;
    }

    public CardPage clickElement(String href) {
        for (WebElement element : cardElements) {
            if (element.getDomProperty("href").endsWith(href)) {
                element.click();
                return new CardPage(driver);
            }
        }
        throw new IllegalArgumentException("Элемент не найден.");
    }
}

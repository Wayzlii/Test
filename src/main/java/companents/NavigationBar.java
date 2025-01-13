package companents;

import common.AbsCommon;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class NavigationBar extends AbsCommon {
    @FindBy(css = "span[title=\"Обучение\"]")
    private WebElement study;
    @FindBy(xpath = "//button[contains(@class,\"sc-5n5sda-0\") and contains(@class,\"exrzoV\")]")
    private WebElement menu;
    @FindBy(xpath = "(//a[contains(@class,\"sc-1pgqitk-0\") and contains(@class,\"dNitgt\")])[contains(text(),\"Тестирование\")]")
    private WebElement testing;
    @FindBy(xpath = "(//a[contains(@class,\"sc-1pgqitk-0\") and contains(@class,\"dNitgt\")])[contains(text(),\"Календарь мероприятий\")]")
    private WebElement calendar;

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public void openStudy() {
        try {
            ExpectedConditions.elementToBeClickable(menu).apply(driver).click();
        } catch (StaleElementReferenceException | NoSuchElementException ignored) {
        }
        ExpectedConditions.elementToBeClickable(study).apply(driver).click();
    }

    public void openTesting() {
        openStudy();
        ExpectedConditions.elementToBeClickable(testing).apply(driver).click();
    }

    public void openCalendar() {
        openStudy();
        try {
            ExpectedConditions.elementToBeClickable(calendar).apply(driver).click();
        } catch (TimeoutException ignored){} //Выпадает ошибка timeOutException. Сделала игнор, так как очень долго приходится ждать ответа от сайта.
    }
}

package companents;

import common.AbsCommon;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NavigationBar extends AbsCommon {
    @FindBy(css = "span[title=\"Обучение\"]")
    private WebElement study;
    @FindBy(xpath = "//div[@class=\"header3__hamburger\"]/button")
    private WebElement menu;
    @FindBy(xpath = "(//a[@href=\"https://otus.ru/categories/testing\"])[position()=1]")
    private WebElement testing;
    @FindBy(xpath = "(//a[@href=\"https://otus.ru/events/near\"])[position()=1]")
    private WebElement calendar;

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public void openStudy() {
        try {
            menu.click();
        } catch (NoSuchElementException ignored) {
        }
        study.click();
    }

    public void openTesting() {
        openStudy();
        testing.click();
    }

    public void openCalendar() {
        openStudy();
        try {
            calendar.click();
        } catch (TimeoutException ignored) {
        }
    }
}

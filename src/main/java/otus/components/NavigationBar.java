package otus.components;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;
import otus.common.AbsCommon;
import otus.common.waiters.Waiter;

import java.util.List;
import java.util.Random;

@Component
public class NavigationBar extends AbsCommon {

    private final Random random = new Random();

    @Getter
    private String courseCategoryName;

    public NavigationBar(WebDriver driver,
                         Waiter waiter) {
        super(driver, waiter);
    }

    @FindBy(xpath = "//span[contains(text(),'Обучение')]")
    private WebElement study;

    @FindBy(xpath = "//button[contains(@class,\"sc-5n5sda-0\") and contains(@class,\"exrzoV\")]")
    private WebElement menu;

    @FindAll({@FindBy(xpath = "//div/child::p[contains(text(),'Все курсы')]/parent::*/div/a")})
    private List<WebElement> coursesCategory;

    public NavigationBar openStudy() {
        try {
            ExpectedConditions.elementToBeClickable(study).apply(driver).click();
        } catch (StaleElementReferenceException | NoSuchElementException ignored) {
            ExpectedConditions.elementToBeClickable(menu).apply(driver).click();
        }
        return this;
    }

    public NavigationBar clickCourseCategory() {
        int courseCategoryNum = random.nextInt(0, coursesCategory.size());
        courseCategoryName = coursesCategory.get(courseCategoryNum).getText();
        courseCategoryName = courseCategoryName.split("\\(")[0].trim();
        coursesCategory.get(courseCategoryNum)
                .click();
        return this;
    }

}

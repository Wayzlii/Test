package otus.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.common.waiters.Waiter;
import otus.components.NavigationBar;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
public class CoursePage extends AbsBasePage<CoursePage> {

    private final String courseUrl;

    private final CardPage cardPage;

    @FindAll(@FindBy(xpath = "//p[contains(text(), 'Направление')]/parent::div/following-sibling::div/div/div/div"))
    private List<WebElement> coursePrograms;

    @FindBy(xpath = "(//section)[position()=3]")
    private WebElement panel;

    @FindBy(xpath = "//div/button[contains(text(),'Показать еще')]")
    WebElement btnShowMore;

    public CoursePage(WebDriver driver,
                      Waiter waiter,
                      @Value("${base.url}") String baseUrl,
                      @Value("${catalog.course.url}") String courseUrl,
                      CardPage cardPage,
                      NavigationBar navigationBar) {
        super(driver, waiter, baseUrl, navigationBar);
        this.courseUrl = courseUrl;
        this.cardPage = cardPage;
    }

    public CoursePage selectCourseProgram(String courseName) {
        Optional<WebElement> foundCourseElement = coursePrograms.stream()
                .filter(element -> element.getText().equals(courseName))
                .findFirst();
        assertThat(foundCourseElement.isPresent()).isTrue();
        assertThat(foundCourseElement.get().getText()).as("").isEqualTo(courseName);
        scrollToElement(foundCourseElement.get());
        foundCourseElement.get()
                .click();
        return this;
    }

    public CardPage clickCardProgram(String courseName) {
        List<WebElement> cardPrograms = panel.findElements(By.xpath("//section/div/div/a"));
        Optional<WebElement> foundCardElement = cardPrograms.stream()
                .filter(element ->
                        element.findElement(By.xpath("descendant::p")).getText().equals("Курс"))
                .filter(element ->
                        element.findElement(By.xpath("h6/div")).getText().equals(courseName))
                .findFirst();
        assertThat(foundCardElement.isPresent()).isTrue();
        scrollToElement(foundCardElement.get());
        foundCardElement.get().click();
        return cardPage;
    }

    public CoursePage checkCourseCategory() {
        WebElement element = driver.findElement(By.xpath(
                "//p[contains(text(), 'Направление')]/parent::div/following-sibling::div/div/div/div[contains(@value, 'true')]"
        ));
        assertThat(navigationBar.getCourseCategoryName()).isEqualTo(element.getText());
        return this;
    }

    public CoursePage waitForLoad() {
        waiter.waitForTime();
        return this;
    }
    public CoursePage openAllProgramCard() {
        while (waiter.waitForElementClickableByLocator(btnShowMore)) {
            scrollToElement(btnShowMore);
            btnShowMore.click();
        }
        return this;
    }

    // прокручивает страницу до необходимого элемента
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Override
    protected String getPath() {
        return courseUrl;
    }

    @Override
    protected CoursePage getCoursePage() {
        return this;
    }

}

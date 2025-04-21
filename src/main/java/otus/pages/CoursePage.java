package otus.pages;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.common.waiters.Waiter;
import otus.component.NavigationBar;
import otus.jsoup.Card;
import otus.jsoup.JsoupDocument;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
public class CoursePage extends AbsBasePage<CoursePage> {

    private final String courseUrl;
    private final CardPage cardPage;

    @FindBy(xpath = "//div/button[contains(text(),'Показать еще')]")
    private WebElement btnShowMore;

    @FindBy(xpath = "(//section)[position()=3]")
    private WebElement panel;

    public CoursePage(WebDriver driver, Waiter waiter,
                      @Value("${base.url}") String baseUrl,
                      @Value("${catalog.course.url}") String courseUrl,
                      CardPage cardPage,
                      NavigationBar navigationBar) {
        super(driver, waiter, baseUrl, navigationBar);
        this.courseUrl = courseUrl;
        this.cardPage = cardPage;
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

    public List<Card> minDateCards() {
        JsoupDocument jsoupDocument = new JsoupDocument(this);
        return jsoupDocument.getCardsMin();
    }

    public List<Card> maxDateCards() {
        JsoupDocument jsoupDocument = new JsoupDocument(this);
        return jsoupDocument.getCardsMax();
    }

    public Pair<Card, CardPage> openCard(Card card) {
        return Pair.of(card, cardPage.open(card.href()));
    }

    @Override
    protected String getPath() {
        return courseUrl;
    }

    public CoursePage openAllProgramCard() {
        while (waiter.waitForElementClickable(btnShowMore)) {
            scrollToElement(btnShowMore);
            btnShowMore.click();
        }
        return this;
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("""
                arguments[0].scrollIntoView(
                          {behavior: "auto", block: "center", inline: "center"}
                          );
                        """, element);

    }
}

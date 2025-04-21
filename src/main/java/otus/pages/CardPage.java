package otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.common.waiters.Waiter;
import otus.component.NavigationBar;
import otus.jsoup.Card;
import otus.util.DateUtil;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
public class CardPage extends AbsBasePage<CardPage> {

    @FindBy(xpath = "//h1")
    private WebElement courseName;

    public CardPage(WebDriver driver, Waiter waiter,
                    @Value("${base.url}") String baseUrl,
                    NavigationBar navigationBar) {
        super(driver, waiter, baseUrl, navigationBar);
    }

    @Override
    protected String getPath() {
        return "";
    }

    public CardPage checkCourseName(String expectedCourseName) {
        assertThat(courseName.getText().equals(expectedCourseName))
                .as("Имя курса соответствует искомому.")
                .isTrue();
        return this;
    }

    public CardPage checkCard(Card card) {
        LocalDate date = DateUtil.getDate(driver.findElement(By.xpath(
                        "//div/section/div[position()=3]/descendant::p[position()=1]")).getText(),
                LocalDate.now().getYear());
        assertThat(date)
                .isEqualTo(card.dateStart())
                .as("Дата курса верна");
        assertThat(driver.findElement(By.xpath("//h1")).getText())
                .isEqualTo(card.courseName())
                .as("Имя курса верное");
        return this;
    }

}

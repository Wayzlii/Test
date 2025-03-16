package otus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.common.waiters.Waiter;
import otus.components.NavigationBar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
public class CardPage extends AbsBasePage<CardPage>{

    @FindBy(xpath = "(//a[contains(@class, 'sc-1vxm7ng-2') and contains (@class, 'glONVl')])[position()=1]")
    private WebElement typeCourseOfCard;
    @FindBy (xpath = "//h1")
    private WebElement courseName;

    public CardPage(WebDriver driver,
                    Waiter waiter,
                    @Value("${base.url}") String baseUrl,
                    NavigationBar navigationBar) {
        super(driver, waiter, baseUrl, navigationBar);
    }
    @Override
    protected String getPath() {
        return "";
    }

    @Override
    protected CoursePage getCoursePage() {
        return null;
    }

    public CardPage checkTypeCourseOfCard(String courseName) {
        assertThat(courseName.equals(typeCourseOfCard.getText()))
                .as("Название типа курса соттветсвует выбранному.")
                .isTrue();
        return this;
    }
    public CardPage checkCourseName(String expectedCourseName) {
        assertThat(courseName.getText().equals(expectedCourseName))
                .as("Имя курса соответствует искомому.")
                .isTrue();
        return this;
    }


}

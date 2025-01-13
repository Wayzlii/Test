package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CalendarPage extends AbsBasePage {

    @FindAll({@FindBy(xpath = "//div[@class=\"dod_new-event__time\"]/span[1]/span[2]")})
    private List<WebElement> cardDates;
    @FindBy(xpath = "(//div[@class=\"dod_new-events-dropdown__input\"])[position()=1]")
    private WebElement eventTypes;
    @FindBy(xpath = "((//div[@class=\"dod_new-events-dropdown__list js-dod_new_events-dropdown\"])/a[contains(text(),\"Открытый вебинар\")])[1]")
    private WebElement btnOpenWebinar;
    @FindAll({@FindBy(xpath = "//div[@class=\"dod_new-type__text\"]")})
    private List<WebElement> cardTypes;

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    public List<LocalDate> getDate() {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate date;
        for (WebElement element : cardDates) {
            String dateString = element.getText();
            String[] stringArr = dateString.split(" ");
            date = LocalDate.of(LocalDate.now().getYear(), monthNum(stringArr[1]), Integer.parseInt(stringArr[0]));
            dates.add(date);
        }
        return dates;
    }

    public void selectOpenWebinarType() {
        ExpectedConditions.elementToBeClickable(eventTypes).apply(driver);
        ExpectedConditions.elementToBeClickable(btnOpenWebinar).apply(driver);
        //click() убран, работает без него, так как при проверке метод кликает
    }

    public List<String> listCardTypes() {
        List<String> listCardTypes = new ArrayList<>();
        for (WebElement element : cardTypes) {
            String cardType = element.getText();
            listCardTypes.add(cardType);
        }
        return listCardTypes;
    }

    private Month monthNum(String month) {
        return switch (month) {
            case "января" -> Month.JANUARY;
            case "февраля" -> Month.FEBRUARY;
            case "марта" -> Month.MARCH;
            case "апреля" -> Month.APRIL;
            case "мая" -> Month.MAY;
            case "июня" -> Month.JUNE;
            case "июля" -> Month.JULY;
            case "августа" -> Month.AUGUST;
            case "сентября" -> Month.SEPTEMBER;
            case "октября" -> Month.OCTOBER;
            case "ноября" -> Month.NOVEMBER;
            case "декабря" -> Month.DECEMBER;
            default -> throw new IllegalArgumentException("Переданное значение некорректно.");
        };
    }
}

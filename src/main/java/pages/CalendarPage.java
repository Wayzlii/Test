package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarPage extends AbsBasePage {

    @FindAll({@FindBy(xpath = "//div[@class=\"dod_new-event__time\"]/span[1]/span[2]")})
    private List<WebElement> cardDates;
    @FindBy(xpath = "(//div[@class=\"dod_new-events-dropdown__input\"])[position()=1]")
    private WebElement eventTypes;
    @FindBy(xpath = "(//div[@class=\"dod_new-events-dropdown__list js-dod_new_events-dropdown\"])[position()=1]/a[position()=4]")
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
        eventTypes.click();
        try {
            btnOpenWebinar.click();
        } catch (TimeoutException ignored) {
        }
    }

    public List<String> listCardTypes() {
        List<String> listCardTypes = new ArrayList<>();
        for (WebElement element : cardTypes) {
            String cardType = element.getText();
            listCardTypes.add(cardType);
        }
        return listCardTypes;
    }

    private int monthNum(String month) {
        int numMonth = 0;
        switch (month) {
            case "января" -> numMonth = 1;
            case "февраля" -> numMonth = 2;
            case "марта" -> numMonth = 3;
            case "апреля" -> numMonth = 4;
            case "мая" -> numMonth = 5;
            case "июня" -> numMonth = 6;
            case "июля" -> numMonth = 7;
            case "августа" -> numMonth = 8;
            case "сентября" -> numMonth = 9;
            case "октября" -> numMonth = 10;
            case "ноября" -> numMonth = 11;
            case "декабря" -> numMonth = 12;
        }
        return numMonth;
    }

}

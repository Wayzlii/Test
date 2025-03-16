package otus.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import otus.OtusHomeworkMain;
import otus.pages.CoursePage;
import otus.pages.MainPage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {
        OtusHomeworkMain.class
})
public class OpenCourseCardTest {

    @Autowired
    CoursePage coursePage;

    @Autowired
    MainPage mainPage;

    @ParameterizedTest
    @CsvFileSource(resources = "/data_for_check_course_type.csv", delimiter = ',')
    @DirtiesContext
    public void findCourseAndCheckCourseNameWithTypeTest(String courseType, String courseName) {
        coursePage.open()
                .selectCourseProgram(courseType)
                .waitForLoad()
                .openAllProgramCard()
                .clickCardProgram(courseName)
                .checkTypeCourseOfCard(courseType)
                .checkCourseName(courseName);
    }

    @Test
    @DirtiesContext
    public void checkMinAndMaxTimeCourseTest() {
        coursePage.open()
                .openAllProgramCard();

        Document document = Jsoup.parse(coursePage.getPageSource());
        Elements elementCards = document.selectXpath(
                "//section/descendant::a[contains(@href,'/lessons/')]"
        );
        List<LocalDate> dates = getDates(elementCards);
        LocalDate minDate = dates.get(0);
        LocalDate maxDate = dates.get(dates.size() - 1);
        checkNinAndMaxDateOnCards(elementCards, minDate);
        checkNinAndMaxDateOnCards(elementCards,maxDate);
    }

    private List<LocalDate> getDates(Elements elementCards) {
        return elementCards.stream()
                .map(card -> card.selectXpath(
                        "descendant::div[contains(text(), 'месяц')]/text()",
                        TextNode.class
                ))
                .flatMap(textNodes -> textNodes.stream())
                .map(textNode -> textNode.text().replace(",", ""))
                .map(string -> getDate(string))
                .filter(localDate -> localDate != null)
                .sorted()
                .toList();
    }

    private void checkNinAndMaxDateOnCards(Elements elementCards, LocalDate date) {
        elementCards.stream()
                .filter(element -> {
                    List<TextNode> textNodeList = element.selectXpath(
                            "descendant::div[contains(text(), 'месяц')]/text()",
                            TextNode.class
                    );
                    if (textNodeList.isEmpty()) {
                        return false;
                    }
                    TextNode textNode1 = textNodeList.get(0);
                    String dateStr = textNode1.text().replace(",", "");
                    LocalDate localDate = getDate(dateStr);
                    return date.equals(localDate);
                })
                .forEach(element -> {
                    Attribute href = element.attribute("href");
                    String courseName = element
                            .selectXpath("h6/div/text()", TextNode.class).get(0).text().trim();
                    String url = coursePage.getBaseUrl() + href.getValue();
                    Document cardDoc;
                    try {
                        cardDoc = Jsoup.connect(url).get();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    assertThat(cardDoc.selectXpath("//h1").text().trim()).isEqualTo(courseName);
                    LocalDate localDate =
                            getDate(cardDoc.selectXpath(
                                            "//div/section/div[position()=3]/descendant::p[position()=1]").text(),
                                    LocalDate.now().getYear()
                            );
                    assertThat(localDate).isEqualTo(date);
                });
    }

    @Test
    @DirtiesContext
    public void checkCatalogCoursesVisibilityTest() {
        mainPage.open()
                .openStudy()
                .clickCourseCategory()
                .checkCourseCategory();
    }

    public LocalDate getDate(String dateStr) {
        String[] stringArr = dateStr.split(" ");
        String year = stringArr[2];
        if (year.matches("[0-9]*"))
            return getDate(dateStr, Integer.parseInt(year));
        return null;
    }

    public LocalDate getDate(String dateStr, int year) {
        String[] stringArr = dateStr.split(" ");
        String day = stringArr[0];
        if (day.matches("[0-9]*")) {
            return LocalDate.of(year, monthNum(stringArr[1]), Integer.parseInt(day));
        }
        return null;
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

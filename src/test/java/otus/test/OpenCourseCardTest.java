package otus.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import otus.Main;
import otus.pages.CardPage;
import otus.pages.CoursePage;
import otus.pages.MainPage;

@SpringBootTest(classes = {
        Main.class
})
public class OpenCourseCardTest {

    @Autowired
    CoursePage coursePage;
    @Autowired
    CardPage cardPage;
    @Autowired
    MainPage mainPage;

    @Value("${course.name}")
    String courseName;

    @Test
    @DirtiesContext
    public void findCourseByNameInCoursePage_ClickOnCourse_CoursePageOpened() {
        coursePage.open()
                .openAllProgramCard()
                .clickCardProgram(courseName)
                .checkCourseName(courseName);
    }

    @Test
    @DirtiesContext
    public void checkCardsWithMinDate() {
        coursePage.open()
                .openAllProgramCard()
                .minDateCards()
                .stream()
                .peek(card -> coursePage.openCard(card))
                .forEach(card -> cardPage.checkCard(card));
    }

    @Test
    @DirtiesContext
    public void checkCardsWithMaxDate() {
        coursePage.open()
                .openAllProgramCard()
                .maxDateCards()
                .stream()
                .peek(card -> coursePage.openCard(card))
                .forEach(card -> cardPage.checkCard(card));
    }

    @Test
    @DirtiesContext
    public void checkRandomCourseCategoryClick() {
        mainPage.open()
                .openStudy()
                .clickCourseCategory()
                .checkCourseCategory();
    }

}

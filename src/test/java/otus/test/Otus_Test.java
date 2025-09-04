package otus.test;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otus.Main;
import otus.extention.UiExtension;
import otus.pages.*;

import java.util.concurrent.atomic.AtomicReference;


@SpringBootTest(classes = {
        Main.class
})
@ExtendWith(UiExtension.class)
public class Otus_Test {

    @Autowired
    ClickHousePage clickHousePage;
    @Autowired
    CoursePage coursePage;
    @Autowired
    SubscriptionPage subscriptionPage;
    @Autowired
    UslugiKompaniyamPage uslugiKompaniyamPage;

    @Test
    public void clickHousePageTest(Page page) {
        clickHousePage
                .open(page)
                .teacherTilesDisplayed(page)
                .teacherTilesScroll(page)
                .teacherTileOpenToClick(page)
                .teacherTileClickToRight(page)
                .teacherTileClickToLeft(page);
    }

    @Test
    public void coursePageTest(Page page) {
        coursePage.open(page)
                .checkAllDerectionsAndAnyLevelOfDifficulty(page)
                .filterCourseLong(page)
                .showAllCards(page)
                .checkCourseLong(page)
                .clickOnCheckBoxAndCheckCards(page)
                .resetFilter(page);
    }

    @Test
    public void subscriptionPageTest(Page page) {
        subscriptionPage
                .open(page)
                .subscriptionOptionsBlockDisplay(page)
                .linkShow(page)
                .linkHide(page)
                .buy(page)
                .registrationDisplay(page);
    }

    @Test
    public void uslugiKompaniyamPageTest(Page page, BrowserContext browserContext) {
        AtomicReference<CustomCourses> customCourses = new AtomicReference<>(null);
        Page newTab = browserContext.waitForPage(() -> {
            customCourses.set(uslugiKompaniyamPage
                    .open(page)
                    .clickToBtnMore(page));
        });
        Assertions.assertNotNull(customCourses.get());
        customCourses.get()
                .pageIsOpen(newTab)
                .displayingLearningDirections(newTab)
                .clickOnCardProgramming(newTab);
    }
}

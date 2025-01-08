import driver.Browsers;
import driver.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.CalendarPage;
import pages.CardPage;
import pages.MainPage;
import pages.TestPage;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OtusTest {

    private WebDriver driver;
    private static String expectedUrl;
    private static String expectedEventType;
    private MainPage mainPage;
    private static final String URL = System.getProperty("base.url", "https://otus.ru/");
    ;
    private static final Browsers BROWSER =
            Browsers.valueOf(System.getProperty("browser", Browsers.FOX.name()).toUpperCase());

    private final Logger logger = LogManager.getLogger(OtusTest.class);

    @BeforeAll
    static void beforeAll() {
        expectedUrl = System.getProperty("test.page.url", "https://otus.ru/catalog/courses?categories=testing");
        expectedEventType = System.getProperty("expectedEventType", "Открытый вебинар");
    }

    @BeforeEach
    void setUp() {
        this.driver = new WebDriverFactory().createDriver(BROWSER);
        driver.get(URL);
        mainPage = new MainPage(driver);
        mainPage.selectCookiePanel().clickConsentBtn();
        mainPage.selectSalePanel().clickCloseSale();
    }

    @Test

    public void checkCardsOnTestingPage() throws InterruptedException {
        mainPage.selectNavigationBar().openTesting();
        Thread.sleep(5000);
        assertEquals(expectedUrl, driver.getCurrentUrl());
        logger.info("Переход на страницу с курсами 'тестирования' успешен.");

        TestPage testPage = new TestPage(driver);
        assertEquals(10, testPage.elementsList().size());
        logger.info("Количество карточек курсов равно 10.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/courseDetails.csv", delimiter = ';')
    @DisplayName(
            """
            Тест проверяет 8 карточек из 10, так как 2 из них выбиваются из общей массы по разметке. \
            Необходимо писать тесты отдельно или искать более универсальные селекторы, что на данный момент не вышло.
            """
    )
    public void checkCardCompliance(String href, String title, String description, String duration, String format) {
        mainPage.selectNavigationBar().openTesting();
        TestPage testPage = new TestPage(driver);
        CardPage cardPage = testPage.clickElement(href);

        assertEquals(System.getProperty("base.url") + href + "/", driver.getCurrentUrl());
        logger.info("Ссылки на карточку совпадают.");
        assertEquals(title, cardPage.getTitleText());
        logger.info("Название совпадает.");
        assertEquals(description, cardPage.getDescriptionText());
        logger.info("Описание совпадает.");
        assertEquals(duration, cardPage.getDurationText());
        logger.info("Длительность обучение совпадает.");
        assertEquals(format, cardPage.getFormatText());
        logger.info("Формат обучения совпадает.");
    }

    @Test
    public void checkCalendarDates() {
        mainPage.selectNavigationBar().openCalendar();
        CalendarPage calendarPage = new CalendarPage(driver);
        List<LocalDate> dates = calendarPage.getDate();
        for (LocalDate date : dates) {
            assertTrue(date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now()));
            logger.info("Текущая дата: " + LocalDate.now() + ", меньше или равна указанной в карточке: " + date);
        }
    }

    @Test
    public void checkCalendarTypeEvent() {
        mainPage.selectNavigationBar().openCalendar();
        CalendarPage calendarPage = new CalendarPage(driver);
        calendarPage.selectOpenWebinarType();
        List<String> types = calendarPage.listCardTypes();
        for (String type : types) {
            assertEquals(expectedEventType, type);
            logger.info("Исследуемый тип мероприятия: " + expectedEventType +
                        ", соответствует полученному типу мероприятия: " + type);
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
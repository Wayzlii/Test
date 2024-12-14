package homework_web_diver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChromeTest {

    private WebDriver driver;
    private final String URL = "https://otus.home.kartushin.su/training.html";
    private final Logger logger = LogManager.getLogger(ChromeTest.class);

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver();
    }


    @Test
    @DisplayName("Проверка соответствия введеного текста")
    void matchingTheEnteredText() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.get(URL);

        WebElement element = driver.findElement(By.id("textInput"));
        element.sendKeys("ОТУС");

        assertEquals("ОТУС", element.getDomProperty("value"));
    }

    @Test
    @DisplayName("Проверка открытия модального окна")
    void openingAModalWindow() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);

        driver.get(URL);

        WebElement element = driver.findElement(By.id("openModalBtn"));
        element.click();

        WebElement element1 = driver.findElement(By.id("myModal"));
        assertEquals("Это модальное окно", element1.findElement(By.tagName("h2")).getText());
        logger.info("Проверка заголовка модального окна.");
        assertEquals("Вы открыли модальное окно. " +
                        "Нажмите на крестик или в любое место вне окна, чтобы закрыть его.",
                element1.findElement(By.tagName("p")).getText());
        logger.info("Проверка тела модального окна.");
        assertEquals("×", element1.findElement(By.id("closeModal")).getText());
        logger.info("Проверка наличия '×' в модальном окне.");
    }

    @Test
    @DisplayName("Проверка формата динамического сообщения")
    void dynamicMessageFormat() {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(URL);

        WebElement element1 = driver.findElement(By.id("name"));
        element1.sendKeys("фыв");

        WebElement element2 = driver.findElement(By.id("email"));
        element2.sendKeys("asdf@sdfg.rt");

        WebElement element3 = driver.findElement(By.id("sampleForm")).findElement(By.tagName("button"));
        element3.click();

        assertEquals("Форма отправлена с именем: фыв и email: asdf@sdfg.rt",
                driver.findElement(By.id("messageBox")).getText());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

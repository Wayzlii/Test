package autotest;

import driver.Browsers;
import driver.GetDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterFormTest {

    private WebDriver driver;
    private static String name;
    private static String password;
    private static String mail;
    private static String birthday;
    private static String birthdayAssert;
    private static final Browsers BROWSER = Browsers.FOX;
    private static String url;

    private final Logger logger = LogManager.getLogger(RegisterFormTest.class);

    @BeforeAll
    static void beforeAll() {
        url = System.getProperty("base.url");
        name = System.getProperty("login");
        password = System.getProperty("password");
        mail = System.getProperty("mail");
        birthday = System.getProperty("birthday");
        birthdayAssert = System.getProperty("birthdayAssert");
        Objects.requireNonNull(url);
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);
        Objects.requireNonNull(mail);
        Objects.requireNonNull(birthday);
        Objects.requireNonNull(birthdayAssert);
    }

    @BeforeEach
    void setUp() {
        this.driver = new GetDriver().getDriver(BROWSER);
    }

    @ParameterizedTest
    @CsvSource(textBlock =
            """
                    1,beginner,Начальный
                    2,intermediate,Средний
                    3,advanced,Продвинутый
                    4,native,Носитель языка
                    """)
    public void registrationForm(int elementIndex, String elementValue, String elementName) throws AWTException {
        driver.get(url);

        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys(name);
        assertEquals(name, userName.getDomProperty("value"));
        logger.info("Введеное имя соответствует содержимому поля.");

        WebElement userMail = driver.findElement(By.id("email"));
        userMail.sendKeys(mail);
        assertEquals(mail, userMail.getDomProperty("value"));
        logger.info("Введеный email соответствует содержимому поля.");

        WebElement userPassword = driver.findElement(By.id("password"));
        userPassword.sendKeys(password);

        WebElement passwordConf = driver.findElement(By.id("confirm_password"));
        passwordConf.sendKeys(password);

        assertEquals(password, userPassword.getDomProperty("value"));
        logger.info("Введеный пароль соответствует эталону.");
        assertEquals(
                userPassword.getDomProperty("value"),
                passwordConf.getDomProperty("value")
        );
        logger.info("Содержимое поля 'Пароль' и 'Подтвердите пароль' соответствуют друг другу.");

        WebElement berthDay = driver.findElement(By.id("birthdate"));
        berthDay.click();
        Robot r = new Robot();
        r.setAutoDelay(100);
        for (char c : birthday.toCharArray()) {
            r.keyPress(c);
        }
        assertEquals(birthdayAssert, berthDay.getDomProperty("value"));
        logger.info("Введеная дата рождения соответствует эталону: " + birthdayAssert);

        Select langLevel = new Select(driver.findElement(By.id("language_level")));
        langLevel.selectByIndex(elementIndex);
        WebElement element = langLevel.getFirstSelectedOption();
        assertEquals(elementValue, element.getDomProperty("value"));
        assertEquals(elementName, element.getText());
        logger.info("Выбранный уровень языка соответствует эталону: " + elementName);

        WebElement registrationBtn = driver.findElement(By.cssSelector("input[type=submit]"));
        registrationBtn.click();

        assertEquals(
                ("Имя пользователя: %s\n" +
                 "Электронная почта: %s\n" +
                 "Дата рождения: %s\n" +
                 "Уровень языка: %s").formatted(name, mail, birthdayAssert, elementValue),
                driver.findElement(By.cssSelector("div#output")).getText()
        );
        logger.info("Форма соответствует выведенному тексту.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

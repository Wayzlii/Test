package autotest;

import driver.Browsers;
import driver.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.RegistrationPage;

import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterFormTest {

    private static final Browsers BROWSER =
            Browsers.valueOf(System.getProperty("browser", Browsers.FOX.name()).toUpperCase());
    private WebDriver driver;
    RegistrationPage registrationPage;
    private final String name = System.getProperty("login","Linda");
    private final String password = System.getProperty("password","12345");
    private final String mail = System.getProperty("mail","123@mail.ru");
    private final String birthday = System.getProperty("birthday","11102000");
    private final String birthdayAssert = System.getProperty("birthdayAssert","2000-10-11");
    private final Logger logger = LogManager.getLogger(RegisterFormTest.class);
    
    @BeforeEach
    void setUp() {
        this.driver = new WebDriverFactory().createDriver(BROWSER);
        this.registrationPage = new RegistrationPage(driver);
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
        registrationPage.open();

        WebElement userName = registrationPage.sendUserName(name);
        assertEquals(name, userName.getDomProperty("value"));
        logger.info("Введеное имя соответствует содержимому поля.");

        WebElement userMail = registrationPage.sendUserMail(mail);
        assertEquals(mail, userMail.getDomProperty("value"));
        logger.info("Введеный email соответствует содержимому поля.");

        WebElement userPassword = registrationPage.sendUserPassword(password);
        WebElement passwordConf = registrationPage.sendPasswordConf(password);
        assertEquals(password, userPassword.getDomProperty("value"));
        logger.info("Введеный пароль соответствует эталону.");
        assertEquals(
                userPassword.getDomProperty("value"),
                passwordConf.getDomProperty("value")
        );
        logger.info("Содержимое поля 'Пароль' и 'Подтвердите пароль' соответствуют друг другу.");

        WebElement berthDay = registrationPage.clickBerthDay();
        Robot r = new Robot();
        r.setAutoDelay(100);
        for (char c : birthday.toCharArray()) {
            r.keyPress(c);
        }
        assertEquals(birthdayAssert, berthDay.getDomProperty("value"));
        logger.info("Введеная дата рождения соответствует эталону: " + birthdayAssert);

        Select langLevel = registrationPage.selectLangLevel(elementIndex);
        WebElement element = langLevel.getFirstSelectedOption();
        assertEquals(elementValue, element.getDomProperty("value"));
        assertEquals(elementName, element.getText());
        logger.info("Выбранный уровень языка соответствует эталону: " + elementName);

        registrationPage.clickRegistrationBtn();
        assertEquals(
                ("Имя пользователя: %s\n" +
                 "Электронная почта: %s\n" +
                 "Дата рождения: %s\n" +
                 "Уровень языка: %s").formatted(name, mail, birthdayAssert, elementValue),
                registrationPage.textOutputText()
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

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class RegistrationPage {

    private final WebDriver driver;
    private final By userName = By.id("username");
    private final By userMail = By.id("email");
    private final By userPassword = By.id("password");
    private final By  passwordConf = By.id("confirm_password");
    private final By berthDay = By.id("birthdate");
    private final By langLevel = By.id("language_level");
    private final By registrationBtn = By.cssSelector("input[type=submit]");
    private final By outputText = By.cssSelector("div#output");

    private static final String url = System.getProperty("base.url","https://otus.home.kartushin.su/form.html");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open () {
        Objects.requireNonNull(url);
        this.driver.get(url);
    }

    private WebElement getBy(By by) {
        return this.driver.findElement(by);
    }

    public WebElement sendUserName(String name) {
        WebElement login = getBy(userName);
        login.sendKeys(name);
        return login;
    }
    public WebElement sendUserMail(String mail) {
        WebElement email = getBy(userMail);
        email.sendKeys(mail);
        return email;
    }
    public WebElement sendUserPassword(String pass) {
        WebElement password = getBy(userPassword);
        password.sendKeys(pass);
        return password;
    }
    public WebElement sendPasswordConf(String pass) {
        WebElement passwordConfig = getBy(passwordConf);
        passwordConfig.sendKeys(pass);
        return passwordConfig;
    }
    public WebElement clickBerthDay() {
        WebElement berthDay = getBy(this.berthDay);
        berthDay.click();
        return berthDay;
    }
    public Select selectLangLevel(int index) {
        Select langLevel = new Select(getBy(this.langLevel));
        langLevel.selectByIndex(index);
        return langLevel;
    }
    public void clickRegistrationBtn() {
            WebElement btn = getBy(registrationBtn);
            btn.click();
    }
    public String textOutputText() {
        WebElement text = getBy(outputText);
        return text.getText();
    }
}

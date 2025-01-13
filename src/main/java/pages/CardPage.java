package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardPage extends AbsBasePage{

    @FindBy (xpath = "//h1")
    private WebElement title;
    @FindBy (xpath = "//div[contains(@class,\"sc-1x9oq14-0\") and contains(@class,\"sc-s2pydo-3\") and contains(@class,\"enpOeQ\") and contains(@class,\"dZDxRw\")]/p")
    private WebElement description;
    @FindBy (xpath = "(//div[contains(@class,\"sc-3cb1l3-4\") and contains(@class,\"kGoYMV\")])[last()-2]")
    private WebElement duration;
    @FindBy (xpath = "//p[contains(text(),\"Онлайн\")]")
    private WebElement format;

    public CardPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText () {
        return title.getText();
    }
    public String getDescriptionText () {
        return description.getText();
    }
    public String getDurationText () {
        return duration.getText();
    }
    public String getFormatText () {
        return format.getText();
    }

}

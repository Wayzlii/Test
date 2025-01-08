package pages;

import common.AbsCommon;
import companents.CookiePanel;
import companents.NavigationBar;
import companents.SalePanel;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage extends AbsCommon {

    protected NavigationBar navigationBar;
    protected CookiePanel cookiePanel;
    protected SalePanel salePanel;

    public AbsBasePage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
        this.cookiePanel = new CookiePanel(driver);
        this.salePanel = new SalePanel(driver);
    }

    public NavigationBar selectNavigationBar() {
        return navigationBar;
    }

    public CookiePanel selectCookiePanel() {
        return cookiePanel;
    }

    public SalePanel selectSalePanel() {
        return salePanel;
    }
}

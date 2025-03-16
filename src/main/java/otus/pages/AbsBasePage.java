package otus.pages;

import otus.common.AbsCommon;
import org.openqa.selenium.WebDriver;
import otus.common.waiters.Waiter;
import otus.components.NavigationBar;

public abstract class AbsBasePage<T> extends AbsCommon {

    protected final String baseUrl;

    protected final NavigationBar navigationBar;

    public AbsBasePage(WebDriver driver, Waiter waiter, String baseUrl, NavigationBar navigationBar) {
        super(driver, waiter);
        this.baseUrl = baseUrl;
        this.navigationBar = navigationBar;
    }
    public String getPageSource() {
        return driver.getPageSource();
    }

    protected abstract String getPath();

    public T open() {
        driver.get(getUrl());
        return (T) this;
    }
    public T openStudy () {
        navigationBar.openStudy();
        return (T) this;
    }
    public CoursePage clickCourseCategory() {
        navigationBar.clickCourseCategory();
        return getCoursePage();
    }

    protected abstract CoursePage getCoursePage();

    public String getUrl() {
        return baseUrl + getPath();
    }
    public String getBaseUrl() {
        return baseUrl;
    }
}

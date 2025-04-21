package otus.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Lookup;
import otus.common.AbsCommon;
import otus.common.waiters.Waiter;
import otus.component.NavigationBar;

public abstract class AbsBasePage<T> extends AbsCommon {

    private final String baseUrl;

    protected final NavigationBar navigationBar;

    public AbsBasePage(WebDriver driver, Waiter waiter, String baseUrl, NavigationBar navigationBar) {
        super(driver, waiter);
        this.baseUrl = baseUrl;
        this.navigationBar = navigationBar;
    }

    protected abstract String getPath();

    public T open() {
        driver.get(getUrl());
        return (T) this;
    }

    public T open(String href) {
        driver.get(baseUrl + href);
        return (T) this;
    }

    public T openStudy() {
        navigationBar.openStudy();
        return (T) this;
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public CoursePage clickCourseCategory() {
        navigationBar.clickRandomCourseCategory();
        return getCoursePage();
    }

    public String getUrl() {
        return baseUrl + getPath();
    }

    @Lookup
    protected CoursePage getCoursePage() {
        return null;
    }
}

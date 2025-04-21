package otus.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.common.waiters.Waiter;
import otus.component.NavigationBar;

@Component
public class MainPage extends AbsBasePage<MainPage> {

    private final CoursePage coursePage;

    public MainPage(WebDriver driver, Waiter waiter,
                    @Value("${base.url}") String baseUrl,
                    NavigationBar navigationBar, CoursePage coursePage) {
        super(driver, waiter, baseUrl, navigationBar);
        this.coursePage = coursePage;
    }

    @Override
    protected String getPath() {
        return "";
    }
}

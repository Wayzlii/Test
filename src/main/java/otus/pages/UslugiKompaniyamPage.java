package otus.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.annotation.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/uslugi-kompaniyam")
@Component
public class UslugiKompaniyamPage extends AbsBasePage<UslugiKompaniyamPage> {

    private final CustomCourses customCourses;

    protected UslugiKompaniyamPage(@Value("${base.url}") String baseUrl, CustomCourses customCourses) {
        super(baseUrl);
        this.customCourses = customCourses;
    }


    public CustomCourses clickToBtnMore(Page page) {
        Locator findCourse = page.getByText("Не нашли нужный курс?Оставьте заявку и мы создадим курс для вашего бизнесаПодроб");
        Locator btnMore = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Подробнее"));
        assertThat(findCourse).isVisible();
        assertThat(btnMore).isVisible();
        assertThat(btnMore).isEnabled();
        btnMore.click();
        return  customCourses;
    }

}

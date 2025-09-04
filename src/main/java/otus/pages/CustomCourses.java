package otus.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.annotation.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/custom_courses/")
@Component
public class CustomCourses extends AbsBasePage<CustomCourses> {

    private final CoursePage coursePage;

    protected CustomCourses(@Value("${base.url}") String baseUrl, CoursePage coursePage) {
        super(baseUrl);
        this.coursePage = coursePage;
    }

    public CustomCourses pageIsOpen(Page page) {
        Locator heading = page.getByRole(AriaRole.HEADING).getByText("Разработка индивидуальных программ обучения для бизнеса");
        heading.scrollIntoViewIfNeeded();
        assertThat(heading).isVisible();
        return this;
    }

    public CustomCourses displayingLearningDirections(Page page) {
        Locator learningDirections = page.locator("#rec512235354 > .t396 > .t396__artboard > .t396__filter");
        learningDirections.scrollIntoViewIfNeeded();
        assertThat(learningDirections).isVisible();
        return this;
    }

    public CoursePage clickOnCardProgramming(Page page) {
        Locator programming = page.locator("#sbs-512235354-1664975284244");
        programming.scrollIntoViewIfNeeded();
        assertThat(programming).isVisible();
        programming.getByRole(AriaRole.LINK).click();
        assertThat(page.getByText("КаталогПоискКурс Скидка 20250")).isVisible();
        assertThat(page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Программирование"))).isChecked();
        return coursePage;
    }

}

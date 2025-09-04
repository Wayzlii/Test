package otus.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.annotation.Path;


import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/lessons/clickhouse")
@Component
public class ClickHousePage extends AbsBasePage<ClickHousePage> {

    protected ClickHousePage(@Value("${base.url}") String baseUrl) {
        super(baseUrl);
    }

    public ClickHousePage teacherTilesDisplayed(Page page) {
        Locator teachers = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Преподаватели"));
        teachers.scrollIntoViewIfNeeded();
        Assertions.assertTrue(teachers.isVisible());
        assertThat(page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Руководитель курсаАлексей ЖелезнойTech Lead Data ArchitectFinTech$")))
                .locator("div").nth(2)).isVisible();
        assertThat(page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Константин ТрофимовDeveloperDB Platform$")))
                .locator("div").nth(1)).isVisible();
        assertThat(page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Алексей ЦыкуновСо-основатель и технический директорHilbert Team$")))
                .locator("div").nth(1)).isVisible();
        assertThat(page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Нурсултан КариевSenior Data EngineerАзия ритейл$")))
                .locator("div").nth(1)).isVisible();
        return this;
    }

    public ClickHousePage teacherTilesScroll(Page page) {
        Locator after = page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Алексей ЦыкуновСо-основатель и технический директорHilbert Team$")));
        Locator before = page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Руководитель курсаАлексей ЖелезнойTech Lead Data ArchitectFinTech$")));
        String beforeBefore = page.locator("div").filter(new Locator.FilterOptions().setHas(before)).last()
                .locator(".swiper-slide").first().getAttribute("data-swiper-slide-index");
        before.dragTo(after);
        String afterBefore = page.locator("div").filter(new Locator.FilterOptions().setHas(before)).last()
                .locator(".swiper-slide").first().getAttribute("data-swiper-slide-index");
        Assertions.assertNotEquals(beforeBefore, afterBefore);
        return this;
    }

    public ClickHousePage teacherTileOpenToClick(Page page) {
        Locator teacher = page.getByText("Нурсултан Кариев").nth(0);
        String teacherName = teacher.textContent();
        page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Нурсултан КариевSenior Data EngineerАзия ритейл$")))
                .click();
        Assertions.assertEquals(teacherName,
                page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Нурсултан Кариев")).textContent());
        return this;
    }

    public ClickHousePage teacherTileClickToRight(Page page) {
        Locator teacherBefore = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Нурсултан Кариев"));
        page.locator("[id=\"__PORTAL__\"]").getByRole(AriaRole.BUTTON).nth(2).click();
        Locator teacherAfter = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Алексей Цыкунов"));
        Assertions.assertNotEquals(teacherBefore.textContent(), teacherAfter.textContent());
        return this;
    }

    public ClickHousePage teacherTileClickToLeft(Page page) {
        Locator teacherBefore = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Алексей Цыкунов"));
        page.locator("[id=\"__PORTAL__\"]").getByRole(AriaRole.BUTTON).nth(1).click();
        Locator teacherAfter = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Нурсултан Кариев"));
        Assertions.assertNotEquals(teacherBefore.textContent(), teacherAfter.textContent());
        return this;
    }

}

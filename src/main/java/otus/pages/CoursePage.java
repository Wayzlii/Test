package otus.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.annotation.Path;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/catalog/courses")
@Component
public class CoursePage extends AbsBasePage<CoursePage> {

    protected CoursePage(@Value("${base.url}") String baseUrl) {
        super(baseUrl);
    }

    public CoursePage checkAllDerectionsAndAnyLevelOfDifficulty(Page page) {
        assertThat(page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Все направления$")))).isVisible();
        assertThat(page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Все направления"))).isChecked();
        assertThat(page.locator("div").filter(new Locator.FilterOptions()
                .setHasText(Pattern.compile("^Любой уровень$")))).isVisible();
        assertThat(page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Любой уровень"))).isChecked();
        return this;
    }

    public CoursePage filterCourseLong(Page page) {
        assertThat(page.getByText("От 0 до 15 месяцев")).isVisible();
        Locator leftBefore = page.getByRole(AriaRole.SLIDER).first();
        leftBefore.scrollIntoViewIfNeeded();
        BoundingBox trackRight = leftBefore.boundingBox();
        int targetX = (int) (trackRight.x + trackRight.width * 2.5);
        int targetY = (int) (trackRight.y);
        moveTrack(leftBefore, page.mouse(), targetX, targetY);
        Locator rightBefore = page.getByRole(AriaRole.SLIDER).nth(1);
        BoundingBox trackLeft = rightBefore.boundingBox();
        int targetX2 = (int) (trackLeft.x - trackLeft.width * 2.5);
        int targetY2 = (int) (trackLeft.y);
        moveTrack(rightBefore, page.mouse(), targetX2, targetY2);
        assertThat(page.getByText("От 3 до 10 месяцев")).isVisible();
        return this;
    }

    public CoursePage showAllCards(Page page) {
        page.reload();
        Locator btnShowMore = getBtnShowMore(page);
        while (btnShowMore.isVisible() && btnShowMore.isEnabled()) {
            btnShowMore.scrollIntoViewIfNeeded();
            btnShowMore.click();
        }
        return this;
    }

    private static Locator getBtnShowMore(Page page) {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Показать еще"));
    }

    public CoursePage checkCourseLong(Page page) {
        Locator containingBlock = page.locator("div[class=\"sc-18q05a6-1 bwGwUO\"]");
        Locator cards = containingBlock.locator("a");
        for (int i = 0; i < cards.count(); i++) {
            Locator card = cards.nth(i);
            card.scrollIntoViewIfNeeded();
            Assertions.assertTrue(card.isVisible());
            Assertions.assertTrue(card.isEnabled());
            Locator element = card.locator("div").last();
            String textContent = element.textContent().trim();
            Assertions.assertTrue(
                    Stream.of("3 месяц", "4 месяц", "5 месяц", "6 месяц", "7 месяц", "8 месяц", "9 месяц", "10 месяц", "О дате старта будет объявлено позже")
                            .anyMatch(textContent::contains),
                    "Не удалось обработать: '" + textContent + "'"
            );
        }
        return this;
    }

    public CoursePage clickOnCheckBoxAndCheckCards(Page page) {
        showAllCards(page);
        int beforeCount = page.locator("div > div[class=\"sc-18q05a6-1 bwGwUO\"] > a").count();
        Locator checkbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Архитектура"));
        Assertions.assertTrue(checkbox.isVisible());
        checkbox.check();
        Assertions.assertTrue(checkbox.isChecked());
        showAllCards(page);
        int afterCount = page.locator("div > div[class=\"sc-18q05a6-1 bwGwUO\"] > a").count();
        Assertions.assertNotEquals(beforeCount, afterCount);
        return this;
    }

    public CoursePage resetFilter(Page page) {
        showAllCards(page);
        int beforeCount = page.locator("div > div[class=\"sc-18q05a6-1 bwGwUO\"] > a").count();
        Locator cleanFilter = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Очистить фильтры"));
        cleanFilter.scrollIntoViewIfNeeded();
        Assertions.assertTrue(cleanFilter.isVisible());
        Assertions.assertTrue(cleanFilter.isEnabled());
        cleanFilter.click();
        page.reload();
        assertThat(page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Все направления"))).isChecked();
        assertThat(page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Любой уровень"))).isChecked();
        assertThat(page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Все типы"))).isChecked();
        assertThat(page.getByRole(AriaRole.MAIN)).containsText("От 0 до 15 месяцев");
        showAllCards(page);
        int afterCount = page.locator("div > div[class=\"sc-18q05a6-1 bwGwUO\"] > a").count();
        Assertions.assertNotEquals(beforeCount, afterCount);
        return this;
    }

    private void moveTrack(Locator locator, Mouse mouse, int x, int y) {
        locator.hover();
        mouse.down();
        mouse.move(x, y);
        mouse.up();
    }

}

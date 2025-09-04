package otus.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.annotation.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Path("/subscription")
@Component
public class SubscriptionPage extends AbsBasePage<SubscriptionPage> {
    protected SubscriptionPage( @Value("${base.url}") String baseUrl) {
        super(baseUrl);
    }

    public SubscriptionPage subscriptionOptionsBlockDisplay(Page page) {
        assertThat(page.getByText("Варианты подписки 🔥"))
                .isVisible();
        assertThat(page.getByText("🔥 newBasic6 месяцев12"))
                .isVisible();
        assertThat(page.getByText("Standard6 месяцев12 месяцев168 000 ₽-15 %142 800 ₽КупитьКупить в рассрочку 3"))
                .isVisible();
        assertThat(page.locator("div")
                .filter(new Locator.FilterOptions()
                        .setHasText("Professional6 месяцев12 месяцев210 000 ₽-15 %178 500 ₽КупитьКупить в рассрочку"))
                .nth(3))
                .isVisible();
        return this;
    }

    public SubscriptionPage linkShow (Page page) {
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Подробнее")).first()).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Подробнее")).first().click();
        assertThat(page.getByText("Не предусмотрены: домашние задания, проектные работы, сертификаты и дипломы о пр").first()).isVisible();
        assertThat(page.getByText("Можно продлить подписку на 3").first()).isVisible();
        return this;
    }

    public SubscriptionPage linkHide (Page page) {
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Свернуть"))).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Свернуть")).click();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Подробнее")).first()).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Свернуть"))).isHidden();
        return this;
    }

    public SubscriptionPage buy(Page page) {
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Купить").setExact(true)).first()).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Купить").setExact(true)).first().click();
        return this;
    }

    public SubscriptionPage registrationDisplay (Page page) {
        assertThat(page.getByText("ВходРегистрацияПодписка на доступ к курсам Проходите параллельно 3")).isVisible();
        assertThat(page.getByText("Зарегистрируйтесь для оформления подписки на 6 месяцев").first()).isVisible();
        return this;
    }

}

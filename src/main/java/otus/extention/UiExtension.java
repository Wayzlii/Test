package otus.extention;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.extension.*;

import java.io.File;

public class UiExtension
        implements BeforeEachCallback,
        BeforeAllCallback,
        AfterEachCallback,
        AfterAllCallback,
        ParameterResolver {

    private Playwright playwright;
    private Browser browser;

    private BrowserContext browserContext;

    private Page page;

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        this.playwright.close();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        this.browserContext.tracing()
                .stop(new Tracing.StopOptions().setPath(new File("./trace.zip").toPath()));
        page.close();
        browserContext.close();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        this.playwright = Playwright.create(new Playwright.CreateOptions());
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        this.browserContext = browser.newContext();
        this.browserContext.tracing()
                .start(
                        new Tracing.StartOptions()
                                .setSnapshots(true)
                                .setSources(true)
                                .setScreenshots(true)
                );
        this.page = browserContext.newPage();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isInstance(page)
               || parameterContext.getParameter().getType().isInstance(browserContext);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        if (parameterContext.getParameter().getType().isInstance(page))
            return this.page;
        if (parameterContext.getParameter().getType().isInstance(browserContext))
            return this.browserContext;
        throw new IllegalArgumentException();
    }
}

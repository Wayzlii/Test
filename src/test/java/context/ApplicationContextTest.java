package context;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import otus.Main;


@SpringBootTest(classes = {
        Main.class
})
@Log4j2
public class ApplicationContextTest {
    @Test
    @DirtiesContext
    public void contextCreatedTest() {
        log.info("Контекст успешно построен");
    }
}

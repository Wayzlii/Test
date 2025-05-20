package ru.otus.steps.blocks;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import org.springframework.beans.factory.annotation.Autowired;
import otus.component.NavigationBar;

public class NavigationBarSteps {

    @Autowired
    private NavigationBar navigationBar;

    @Если("Открыть вкладку Обучение")
    public void openStudy() {
        navigationBar.openStudy();
    }

    @И("Кликнуть на случайную категорию курсов")
    public void clickCourseCategory() {
        navigationBar.clickRandomCourseCategory();
    }
}

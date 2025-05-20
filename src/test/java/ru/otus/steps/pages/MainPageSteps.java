package ru.otus.steps.pages;

import io.cucumber.java.ru.Дано;
import org.springframework.beans.factory.annotation.Autowired;
import otus.pages.MainPage;

public class MainPageSteps {

    @Autowired
    private MainPage mainPage;

    @Дано("Открыта главная страница")
    public MainPage mainPageOpen() {
        return mainPage.open();
    }
}

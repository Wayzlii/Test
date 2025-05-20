package ru.otus.steps.pages;

import io.cucumber.java.ru.Тогда;
import org.springframework.beans.factory.annotation.Autowired;
import otus.pages.CardPage;

public class CardPageSteps {

    @Autowired
    private CardPage cardPage;

    @Тогда("Откроется страница картички с названием (.*)$")
    public void checkCourseName(String expectedCourseName) {
        cardPage.checkCourseName(expectedCourseName);
    }

}

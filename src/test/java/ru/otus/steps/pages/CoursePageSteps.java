package ru.otus.steps.pages;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.То;
import org.springframework.beans.factory.annotation.Autowired;
import otus.pages.CardPage;
import otus.pages.CoursePage;


public class CoursePageSteps {

    @Autowired
    private CoursePage coursePage;
    @Autowired
    private CardPage cardPage;

    @Пусть("Открыта страница каталога курсов")
    public void openCoursePage() {
        coursePage.open();
    }

    @Если("Открыть все карточки курса")
    public void openAllProgramCard() {
        coursePage.openAllProgramCard();
    }

    @И("Кликнуть по карточке с названием (.*)$")
    public void clickCardProgram(String courseName) {
        coursePage.clickCardProgram(courseName);
    }

    @То("Будет открыт каталог курсов с курсами выбранной категории")
    public void checkCourseCategory() {
        coursePage.checkCourseCategory();
    }

    @И("Найти все карточки с минимальной датой старта курса")
    public void minDateCards() {
        coursePage.minDateCards();
    }

    @То("Откроем и проверим все найденые карточки по минимальной дате и имени")
    public void findAllCardsWithMinDate() {
        coursePage.minDateCards()
                .stream()
                .peek(card -> coursePage.openCard(card))
                .forEach(card -> cardPage.checkCard(card));
    }

    @И("Найти все карточки с максимальной датой старта курса")
    public void maxDateCards() {
        coursePage.maxDateCards();
    }

    @То("Откроем и проверим все найденые карточки по максимальной дате и имени")
    public void findAllCardsWithMaxDate() {
        coursePage.maxDateCards()
                .stream()
                .peek(card -> coursePage.openCard(card))
                .forEach(card -> cardPage.checkCard(card));
    }
}

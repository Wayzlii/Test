package OTUS.ПодготовительныйКурсJava.Модуль1.Lesson5.HomeworkAnimals;

public class Duck extends Animal implements Flying{

    public Duck(String name, int age, int weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    protected void toSay() {
        System.out.println("Кря!");
    }

    @Override
    public void toFly() {
        System.out.println("Я лечу!");
    }
}

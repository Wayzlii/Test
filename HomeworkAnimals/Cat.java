package OTUS.ПодготовительныйКурсJava.Модуль1.Lesson5.HomeworkAnimals;

public class Cat extends Animal {

    public Cat(String name, int age, int weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    protected void toSay() {
        System.out.println("Мяу!");
    }
}

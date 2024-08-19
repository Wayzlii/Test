package OTUS.ПодготовительныйКурсJava.Модуль1.Lesson5.HomeworkAnimals;

public class Animal {

    private String name;
    private int age;
    private int weight;
    private String color;

    private String ageToString() {
        String result = "";
        if (getAge() == 1) {
            result = "год";
        } else if (getAge() == 2 || getAge() == 3 || getAge() == 4) {
            result = "года";
        } else {
            result = "лет";
        }
      return result;
    }

    @Override
    public String toString() {
        return "Привет! " +
                "Меня зовут " + name +
                ", мне " + age + " " + ageToString() +
                ", я вешу - " + weight +
                " кг, мой цвет - " + color;
    }

    public Animal(String name, int age, int weight, String color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
        if (age < 0){
            throw new IllegalArgumentException("Возраст не может быть отрицательным числом.");
        }
        if (weight < 0){
            throw new IllegalArgumentException("Вес не может быть отрицательным числом.");
        }
    }
    protected void toSay() {
        System.out.println("Я говорю");
    }
    protected void toGo() {
        System.out.println("Я иду");
    }
    protected void toDrink () {
        System.out.println("Я пью");
    }
    protected void toEat() {
        System.out.println("Я ем");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

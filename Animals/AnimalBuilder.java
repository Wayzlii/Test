package Homework.Animals;

public class AnimalBuilder {
    public Animal animalBuilder(String animalType,
                              String name,
                              int age, int weight,
                              String color) {
        if (animalType.equals("cat")) {
            return (new Cat(name, age, weight, color));
        } else if (animalType.equals("dog")) {
            return (new Dog(name, age, weight, color));
        } else {
            return (new Duck(name, age, weight, color));
        }
    }
}

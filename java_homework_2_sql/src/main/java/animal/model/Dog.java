package animal.model;

public class Dog extends Animal{
    public Dog(String name, String color, int age, int weight) {
        super(AnimalType.DOG, name, color, age, weight);
    }

    public Dog(int id, String name, String color, int age, int weight) {
        super(id, AnimalType.DOG, name, color, age, weight);
    }

    @Override
    public void say() {
        System.out.println("Гав!");
    }
}

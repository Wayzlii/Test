package animal;

public class Cat extends Animal{
    public Cat(String name, String color, int age, int weight) {
        super(AnimalType.CAT, name, color, age, weight);
    }

    public Cat(int id, String name, String color, int age, int weight) {
        super(id, AnimalType.CAT, name, color, age, weight);
    }

    @Override
    public void say() {
        System.out.println("Мяу!");
    }
}

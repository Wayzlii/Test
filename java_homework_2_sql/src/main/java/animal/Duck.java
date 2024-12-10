package animal;

public class Duck extends Animal implements Flying{
    public Duck(String name, String color, int age, int weight) {
        super(AnimalType.DUCK, name, color, age, weight);
    }

    public Duck(int id, String name, String color, int age, int weight) {
        super(id, AnimalType.DUCK, name, color, age, weight);
    }

    @Override
    public void say() {
        System.out.println("Кря!");
    }

    @Override
    public void flay() {
        System.out.println("Я лечу!");
    }
}

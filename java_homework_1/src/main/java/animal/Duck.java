package animal;

public class Duck extends Animal implements Flaying{
    public Duck(String name, int age, int weight, String color) {
        super(name, age, weight, color);
    }
    @Override
    public void say() {
        System.out.println("Кря!");
    }

    public void flay() {
        System.out.println("Я лечу!");
    }
}

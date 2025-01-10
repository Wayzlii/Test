package animal.model;

public abstract class Animal {
    private String name;
    private int age;
    private int weight;
    private String color;

    public Animal(String name, int age, int weight, String color) {
        this.name = name.trim();
        if (age >= 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Возраст не может быть отрицательным числом.");
        }
        if (weight >= 0) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Вес не может быть отрицательным.");
        }
        this.color = color.trim();
    }
    public void say() {
        System.out.println("Я гворю.");
    }
    public void go() {
        System.out.println("Я иду");
    }
    public void drink() {
        System.out.println("Я пью.");
    }
    public void eat() {
        System.out.println("Я ем.");
    }
    private String getAgeSuffix() {
        String res = "";
        int ageMod = age % 10;
        if (age % 100 > 9 && age % 100 < 21) {
            res = "лет";
        } else if (ageMod == 1) {
            res = "год";
        } else if (ageMod > 1 && ageMod < 5) {
            res = "года";
        } else {
            res = "лет";
        }
        return res;
    }

    @Override
    public String toString() {
        return "Привет!" +
                " Меня зовут " + name +
                ", мне " + age + " " + getAgeSuffix() +
                ", мой вес " + weight +
                "кг, мой окрас - " + color;
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

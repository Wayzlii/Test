package animal;

public abstract class Animal {
    private int id;
    private AnimalType type;
    private String name;
    private String color;
    private int age;
    private int weight;

    public Animal(int id, AnimalType type, String name, String color, int age, int weight) {
        this(type, name, color, age, weight);
        this.id = id;
    }

    public Animal(AnimalType type, String name, String color, int age, int weight) {
        this.type = type;
        this.name = name;
        this.color = color;
        if (age >= 0) {
            this.age = age;
        } else System.out.println("Возраст животного не может быть отрицательным числом.");
        if (weight >= 0) {
            this.weight = weight;
        } else System.out.println("Вес животного не может быть отрицательным.");
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
        if (age % 10 == 1) {
            res = "год";
        } else if (age % 10 > 1 && age % 10 < 5) {
            res = "года";
        } else {
            res = "лет";
        }
        return res;
    }

    @Override
    public String toString() {
        return "Привет!" +
                "Мой id = " + id +
                ". Я " + type +
                ". Меня зовут " + name +
                ", мне " + age + " " + getAgeSuffix() +
                ", мой вес " + weight +
                "кг, мой окрас - " + color;
    }

    public AnimalType getAnimalType() {
        return type;
    }

    public void setAnimalType(String animalType) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

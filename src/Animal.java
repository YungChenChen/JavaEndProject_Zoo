public class Animal {
    String name;
    int age;
    String healthStatus;
    Enclosure enclosure;  // 新增柵欄屬性

    public Animal(String name, int age, String healthStatus) {
        this.name = name;
        this.age = age;
        this.healthStatus = healthStatus;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public void displayInfo() {
        System.out.println("動物名字: " + name + ", 年齡: " + age + ", 健康狀態: " + healthStatus +
                (enclosure != null ? ", 柵欄: " + enclosure.getCapacity() : ""));
    }


    public void feed(String food) {
        System.out.println(name + " 正在吃 " + food + "。");
    }

    public void move(String targetArea) {
        System.out.println(name + " 正在移動到 " + targetArea + "。");
    }
}

class Mammal extends Animal {
    public Mammal(String name, int age, String healthStatus) {
        super(name, age, healthStatus);
    }
}
class Bird extends Animal {
    public Bird(String name, int age, String healthStatus) {
        super(name, age, healthStatus);
    }
}
class Reptile extends Animal {
    public Reptile(String name, int age, String healthStatus) {
        super(name, age, healthStatus);
    }
}

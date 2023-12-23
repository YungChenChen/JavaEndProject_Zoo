class Animal {
    String name;
    int age;
    String healthStatus;

    public Animal(String name, int age, String healthStatus) {
        this.name = name;
        this.age = age;
        this.healthStatus = healthStatus;
    }

    public void displayInfo() {
        System.out.println("Animal: " + name + ", Age: " + age + ", Health: " +
                healthStatus);
    }

    public void feed(String food) {
        System.out.println(name + " 正在吃 " + food + "。");
    }

    public void move(String targetArea) {
        System.out.println(name + " 正在移動到 " + targetArea + "。");
    }
}

class Mammal extends Animal {
    public Mammal(String name, int age, String healthStatus) { super(name, age, healthStatus);
    }
}
class Bird extends Animal {
    public Bird(String name, int age, String healthStatus) { super(name, age, healthStatus);
    }
}
class Reptile extends Animal {
    public Reptile(String name, int age, String healthStatus) { super(name, age, healthStatus);
    }
}

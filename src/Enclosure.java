import java.util.ArrayList;

public class Enclosure {
    String name;
    int capacity;
    ArrayList<Animal> animals = new ArrayList<>();


    public Enclosure(String name, int capacity) { // 修改构造函数以接收柵欄名稱
        this.name = name;
        this.capacity = capacity;
    }


    public void addAnimal(Animal animal) {
        if (animals.size() < capacity) {
            animals.add(animal);
            animal.setEnclosure(this);  // 設定動物所在的柵欄
            System.out.println(animal.name + " 已成功新增到柵欄。");
        } else {
            System.out.println("柵欄已滿，無法新增動物。");
        }
    }
    public void removeAnimal(Animal animal) {
        if (animals.contains(animal)) {
            animals.remove(animal);
        }
    }

    public void listAnimals() {
        for (Animal animal : animals) {
            animal.displayInfo();
        }
    }

    // 可以添加其他方法
    public int getCapacity() {
        return capacity;
    }
    public String getName() { // 添加方法來獲取柵欄名稱
        return name;
    }
}
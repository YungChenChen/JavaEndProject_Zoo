import java.util.ArrayList;
public class Enclosure {
    int capacity;
    ArrayList<Animal> animals = new ArrayList<>();
    public Enclosure(int capacity) {
        this.capacity = capacity;
    }
    public void addAnimal(Animal animal) {
        if (animals.size() < capacity) {
            animals.add(animal);
        }
        else {
        System.out.println("Enclosure is full!");
        }
    }
    public void listAnimals() {
        for (Animal animal : animals) {
            animal.displayInfo();
        }
    }
}


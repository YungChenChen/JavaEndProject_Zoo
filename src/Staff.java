class Staff {
    String name;
    int id;
    float salary;

    public Staff(String name, int id, float salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

}
class Zookeeper extends Staff {
    public Zookeeper(String name, int id, float salary) {
        super(name, id, salary);
    }
}
class Veterinaria extends Staff {
    public Veterinaria(String name, int id, float salary) {
        super(name, id, salary);
    }
}
class  Manager extends Staff {
    public Manager(String name, int id, float salary) {
        super(name, id, salary);
    }
}
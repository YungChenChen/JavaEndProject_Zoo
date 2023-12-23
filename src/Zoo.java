import java.util.ArrayList;
public class Zoo {
    ArrayList<Enclosure> enclosures = new ArrayList<>();
    ArrayList<Staff> staff = new ArrayList<>();
    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
    }
    public void addStaff(Staff newStaff) {
        staff.add(newStaff);
    }
    public void generateReport() {
        for (Enclosure enclosure : enclosures) {
            enclosure.listAnimals();
        }
    }
}
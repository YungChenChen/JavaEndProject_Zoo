import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Animal> zoo = readData();
        ArrayList<Staff> staffList = readStaffData();


        while (true) {
            System.out.println("請選擇要管理的目標：");
            System.out.println("1. 動物");
            System.out.println("2. 員工");
            System.out.println("3. 動物園柵欄");
            System.out.println("4. 退出");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                manageAnimals(input, zoo);
            } else if (choice.equals("2")) {
                manageStaff(input, staffList);
            } else if (choice.equals("3")) {
                manageEnclosures();
            } else if (choice.equals("4")) {
                writeData(zoo);
                break;
            } else {
                System.out.println("請輸入有效的選項。");
            }
        }
    }

    private static void manageAnimals(Scanner input, ArrayList<Animal> zoo) {
        while (true) {
            System.out.println("1. 添加動物");
            System.out.println("2. 移除動物");
            System.out.println("3. 餵食動物");
            System.out.println("4. 移動動物");
            System.out.println("5. 列印動物列表");
            System.out.println("6. 上一頁");
            String option = input.nextLine();

            if (option.equals("1")) {
                zoo.add(newAnimal(input));
            } else if (option.equals("2")) {
                removeAnimal(input, zoo);
            } else if (option.equals("3")) {
                feedAnimals(input, zoo);
            } else if (option.equals("4")) {
                moveAnimal(input, zoo);
            } else if (option.equals("5")) {
                printAnimalList(zoo);
            } else if (option.equals("6")) {
                break;
            } else {
                System.out.println("請輸入有效的選項。");
            }
            writeData(zoo);
        }
    }

    private static void manageStaff(Scanner input, ArrayList<Staff> staffList) {
        // 這裡不需要重新讀取員工資料
        // staffList = readStaffData();

        while (true) {
            System.out.println("1. 新增員工");
            System.out.println("2. 刪除員工");
            System.out.println("3. 列印員工列表");
            System.out.println("4. 上一頁");
            String option = input.nextLine();

            if (option.equals("1")) {
                addStaff(input, staffList);
            } else if (option.equals("2")) {
                removeStaff(input, staffList);
            } else if (option.equals("3")) {
                printStaffList(staffList);
            } else if (option.equals("4")) {
                writeStaffData(staffList);
                break;
            } else {
                System.out.println("請輸入有效的選項。");
            }
        }
    }

    private static void addStaff(Scanner input, ArrayList<Staff> staffList) {
        System.out.print("請輸入員工名字: ");
        String name = input.nextLine();
        System.out.print("請輸入員工ID: ");
        int id = Integer.parseInt(input.nextLine());
        System.out.print("請輸入員工薪水: ");
        float salary = Float.parseFloat(input.nextLine());

        Staff newStaff = new Staff(name, id, salary);
        staffList.add(newStaff);
        System.out.println(name + " 已成功新增為員工。");
    }

    private static void removeStaff(Scanner input, ArrayList<Staff> staffList) {
        System.out.print("請輸入要刪除的員工ID: ");
        int id = Integer.parseInt(input.nextLine());

        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).id == id) {
                String removedName = staffList.get(i).name;
                staffList.remove(i);
                System.out.println(removedName + " 已成功刪除。");
                return;
            }
        }
        System.out.println("未找到該員工。");
    }

    private static void printStaffList(ArrayList<Staff> staffList) {
        System.out.println("員工列表：");
        for (Staff staff : staffList) {
            System.out.println("員工名字: " + staff.name + ", 員工ID: " + staff.id + ", 員工薪水: " + staff.salary);
        }
        System.out.println();
    }

        private static void manageEnclosures() {
            // 圈舍管理的程式碼
            // 可以是你的圈舍管理功能
        }





    private static ArrayList<Animal> readData() {
        ArrayList<Animal> zoo = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(new File("data.txt"));
            while (fileIn.hasNext()) {
                // 逐行讀取動物資料
                String raw = fileIn.nextLine();
                // 使用逗號分隔動物資料
                String[] arr = raw.split(",");
                // 第一項是動物名字
                Animal animal = new Animal(arr[0], Integer.parseInt(arr[1]), arr[2]);
                // 添加到動物園
                zoo.add(animal);
            }
            fileIn.close();
        } catch (IOException e) {
            System.out.println("動物園資料文件不存在。");
        }
        return zoo;
    }

    private static void writeData(ArrayList<Animal> zoo) {
        try {
            PrintWriter fileOut = new PrintWriter("zoo_data.txt");
            for (Animal animal : zoo) {
                // 添加動物資訊到文件中
                fileOut.println(animal.name + "," + animal.age + "," + animal.healthStatus);
            }
            fileOut.close();
        } catch (IOException e) {
            System.out.println("無法寫入動物園資料。");
        }
    }

    private static ArrayList<Staff> readStaffData() {
        ArrayList<Staff> staffList = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(new File("staff_data.txt"));
            while (fileIn.hasNext()) {
                // 逐行讀取員工資料
                String raw = fileIn.nextLine();
                // 使用逗號分隔員工資料
                String[] arr = raw.split(",");
                // 第一項是員工名字
                Staff staff = new Staff(arr[0], Integer.parseInt(arr[1]), Float.parseFloat(arr[2]));
                // 添加到員工列表
                staffList.add(staff);
            }
            fileIn.close();
        } catch (IOException e) {
            System.out.println("員工資料文件不存在。");
        }
        return staffList;
    }

    private static void writeStaffData(ArrayList<Staff> staffList) {
        try {
            PrintWriter fileOut = new PrintWriter("staff_data.txt");
            for (Staff staff : staffList) {
                // 添加員工資訊到文件中
                fileOut.println(staff.name + "," + staff.id + "," + staff.salary);
            }
            fileOut.close();
        } catch (IOException e) {
            System.out.println("無法寫入員工資料。");
        }
    }

    private static Animal newAnimal(Scanner input) {
        System.out.print("請輸入動物名字: ");
        String name = input.nextLine();
        System.out.print("請輸入動物年齡: ");
        int age = Integer.parseInt(input.nextLine());
        System.out.print("請輸入動物健康狀態: ");
        String healthStatus = input.nextLine();
        return new Animal(name, age, healthStatus);
    }

    private static void removeAnimal(Scanner input, ArrayList<Animal> zoo) {
        System.out.print("請輸入要移除的動物名字: ");
        String animalName = input.nextLine();
        for (int i = 0; i < zoo.size(); i++) {
            if (zoo.get(i).name.equals(animalName)) {
                zoo.remove(i);
                System.out.println(animalName + " 已成功移除。");
                return;
            }
        }
        System.out.println("未找到名為 " + animalName + " 的動物。");
    }

    private static void feedAnimals(Scanner input, ArrayList<Animal> zoo) {
        for (Animal animal : zoo) {
            System.out.print("請輸入給 " + animal.name + " 餵食的食物: ");
            String food = input.nextLine();
            // 可以將餵食的邏輯加入到新的Animal類別中
            animal.feed(food);
        }
    }

    private static void moveAnimal(Scanner input, ArrayList<Animal> zoo) {
        System.out.print("請輸入要移動的動物名字: ");
        String animalName = input.nextLine();
        for (Animal animal : zoo) {
            if (animal.name.equals(animalName)) {
                System.out.print("請輸入目標區域: ");
                String targetArea = input.nextLine();
                // 可以將移動的邏輯加入到新的Animal類別中
                animal.move(targetArea);
                return;
            }
        }
        System.out.println("未找到名為 " + animalName + " 的動物。");
    }

    private static void printAnimalList(ArrayList<Animal> zoo) {
        System.out.println("動物列表：");
        for (Animal animal : zoo) {
            animal.displayInfo();
        }
        System.out.println();
    }
}
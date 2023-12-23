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
        ArrayList<Enclosure> enclosures = readEnclosureData();



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
                manageEnclosures(input, enclosures,zoo); // 注意这里将zoo传递给manageEnclosures方法
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
            System.out.println("1. 新增動物");
            System.out.println("2. 移除動物");
            System.out.println("3. 餵食動物");
            System.out.println("4. 列印動物列表");
            System.out.println("5. 上一頁");
            String option = input.nextLine();

            if (option.equals("1")) {
                zoo.add(newAnimal(input));
            } else if (option.equals("2")) {
                removeAnimal(input, zoo);
            } else if (option.equals("3")) {
                feedAnimals(input, zoo);
            } else if (option.equals("4")) {
                printAnimalList(zoo);
            } else if (option.equals("5")) {
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

    private static void manageEnclosures(Scanner input, ArrayList<Enclosure> enclosures, ArrayList<Animal> zoo) {
        while (true) {
            System.out.println("1. 新增柵欄");
            System.out.println("2. 將動物新增到柵欄");
            System.out.println("3. 將動物從柵欄移除");
            System.out.println("4. 列印柵欄列表");
            System.out.println("5. 上一頁");
            String option = input.nextLine();

            if (option.equals("1")) {
                addEnclosure(input, enclosures);
            } else if (option.equals("2")) {
                addAnimalToEnclosure(input, zoo, enclosures);
            } else if (option.equals("3")) {
                removeAnimalFromEnclosure(input, enclosures);
            } else if (option.equals("4")) {
                printEnclosureList(enclosures);
            } else if (option.equals("5")) {
                // 可以在這裡寫入柵欄資料到文件中，如果需要的話
                break;
            } else {
                System.out.println("請輸入有效的選項。");
            }
        }
        writeData(zoo);
        writeEnclosureData(enclosures); // 保存柵欄数据
    }
    private static void removeAnimalFromEnclosure(Scanner input, ArrayList<Enclosure> enclosures) {
        System.out.print("請輸入動物名字: ");
        String animalName = input.nextLine();

        for (Enclosure enclosure : enclosures) {
            for (Animal animal : enclosure.animals) {
                if (animal.name.equals(animalName)) {
                    enclosure.removeAnimal(animal);
                    System.out.println("已從柵欄移除動物 " + animalName);
                    return;
                }
            }
        }
        System.out.println("未找到名為 " + animalName + " 的動物或動物不在任何柵欄中。");
    }
    private static void addAnimalToEnclosure(Scanner input, ArrayList<Animal> zoo, ArrayList<Enclosure> enclosures) {
        System.out.print("請輸入動物名字: ");
        String animalName = input.nextLine();

        Animal animalToAdd = null;

        for (Animal animal : zoo) {
            if (animal.name.equals(animalName)) {
                animalToAdd = animal;
                break;
            }
        }

        if (animalToAdd != null) {
            System.out.print("請輸入要新增到的柵欄名稱: ");
            String enclosureName = input.nextLine();

            Enclosure targetEnclosure = null;

            for (Enclosure enclosure : enclosures) {
                if (enclosure.getName().equals(enclosureName)) {
                    targetEnclosure = enclosure;
                    break;
                }
            }

            if (targetEnclosure != null) {
                if (targetEnclosure.getCapacity() > targetEnclosure.animals.size()) {
                    targetEnclosure.addAnimal(animalToAdd);
                    System.out.println("已將動物 " + animalName + " 新增到柵欄 " + enclosureName + " 中。");
                } else {
                    System.out.println("柵欄已滿，無法新增動物到 " + enclosureName + "，請選擇其他柵欄。");
                }
            } else {
                System.out.println("未找到名為 " + enclosureName + " 的柵欄。");
            }
        } else {
            System.out.println("未找到名為 " + animalName + " 的動物。");
        }
    }
    private static void addEnclosure(Scanner input, ArrayList<Enclosure> enclosures) {
        System.out.print("請輸入柵欄名稱: ");
        String enclosureName = input.nextLine(); // 要求輸入柵欄名稱
        System.out.print("請輸入柵欄容量: ");
        int capacity = Integer.parseInt(input.nextLine());

        Enclosure enclosure = new Enclosure(enclosureName, capacity); // 創建帶有名稱的柵欄
        enclosures.add(enclosure);
        System.out.println("已成功新增柵欄 " + enclosureName + "，容量為 " + capacity + "。");
    }

    private static void printEnclosureList(ArrayList<Enclosure> enclosures) {
        System.out.println("柵欄列表：");
        for (Enclosure enclosure : enclosures) {
            System.out.println(enclosure.getName() + " - 容量: " + enclosure.getCapacity() +
                    ", 目前動物數量: " + enclosure.animals.size());
        }
        System.out.println();
    }




    private static ArrayList<Animal> readData() {
        ArrayList<Animal> zoo = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(new File("zoo_data.txt"));
            while (fileIn.hasNextLine()) {
                String raw = fileIn.nextLine();
                if (raw.trim().isEmpty()) {
                    continue; // 跳过空行
                }
                String[] arr = raw.split(",");
                Animal animal = new Animal(arr[0], Integer.parseInt(arr[1]), arr[2], arr[3]);
                zoo.add(animal);
            }
            fileIn.close();
        } catch (IOException e) {
            System.out.println("动物园数据文件不存在。");
        }

     /*   try {
            Scanner fileIn = new Scanner(new File("zoo_data.txt"));
            while (fileIn.hasNext()) {
                // 逐行讀取動物資料
                String raw = fileIn.nextLine();
                // 使用逗號分隔動物資料
                String[] arr = raw.split(",");
                // 第一項是動物名字
                Animal animal = new Animal(arr[0], Integer.parseInt(arr[1]), arr[2],arr[3]);
                // 添加到動物園
                zoo.add(animal);
            }
            fileIn.close();
        } catch (IOException e) {
            System.out.println("動物園資料文件不存在。");
        }*/
        return zoo;
    }

    private static void writeData(ArrayList<Animal> zoo) {
        try {
            PrintWriter fileOut = new PrintWriter("zoo_data.txt");

            for (Animal animal : zoo) {
                // 添加動物資訊到文件中

                fileOut.println(animal.name + "," + animal.age + "," + animal.healthStatus + "," + animal.categoryChoice);
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
    private static ArrayList<Enclosure> readEnclosureData() {
        ArrayList<Enclosure> enclosures = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(new File("enclosure_data.txt"));
            while (fileIn.hasNext()) {
                String raw = fileIn.nextLine();
                String[] arr = raw.split(",");
                String enclosureName = arr[0]; // 第一项是容量
                int capacity = Integer.parseInt(arr[1]); // 第一项是容量
                //String enclosureName = ""; // 定义柵欄名稱变量
                // 检查是否有足够的数据来获取柵欄名稱
                if (arr.length > 1) {
                    enclosureName = arr[0];
                }
                // 创建柵欄并添加到列表中
                Enclosure enclosure = new Enclosure(enclosureName, capacity);
                enclosures.add(enclosure);
            }
            fileIn.close();
        } catch (IOException e) {
            System.out.println("柵欄資料文件不存在或發生錯誤。");
        }
        return enclosures;
    }

    private static void writeEnclosureData(ArrayList<Enclosure> enclosures) {
        try {
            PrintWriter fileOut = new PrintWriter("enclosure_data.txt");
            for (Enclosure enclosure : enclosures) {
                // 将柵欄容量写入文件
                fileOut.println(enclosure.getName() + "," + enclosure.getCapacity());
            }
            fileOut.close();
        } catch (IOException e) {
            System.out.println("無法寫入柵欄資料。");
        }
    }

    ////////////
    private static Animal newAnimal(Scanner input) {
        System.out.println("請選擇動物類別：");
        System.out.println("1. 哺乳動物");
        System.out.println("2. 鳥類");
        System.out.println("3. 爬行動物");
        String categoryChoice = input.nextLine();
        System.out.print("請輸入動物名字: ");
        String name = input.nextLine();
        System.out.print("請輸入動物年齡: ");
        int age = Integer.parseInt(input.nextLine());
        System.out.print("請輸入動物健康狀態: ");
        String healthStatus = input.nextLine();

        if(categoryChoice.equals("1")) {
            categoryChoice = "哺乳動物";
        } else if (categoryChoice.equals("2")) {
            categoryChoice = "鳥類";
        } else if (categoryChoice.equals("3")) {
            categoryChoice = "爬行動物";
        }

        return new Animal(name, age, healthStatus,categoryChoice);
    }
    class Mammal extends Animal {
        public Mammal(String name, int age, String healthStatus,String categoryChoice) {
            super(name, age, healthStatus,categoryChoice);
        }
    }
    class Bird extends Animal {
        public Bird(String name, int age, String healthStatus,String categoryChoice) {
            super(name, age, healthStatus,categoryChoice);
        }
    }
    class Reptile extends Animal {
        public Reptile(String name, int age, String healthStatus,String categoryChoice) {
            super(name, age, healthStatus,categoryChoice);
        }
    }



/*
    private static Animal newAnimal(Scanner input) {
        System.out.println("請選擇動物類別：");
        System.out.println("1. 哺乳動物");
        System.out.println("2. 鳥類");
        System.out.println("3. 爬行動物");
        String categoryChoice = input.nextLine();
        System.out.print("請輸入動物名字: ");
        String name = input.nextLine();
        System.out.print("請輸入動物年齡: ");
        int age = Integer.parseInt(input.nextLine());
        System.out.print("請輸入動物健康狀態: ");
        String healthStatus = input.nextLine();

        switch (categoryChoice) {
            case "1":
                categoryChoice = "哺乳動物";
                newAnimal = new Mammal(name, age, healthStatus,categoryChoice);
                break;
            case "2":
                categoryChoice = "鳥類";
                newAnimal = new Bird(name, age, healthStatus,categoryChoice);
                break;
            case "3":
                categoryChoice = "爬行動物";
               // newAnimal = new Reptile(name, age, healthStatus,categoryChoice);
                break;
            default:
                System.out.println("無效的選擇，將創建一般動物。");
                //newAnimal = new Animal(name, age, healthStatus,categoryChoice);
                break;
        }

        zoo.add(newAnimal);
      //  System.out.println("已添加新动物：" + name);
    }*/
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



    private static void printAnimalList(ArrayList<Animal> zoo) {
        System.out.println("動物列表：");
        for (Animal animal : zoo) {
            animal.displayInfo();
        }
        System.out.println();
    }
}
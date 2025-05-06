import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name.trim();
        this.rollNumber = rollNumber;
        this.grade = grade.trim().toUpperCase();
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name.trim(); }
    public void setGrade(String grade) { this.grade = grade.trim().toUpperCase(); }

    @Override
    public String toString() {
        return rollNumber + "," + name + "," + grade;
    }

    public static Student fromString(String data) {
        String[] parts = data.split(",");
        return new Student(parts[1], Integer.parseInt(parts[0]), parts[2]);
    }
}

class StudentManagementSystem {
    private Map<Integer, Student> studentMap = new HashMap<>();
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getRollNumber())) {
            System.out.println("❌ Roll number already exists.");
            return;
        }
        studentMap.put(student.getRollNumber(), student);
        saveStudentsToFile();
        System.out.println("✅ Student added successfully.");
    }

    public void removeStudent(int rollNumber) {
        if (studentMap.remove(rollNumber) != null) {
            saveStudentsToFile();
            System.out.println("✅ Student removed.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    public void editStudent(int rollNumber, String newName, String newGrade) {
        Student student = studentMap.get(rollNumber);
        if (student != null) {
            student.setName(newName);
            student.setGrade(newGrade);
            saveStudentsToFile();
            System.out.println("✅ Student updated.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    public void searchStudent(int rollNumber) {
        Student student = studentMap.get(rollNumber);
        if (student != null) {
            System.out.println("🎓 Student Found: " + student.getName() + " | Grade: " + student.getGrade());
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    public void displayAllStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("⚠️ No students found.");
            return;
        }
        System.out.println("📋 All Students:");
        for (Student s : studentMap.values()) {
            System.out.println("Roll No: " + s.getRollNumber() + " | Name: " + s.getName() + " | Grade: " + s.getGrade());
        }
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : studentMap.values()) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving students: " + e.getMessage());
        }
    }

    private void loadStudentsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = Student.fromString(line);
                studentMap.put(student.getRollNumber(), student);
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading students: " + e.getMessage());
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1–6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int roll = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("⚠️ Name and grade cannot be empty.");
                        break;
                    }
                    sms.addStudent(new Student(name, roll, grade));
                    break;
                case 2:
                    System.out.print("Enter roll number to edit: ");
                    int editRoll = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new grade: ");
                    String newGrade = scanner.nextLine();
                    if (newName.isEmpty() || newGrade.isEmpty()) {
                        System.out.println("⚠️ Name and grade cannot be empty.");
                        break;
                    }
                    sms.editStudent(editRoll, newName, newGrade);
                    break;
                case 3:
                    System.out.print("Enter roll number to remove: ");
                    int removeRoll = scanner.nextInt();
                    sms.removeStudent(removeRoll);
                    break;
                case 4:
                    System.out.print("Enter roll number to search: ");
                    int searchRoll = scanner.nextInt();
                    sms.searchStudent(searchRoll);
                    break;
                case 5:
                    sms.displayAllStudents();
                    break;
                case 6:
                    System.out.println("👋 Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int studentId;
    private String name;
    private int age;
    private String grade;
    private String contact;

    public Student(int studentId, String name, int age, String grade, String contact) {
        this.studentId = studentId;
        setAge(age);
        setGrade(grade);
        this.name = name;
        this.contact = contact;
    }

    // Validation
    public void setAge(int age) {
        if (age > 0)
            this.age = age;
        else
            throw new IllegalArgumentException("Age must be positive.");
    }

    public void setGrade(String grade) {
        if (grade.matches("[A-F]"))
            this.grade = grade;
        else
            throw new IllegalArgumentException("Grade must be between A and F.");
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void update(String name, int age, String grade, String contact) {
        this.name = name;
        setAge(age);
        setGrade(grade);
        this.contact = contact;
    }

    public void display() {
        System.out.printf("%-6d %-10s %-5d %-6s %-12s%n",
                studentId, name, age, grade, contact);
    }
}

public class StudentInformationSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add 2.View 3.Update 4.Delete 5.Search 6.Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> updateStudent();
                    case 4 -> deleteStudent();
                    case 5 -> searchStudent();
                    case 6 -> System.exit(0);
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    static void addStudent() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Grade (A-F): ");
        String grade = sc.nextLine();

        System.out.print("Contact: ");
        String contact = sc.nextLine();

        students.add(new Student(id, name, age, grade, contact));
        System.out.println("Student added.");
    }

    static void viewStudents() {
        System.out.println("\nID     Name       Age   Grade  Contact");
        System.out.println("----------------------------------------");
        for (Student s : students)
            s.display();
    }

    static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.print("New Name: ");
                String name = sc.nextLine();
                System.out.print("New Age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("New Grade: ");
                String grade = sc.nextLine();
                System.out.print("New Contact: ");
                String contact = sc.nextLine();

                s.update(name, age, grade, contact);
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        students.removeIf(s -> s.getStudentId() == id);
        System.out.println("Student deleted if existed.");
    }

    static void searchStudent() {
        System.out.print("Enter ID or Name: ");
        sc.nextLine();
        String key = sc.nextLine();

        for (Student s : students) {
            if (String.valueOf(s.getStudentId()).equals(key) ||
                s.getName().equalsIgnoreCase(key)) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found.");
    }
}

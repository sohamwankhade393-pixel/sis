import java.util.*;

class Student {
    String id, name, contact;
    int age;
    ArrayList<Integer> marks = new ArrayList<>();

    Student(String id, String name, int age, String contact, ArrayList<Integer> marks) {
        this.id = id; this.name = name; this.age = age; this.contact = contact; this.marks = marks;
    }

    double average() {
        return marks.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    int highest() {
        return marks.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    int lowest() {
        return marks.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    char grade() {
        double a = average();
        if (a >= 90) return 'A';
        if (a >= 80) return 'B';
        if (a >= 70) return 'C';
        if (a >= 60) return 'D';
        return 'F';
    }
}

public class GradeManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static int readMark() {
        int m;
        while (true) {
            m = sc.nextInt();
            if (m >= 0 && m <= 100) return m;
            System.out.print("Invalid (0-100). Re-enter: ");
        }
    }

    static void addStudent() {
        System.out.print("ID: "); String id = sc.next();
        System.out.print("Name: "); sc.nextLine(); String name = sc.nextLine();
        System.out.print("Age: "); int age = sc.nextInt();
        System.out.print("Contact: "); String contact = sc.next();

        System.out.print("Number of subjects: "); int n = sc.nextInt();
        ArrayList<Integer> marks = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.print("Mark " + i + ": ");
            marks.add(readMark());
        }
        students.add(new Student(id, name, age, contact, marks));
        System.out.println("Student added.");
    }

    static void viewAll() {
        System.out.printf("%-6s %-15s %-3s %-6s %-4s%n","ID","Name","Age","Avg","G");
        for (Student s : students)
            System.out.printf("%-6s %-15s %-3d %-6.2f %-4c%n",
                    s.id, s.name, s.age, s.average(), s.grade());
    }

    static Student search(String key) {
        for (Student s : students)
            if (s.id.equalsIgnoreCase(key) || s.name.equalsIgnoreCase(key)) return s;
        return null;
    }

    static void searchStudent() {
        System.out.print("Enter ID or Name: ");
        String k = sc.next();
        Student s = search(k);
        if (s == null) { System.out.println("Not found."); return; }
        System.out.println("ID: " + s.id);
        System.out.println("Name: " + s.name);
        System.out.println("Avg: " + s.average());
        System.out.println("High/Low: " + s.highest() + "/" + s.lowest());
        System.out.println("Grade: " + s.grade());
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add 2.View 3.Search 4.Exit");
            System.out.print("Choice: ");
            int c = sc.nextInt();
            if (c == 1) addStudent();
            else if (c == 2) viewAll();
            else if (c == 3) searchStudent();
            else break;
        }
    }
}

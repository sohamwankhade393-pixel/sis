import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name, department, position;
    double salary;
    LocalDate joinDate;

    Employee(int id, String name, String department, String position,
             double salary, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    public String toString() {
        return id + " | " + name + " | " + department + " | " +
               position + " | " + salary + " | " + joinDate;
    }
}

class EmployeeService {
    ArrayList<Employee> list = new ArrayList<>();
    HashMap<Integer, Employee> map = new HashMap<>();
    String file = "employees.dat";

    void add(Employee e) {
        if (map.containsKey(e.id)) throw new RuntimeException();
        list.add(e);
        map.put(e.id, e);
    }

    Employee get(int id) {
        return map.get(id);
    }

    void updateSalary(int id, double salary) {
        map.get(id).salary = salary;
    }

    void delete(int id) {
        Employee e = map.remove(id);
        list.remove(e);
    }

    List<Employee> searchByName(String name) {
        List<Employee> r = new ArrayList<>();
        for (Employee e : list)
            if (e.name.equalsIgnoreCase(name)) r.add(e);
        return r;
    }

    List<Employee> searchByDepartment(String dept) {
        List<Employee> r = new ArrayList<>();
        for (Employee e : list)
            if (e.department.equalsIgnoreCase(dept)) r.add(e);
        return r;
    }

    double totalSalary() {
        double s = 0;
        for (Employee e : list) s += e.salary;
        return s;
    }

    double averageSalary() {
        return list.isEmpty() ? 0 : totalSalary() / list.size();
    }

    void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
        o.writeObject(list);
        o.close();
    }

    void load() throws Exception {
        File f = new File(file);
        if (!f.exists()) return;
        ObjectInputStream i = new ObjectInputStream(new FileInputStream(file));
        list = (ArrayList<Employee>) i.readObject();
        map.clear();
        for (Employee e : list) map.put(e.id, e);
        i.close();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        EmployeeService es = new EmployeeService();
        es.load();

        es.add(new Employee(1, "Amit", "IT", "Developer", 50000,
                LocalDate.of(2023, 1, 10)));
        es.add(new Employee(2, "Neha", "HR", "Manager", 60000,
                LocalDate.of(2022, 3, 5)));

        System.out.println(es.get(1));
        es.updateSalary(1, 55000);

        System.out.println(es.searchByDepartment("IT"));
        System.out.println(es.totalSalary());
        System.out.println(es.averageSalary());

        es.save();
    }
}

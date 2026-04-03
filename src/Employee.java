import java.util.Objects;

public class Employee {
    private static int nextId = 1;

    private final int id;
    private String fullName;
    private int department;   // от 1 до 5
    private int salary;       // от 50 до 450

    public Employee(String fullName, int department, int salary) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Department must be between 1 and 5");
        }
        if (salary < 50 || salary > 450) {
            throw new IllegalArgumentException("Salary must be between 50 and 450");
        }
        this.id = nextId++;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    // Геттеры
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public int getDepartment() { return department; }
    public int getSalary() { return salary; }

    // Сеттеры для отдела и зарплаты
    public void setDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Department must be between 1 and 5");
        }
        this.department = department;
    }

    public void setSalary(int salary) {
        if (salary < 50 || salary > 450) {
            throw new IllegalArgumentException("Salary must be between 50 and 450");
        }
        this.salary = salary;
    }

    // equals сравнивает только зарплату (бухгалтерский учёт)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary);
    }

    // Полная информация
    @Override
    public String toString() {
        return String.format("ID: %d | ФИО: %s | Отдел: %d | Зарплата: %d",
                id, fullName, department, salary);
    }

    // Краткая информация (ФИО + зарплата)
    public void printShortInfo() {
        System.out.println(fullName + ", зарплата: " + salary);
    }
}
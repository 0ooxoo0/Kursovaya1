public class EmployeeBook {
    private final Employee[] employees = new Employee[10];

    // 1. Получить список всех сотрудников (не null)
    public void printAllEmployees() {
        System.out.println("\n=== Список всех сотрудников ===");
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp);
            }
        }
    }

    // 2. Подсчитать среднее значение зарплат (только по не-null)
    public double getAverageSalary() {
        int count = 0;
        int sum = 0;
        for (Employee emp : employees) {
            if (emp != null) {
                count++;
                sum += emp.getSalary();
            }
        }
        if (count == 0) return 0;
        return (double) sum / count;
    }

    // 3. Вывести налоги по заданной схеме
    public void printTaxes(String type) {
        System.out.println("\n=== Расчёт налогов (" + type + ") ===");
        double totalTax = 0;
        for (Employee emp : employees) {
            if (emp == null) continue;
            double tax = 0;
            int salary = emp.getSalary();
            switch (type.toUpperCase()) {
                case "PROPORTIONAL":
                    tax = salary * 0.13;
                    break;
                case "PROGRESSIVE":
                    if (salary <= 150) tax = salary * 0.13;
                    else if (salary <= 350) tax = salary * 0.17;
                    else tax = salary * 0.21;
                    break;
                default:
                    System.out.println("Неизвестный тип налога. Используйте PROPORTIONAL или PROGRESSIVE");
                    return;
            }
            System.out.printf("%s | зарплата: %d | налог: %.2f%n",
                    emp.getFullName(), salary, tax);
            totalTax += tax;
        }
        System.out.printf("Общая сумма налогов: %.2f%n", totalTax);
    }

    // 4. Проиндексировать зарплату сотрудников указанного отдела на percent%
    public void indexSalariesByDepartment(int department, int percent) {
        System.out.printf("\n=== Индексация отдела %d на %d%% ===%n", department, percent);
        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];
            if (emp == null) continue;
            if (emp.getDepartment() != department) continue; // пропускаем другие отделы
            int newSalary = (int) Math.round(emp.getSalary() * (100 + percent) / 100.0);
            emp.setSalary(newSalary);
            System.out.printf("Индексирован: %s -> новая зарплата: %d%n",
                    emp.getFullName(), newSalary);
        }
    }

    // 5. Найти первого сотрудника отдела с зарплатой > threshold, вывести номер и краткую инфо
    public void findFirstByDepartmentAndSalary(int department, int salaryThreshold) {
        System.out.printf("\n=== Поиск первого сотрудника отдела %d с зарплатой > %d ===%n",
                department, salaryThreshold);
        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];
            if (emp != null && emp.getDepartment() == department && emp.getSalary() > salaryThreshold) {
                System.out.print("Порядковый номер: " + (i + 1) + " | ");
                emp.printShortInfo();
                return;
            }
        }
        System.out.println("Сотрудник не найден.");
    }

    // 6. Вывести первых employeeNumber сотрудников с зарплатой меньше wage
    public void printFirstEmployeesWithSalaryLessThan(int wage, int employeeNumber) {
        System.out.printf("\n=== Первые %d сотрудников с зарплатой < %d ===%n", employeeNumber, wage);
        int found = 0;
        int index = 0;
        while (index < employees.length && found < employeeNumber) {
            Employee emp = employees[index];
            if (emp != null && emp.getSalary() < wage) {
                emp.printShortInfo();
                found++;
            }
            index++;
        }
        if (found == 0) System.out.println("Нет сотрудников с зарплатой ниже " + wage);
    }

    // 7. Проверить, есть ли сотрудник в массиве с точки зрения бухгалтерии (по зарплате)
    public boolean containsEmployee(Employee target) {
        for (Employee emp : employees) {
            if (emp != null && emp.equals(target)) {
                return true;
            }
        }
        return false;
    }

    // 8. Добавить сотрудника в первую свободную ячейку
    public boolean addEmployee(Employee emp) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = emp;
                return true;
            }
        }
        return false; // нет свободного места
    }

    // 9. Получить сотрудника по id
    public Employee getEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp != null && emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    // Дополнительный метод: получить количество сотрудников (не null)
    public int getEmployeeCount() {
        int count = 0;
        for (Employee emp : employees) {
            if (emp != null) count++;
        }
        return count;
    }
}
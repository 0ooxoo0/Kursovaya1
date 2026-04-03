public class Main {
    public static void main(String[] args) {
        EmployeeBook book = new EmployeeBook();

        // Создаём 11 сотрудников (один не добавится)
        Employee[] testEmployees = {
                new Employee("Иванов Иван Иванович", 1, 120),
                new Employee("Петров Петр Петрович", 2, 250),
                new Employee("Сидорова Анна Сергеевна", 3, 380),
                new Employee("Кузнецов Дмитрий Алексеевич", 1, 90),
                new Employee("Смирнова Ольга Владимировна", 4, 430),
                new Employee("Васильев Артем Игоревич", 2, 150),
                new Employee("Морозова Екатерина Павловна", 5, 200),
                new Employee("Новиков Андрей Викторович", 3, 310),
                new Employee("Федорова Татьяна Дмитриевна", 4, 70),
                new Employee("Волков Сергей Николаевич", 5, 440),
                new Employee("Зайцева Мария Александровна", 1, 300) // 11-й, не добавится
        };

        System.out.println("=== Добавление сотрудников ===");
        for (Employee emp : testEmployees) {
            boolean added = book.addEmployee(emp);
            System.out.printf("Добавление %s: %s%n", emp.getFullName(), added ? "успешно" : "не удалось (нет места)");
        }

        // 1. Список всех сотрудников
        book.printAllEmployees();

        // 2. Средняя зарплата
        System.out.printf("\n=== Средняя зарплата ===\n%.2f\n", book.getAverageSalary());

        // 3. Налоги
        book.printTaxes("PROPORTIONAL");
        book.printTaxes("PROGRESSIVE");

        // 4. Индексация отдела 2 на 10%
        book.indexSalariesByDepartment(2, 10);
        book.printAllEmployees(); // проверка изменений

        // 5. Поиск первого сотрудника отдела 1 с зарплатой > 100
        book.findFirstByDepartmentAndSalary(1, 100);
        // Edge-case: несуществующий отдел
        book.findFirstByDepartmentAndSalary(10, 200);
        // Edge-case: зарплата выше максимальной
        book.findFirstByDepartmentAndSalary(2, 1000);

        // 6. Первые 2 сотрудника с зарплатой < 300
        book.printFirstEmployeesWithSalaryLessThan(300, 2);
        // Edge-case: больше чем сотрудников
        book.printFirstEmployeesWithSalaryLessThan(500, 20);
        // Edge-case: зарплата меньше минимальной
        book.printFirstEmployeesWithSalaryLessThan(40, 1);

        // 7. Проверка наличия по зарплате (equals)
        Employee dummy1 = new Employee("Тестовый", 1, 150);
        Employee dummy2 = new Employee("Другой", 2, 999);
        System.out.println("\n=== Проверка наличия по зарплате ===");
        System.out.printf("Сотрудник с зарплатой 150 существует: %b%n", book.containsEmployee(dummy1));
        System.out.printf("Сотрудник с зарплатой 999 существует: %b%n", book.containsEmployee(dummy2));

        // 8. Поиск по id
        System.out.println("\n=== Поиск по id ===");
        Employee found = book.getEmployeeById(5);
        if (found != null) {
            System.out.println("Найден: " + found);
        } else {
            System.out.println("Сотрудник с id 5 не найден");
        }
        // Edge-case: несуществующий id
        found = book.getEmployeeById(100);
        System.out.println("Поиск id=100: " + (found == null ? "не найден" : found));

        // Дополнительная проверка: повторное добавление в полный массив
        System.out.println("\n=== Попытка добавить ещё одного сотрудника ===");
        Employee extra = new Employee("Лишний Лишний Лишний", 1, 100);
        boolean added = book.addEmployee(extra);
        System.out.println("Результат добавления: " + added);
    }
}
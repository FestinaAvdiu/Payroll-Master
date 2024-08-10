import java.util.List;

public class DataSorter {
    public static void sortEmployeesByName(List<Employee> employees) {
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
    }

    public static void sortByMissedHours(List<Employee> employees) {
        // LAMBDA expression
        employees.sort((emp1, emp2) -> {
            // Compare employees based on missed hours (most missed hours first)
            return Integer.compare(emp2.getMissedHours(), emp1.getMissedHours());
        });
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    public static List<Employee> readEmployeeDataFromFile(String filename) {
        List<Employee> employees = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            // Skip the header line if it exists
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 6) {
                    int identifier = Integer.parseInt(data[0]);
                    String name = data[1].trim();
                    String position = data[2].trim();
                    int requiredWorkHours = Integer.parseInt(data[3].trim());
                    double wage = Double.parseDouble(data[4].trim());
                    double overtimeWage = Double.parseDouble(data[5].trim());

                    if ("Manager".equalsIgnoreCase(position)) {
                        double basicWage = wage;
                        employees.add(new Manager(identifier, name, position, requiredWorkHours, basicWage, overtimeWage, 0, 0, 0, basicWage));
                    } else if ("Worker".equalsIgnoreCase(position)) {
                        double hourlyWage = wage;
                        employees.add(new Worker(identifier, name, position, requiredWorkHours, hourlyWage, overtimeWage, 0, 0, 0, hourlyWage));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file '" + filename + "' was not found.");
        }

        return employees;
    }

    public static void readDailyWorkedHours(String filename, List<Employee> employees, int numberOfDays) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            // Skip the header line if it exists
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 2) {
                    int identifier = Integer.parseInt(data[0].trim());
                    int workedHours = Integer.parseInt(data[1].trim());

                    // Find the corresponding Employee by identifier and update their worked hours
                    for (Employee employee : employees) {
                        if (employee.getIdentifier() == identifier) {
                            employee.setWorkedHours(workedHours);
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file '" + filename + "' was not found.");
        }
    }
}


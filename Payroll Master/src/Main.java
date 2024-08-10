import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int numberOfDays = getNumberOfDays();

        List<Employee> employees = readEmployeeDataAndDailyHours(numberOfDays);

        int[] monthlyWorkedHours = processMonthlyData(employees, numberOfDays);

        // Get user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Choose one of these options:");
            System.out.println();
            System.out.println("1. Print the Existing Data about employees");
            System.out.println("2: Print the calculated Monthly Data about each employee");
            System.out.println("3: Print Current Wages of employees in alphabetic order of employee name");
            System.out.println("4. Print List of Employees in order of missed hours (most in front)");
            System.out.println("5. Print the Total Amount the company has to pay for normal wage, overtime wage, and loss");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printEmployeeData(employees);
                    break;
                case 2:
                    printMonthlyData(employees, monthlyWorkedHours);
                    break;
                case 3:
                    calculateAndPrintMonthlyWages(employees, monthlyWorkedHours);
                    break;
                case 4:
                    printByMissedHours(employees);
                    break;
                case 5:
                    calculateAndPrintTotalAmounts(employees, monthlyWorkedHours, numberOfDays);
                    break;
                case 6:
                    System.out.println("Thank you! Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static int getNumberOfDays() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to our TECH COMPANY!");
        System.out.println();

        int numberOfDays;
        while (true) {
            System.out.print("Enter the number of days to calculate payments for: ");
            numberOfDays = scanner.nextInt();

            if (numberOfDays > 22) {
                System.out.println("Invalid! An employee usually works 22 days a month!");
                System.out.println("Choose a number between 1 and 22");
            } else {
                break; // Valid input, exit the loop
            }
        }

        return numberOfDays;
    }

    private static List<Employee> readEmployeeDataAndDailyHours(int numberOfDays) {
            List<Employee> employees = DataReader.readEmployeeDataFromFile("C:/Users/Admin/Downloads/Payroll Master/Payroll Master/Data Files/catalogfile.txt");
            DataReader.readDailyWorkedHours("C:/Users/Admin/Downloads/Payroll Master/Payroll Master/Data Files/dailyfile.txt", employees, numberOfDays);
            DataReader.readDailyWorkedHours("C:/Users/Admin/Downloads/Payroll Master/Payroll Master/Data Files/seconddailyfile.txt", employees, numberOfDays);
            return employees;
    }

    private static void printEmployeeData(List<Employee> employees) {
        System.out.println("List of employees: ");
            for (Employee employee : employees) {

                System.out.println("Identifier: " + employee.getIdentifier());
                System.out.println("Name: " + employee.getName());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Required Work Hours: " + employee.getRequiredWorkHours());

                if (employee instanceof Manager manager) {
                    System.out.println("Basic Wage: $" + manager.getBasicWage());
                    System.out.println("Overtime Wage: $" + manager.getOvertimeWage());
                } else if (employee instanceof Worker worker) {
                    System.out.println("Hourly Wage: $" + worker.getHourlyWage());
                    System.out.println("Overtime Wage: $" + worker.getOvertimeWage());
                }

                System.out.println();
            }
    }

    public static int[] processMonthlyData(List<Employee> employees, int numberOfDays) {
            int[] monthlyWorkedHours = new int[employees.size()];
            Arrays.fill(monthlyWorkedHours, 0);
            // Number of Days is set by user input - working days in a month
            for (int day = 1; day <= numberOfDays; day++) {
                for (Employee employee : employees) {
                    int workedHours = employee.getWorkedHours();

                    if (workedHours > employee.getRequiredWorkHours()) {
                        int extraHours = workedHours - employee.getRequiredWorkHours();
                        employee.setOvertimeHours(employee.getOvertimeHours() + extraHours);
                        workedHours = employee.getRequiredWorkHours();
                    } else if (workedHours < employee.getRequiredWorkHours()) {
                        int missedHours = employee.getRequiredWorkHours() - workedHours;
                        employee.setMissedHours(employee.getMissedHours() + missedHours);
                    }

                    monthlyWorkedHours[employee.getIdentifier() - 1] += workedHours;
                }
            }
            return monthlyWorkedHours;
    }

    private static void printMonthlyData(List<Employee> employees, int[] monthlyWorkedHours) {
        System.out.println("Printing the monthly data:");
        System.out.println();
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                int monthlyHours = monthlyWorkedHours[i];

                System.out.println("Identifier: " + employee.getIdentifier());
                System.out.println("Employee: " + employee.getName());
                // Basic hours are the required part of hours worked by the employee, while overtime and missed hours are not basic, so separated
                System.out.println("Monthly Basic Hours: " + monthlyHours);
                System.out.println("Monthly Overtime Hours: " + employee.getOvertimeHours());
                System.out.println("Monthly Missed Hours: " + employee.getMissedHours());
                System.out.println();
            }
    }

    private static void calculateAndPrintMonthlyWages(List<Employee> employees, int[] monthlyWorkedHours) {
        for (Employee employee : employees) {
            if (employee instanceof Manager manager) {
                double monthlyWage = manager.getBasicWage() + (manager.getOvertimeHours() * manager.getOvertimeWage());
                employee.setWage(monthlyWage);
            } else if (employee instanceof Worker worker) {
                double hourlyWage = worker.getHourlyWage();
                double overtimeWage = worker.getOvertimeWage();

                int basicWorkHours = monthlyWorkedHours[worker.getIdentifier() - 1]; // Actual worked hours for the month
                double wage = (basicWorkHours * hourlyWage) + (worker.getOvertimeHours() * hourlyWage * (1 + overtimeWage));
                employee.setWage(wage);
            } else {
                // Handle other types of employees if needed
                employee.setWage(0); // Default value
            }
        }

        // Sort employees by name
        DataSorter.sortEmployeesByName(employees);

        // Print employees and their monthly wages
        System.out.println("List of wages (sorted by name): ");
        System.out.println();
        for (Employee employee : employees) {
            System.out.println("Name: " + employee.getName());
            System.out.println("Monthly Wage: $" + employee.getWage());
            System.out.println();
        }
    }

    private static void printByMissedHours(List<Employee> employees){
        // Sort employees by missed hours
        DataSorter.sortByMissedHours(employees);

        // Print the employees names by this sort
        System.out.println("Print employees in order of missed hours: ");
        System.out.println();
        for (Employee employee : employees) {
            System.out.println("Name: " + employee.getName());
            System.out.println("Missed Hours: " + employee.getMissedHours());
            System.out.println();
        }
    }

    private static void calculateAndPrintTotalAmounts(List<Employee> employees, int[] monthlyWorkedHours, int numberOfDays) {
        double totalNormalWage = 0.0;
        double totalOvertimeWage = 0.0;
        double totalLoss = 0.0;

        for (Employee employee : employees) {
            double normalWage = 0.0; // Initialized variables here
            double overtimeWage = 0.0;
            double loss = 0.0;

            if (employee instanceof Manager manager) {

                // Calculate a sort of "hourly pay rate" for Managers
                // So we can calculate the loss by MissedHours
                // Formula = basic wage / length of month / required work hours
                double hourlyPay = (manager.getBasicWage() / numberOfDays) / manager.getRequiredWorkHours();

                // Calculate loss for Managers based on missed hours
                // Formula: missedHours * the "hourlyPay"
                loss = manager.getMissedHours() * hourlyPay;

                // For Managers, use the manager's basic wage as normal wage
                normalWage = manager.getBasicWage();

                // For Managers, use formula: overtime hours * overtime wage
                overtimeWage = manager.getOvertimeHours() * manager.getOvertimeWage();

                System.out.println("Name: " + manager.getName());
                System.out.println("Normal Wage: $" + normalWage);
                System.out.println("Overtime Wage: $" + overtimeWage);
                System.out.println("Loss: $" + loss);
                System.out.println();


            } else if (employee instanceof Worker worker) {

                // Loss is 0 for Workers, they are paid hourly
                loss = 0.0;

                // Calculate normal wage for Workers using the formula:
                // (precalculated) monthly worked hours * hourly wage
                normalWage = monthlyWorkedHours[worker.getIdentifier() - 1] * worker.getHourlyWage();

                // Calculate overtime wage for Workers using the formula:
                // Overtime Hours * Hourly Wage * (1 + Overtime Wage)
                overtimeWage = worker.getOvertimeHours() * worker.getHourlyWage() * (1 + worker.getOvertimeWage());

                System.out.println("Name: " + worker.getName());
                System.out.println("Normal Wage: $" + normalWage);
                System.out.println("Overtime Wage: $" + overtimeWage);
                System.out.println("Loss: $" + loss);
                System.out.println();
            }

            // Calculate total amounts
            totalNormalWage += normalWage;
            totalOvertimeWage += overtimeWage;
            totalLoss += loss;
        }

        // Print total amount for all
        System.out.println("The total amount of $ the company has to pay: ");
        System.out.println();
        System.out.println("Total Normal Wage: $" + totalNormalWage);
        System.out.println("Total Overtime Wage: $" + totalOvertimeWage);
        System.out.println("Total Loss: $" + totalLoss);
    }
}
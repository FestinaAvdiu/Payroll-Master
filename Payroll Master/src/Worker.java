public class Worker extends Employee{

    private final double hourlyWage;

    public Worker(int identifier, String name, String position, int requiredWorkHours, double wage, double overtimeWage, int workedHours, int missedHours, int overtimeHours, double hourlyWage) {
        super(identifier, name, "Worker", requiredWorkHours, wage, overtimeWage, workedHours, missedHours, overtimeHours);
        this.hourlyWage = hourlyWage;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }
}

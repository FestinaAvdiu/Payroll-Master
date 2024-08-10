public class Manager extends Employee{

    private final double basicWage;

    public Manager(int identifier, String name, String position, int requiredWorkHours, double wage, double overtimeWage, int workedHours, int missedHours, int overtimeHours, double basicWage) {
        super(identifier, name, "Manager", requiredWorkHours, wage, overtimeWage, workedHours, missedHours, overtimeHours);
        this.basicWage = basicWage;
    }

    public double getBasicWage() {
        return basicWage;
    }

}

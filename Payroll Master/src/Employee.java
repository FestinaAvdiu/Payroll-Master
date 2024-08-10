public class Employee {

    private final int identifier;
    private final String name;
    private final String position;
    private final int requiredWorkHours;
    private double wage;
    private final double overtimeWage;
    private int workedHours;
    private int missedHours;
    private int overtimeHours;

    public Employee(int identifier, String name, String position, int requiredWorkHours, double wage, double overtimeWage, int workedHours, int missedHours, int overtimeHours) {
        this.identifier = identifier;
        this.name = name;
        this.position = position;
        this.requiredWorkHours = requiredWorkHours;
        this.wage = wage;
        this.overtimeWage = overtimeWage;
        this.workedHours = workedHours;
        this.missedHours = missedHours;
        this.overtimeHours = overtimeHours;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }


    public int getRequiredWorkHours() {
        return requiredWorkHours;
    }


    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getOvertimeWage() {
        return overtimeWage;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public int getMissedHours() {
        return missedHours;
    }

    public void setMissedHours(int missedHours) {
        this.missedHours = missedHours;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

}

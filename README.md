# 🚀 Payroll Master

Welcome to the **Payroll Master**!  This Java-based console app is your go-to tool for managing employee payrolls with ease. Whether you're a manager keeping tabs on your team's hours or an employee curious about your wages, this system has got you covered!

## ✨ Features

- **Employee Data Management**: Input and manage employee details like name, position, and work hours.
- **Daily Worked Hours Tracking**: Keep tabs on how many hours each employee works daily.
- **Monthly Calculations**: Automatically calculate monthly worked hours, overtime, and missed hours.
- **Wage Calculation**: Get precise wage calculations for normal hours and overtime.
- **Employee Sorting**: Sort employees alphabetically, by missed hours, or wages.
- **Expense Calculation**: Calculate the total amount the company owes, including wages, overtime, and any losses.

## 🗂️ Project Structure

```plaintext
Payroll Master
├── src
│   |
│   ├── Main.java             # The entry point of the program
│   ├── Employee.java         # Employee class and subclasses
|   |        ├── Worker.java       
│   |        └── Manager.java 
|   |
│   ├── DataReader.java       # Reads employee and work hours data
│   └── DataSorter.java       # Sorts employees by various criteria
|
└── Data Files
        ├── catalogfile.txt     # Sample employee data
        ├── dailyfile.txt       # Sample daily work hours at the first site of the company
        └── seconddailyfile.txt # Sample daily work hours at the second site
```

## 🚀 How It Works

1. **Start the Program**: Enter the number of working days to calculate payroll for. (Psst! An average month is about 22 days!)

2. **Choose Your Adventure**: The main menu lets you:
   - View employee data
   - See monthly data
   - Print wages in alphabetical order
   - Sort employees by missed hours
   - Calculate total company expenses

3. **Sit Back and Relax**: Let the system handle the calculations and sorting. 🎉

## 📋 Example Output

```plaintext
Choose one of these options:
1. Print Existing Employee Data
2. Print Calculated Monthly Data
3. Print Current Wages in Alphabetical Order
4. List Employees by Missed Hours (Most First)
5. Print Total Company Expenses
6. Exit
```

## 💻 Technologies Used

- **Java**: The magic behind the curtain.
- **Object-Oriented Programming**: Using inheritance and polymorphism to keep things neat.
- **File I/O**: Reading data from files like a pro.

## 🚀 How to Run the Project

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/FestinaAvdiu/Payroll-Master.git

2. **Navigate to the Project Directory**:

    ```bash
    cd payroll-master
    ```

3. **Compile and Run**:

    ```bash
    javac Main.java
    java Main
    ```

4. **Follow the Prompts**: Enter the number of working days and explore the options!

### 👩‍💻 Happy coding! May your payroll calculations always be accurate! 😄


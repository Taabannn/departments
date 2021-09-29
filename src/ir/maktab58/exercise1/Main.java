package ir.maktab58.exercise1;

import ir.maktab58.exercise1.dataaccess.*;
import ir.maktab58.exercise1.models.*;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DepartmentDataBaseAccess departmentAccess = new DepartmentDataBaseAccess();
        ClerkDataBaseAccess clerkAccess = new ClerkDataBaseAccess();
        ArrayList<Department> departments = departmentAccess.getAllDepartments();
        ArrayList<Clerk> clerks = clerkAccess.getAllClerks(departments);
        Scanner inputLine = new Scanner(System.in);
        System.out.println("Please enter your username and password.");
        String userAndPass = deleteLastSpaces(inputLine.nextLine());
        while (isThisUserAllowed(userAndPass)) {
            departmentAccess.findClerksInEachDepartment(departments, clerkAccess);
            System.out.println("**********Welcome**********");
            System.out.println("1) save new department info");
            System.out.println("2) save new clerk info");
            System.out.println("3) update department info");
            System.out.println("4) update clerk info");
            System.out.println("5) show all departments info");
            System.out.println("6) show all clerks info");
            System.out.println("7) exit");
            String choice = inputLine.nextLine();
            if (choice.equals("1")) {
                departments = addNewDepartment(departmentAccess, departments, inputLine);
            } else if (choice.equals("2")) {
                clerks = addNewClerk(clerkAccess, clerks, departments, inputLine, departmentAccess);
            } else if (choice.equals("3")) {
                departments = updateDepartmentInfo(departmentAccess, departments, inputLine, clerkAccess);
            } else if (choice.equals("4")) {
                clerks = updateClerkInfo(clerkAccess, departments, clerks, inputLine);
            } else if (choice.equals("5")) {
                showAllDepartmentsInfo(departments);
            } else if (choice.equals("6")) {
                showAllClerksInfo(clerks);
            } else if (choice.equals("7")) {
                break;
            } else {
                System.out.println("Invalid input command. Your choice must be an integer between 1 to 7.");
            }
        }
    }

    public static String deleteLastSpaces(String inputLine) {
        if (inputLine.length() == 0) {
            System.out.println("Input buffer is empty.");
            return inputLine;
        }

        if (inputLine.charAt(inputLine.length() - 1) != ' ')
            return inputLine;

        if (inputLine.equals(" ")) {
            System.out.println("Input buffer is just a space char.");
            return inputLine;
        }

        inputLine = inputLine.substring(0, inputLine.length() - 2);
        return deleteLastSpaces(inputLine);
    }

    public static boolean isThisUserAllowed(String userAndPass) {
        String[] lineTokens = userAndPass.split(" ");
        if (lineTokens.length != 2) {
            System.out.println("You've entered less or more than two inputs.");
            return false;
        }
        return Admin.isThisUserAnAdmin(lineTokens[0], lineTokens[1]);
    }

    public static boolean isItValidInt(String phoneNumberStr) {
        for (int i = 0; i < phoneNumberStr.length(); i++) {
            if ((phoneNumberStr.charAt(i) < 48) || (phoneNumberStr.charAt(i) > 57))
                return false;
        }
        return true;
    }

    public static Department getDepartment(ArrayList<Department> departments, int departmentId) {
        for (Department department : departments) {
            if (department.getDepartmentId() == departmentId)
                return department;
        }
        return null;
    }

    public static Department getDepartment(ArrayList<Department> departments, String departmentName) {
        for (Department department : departments) {
            if (department.getDepartmentName().equals(departmentName))
                return department;
        }
        System.out.println("Department with departmentName = " + departmentName + " is not existed");
        return null;
    }

    public static Clerk getClerk(ArrayList<Clerk> clerks, int clerkId) {
        for (Clerk clerk : clerks) {
            if (clerk.getClerkId() != clerkId) {
                continue;
            }
            return clerk;
        }
        return null;
    }

    public static boolean validatePersonnelIdAndDateProperties(String personnelId, String year, String month, String day) {
        if (!isItValidInt(personnelId)) {
            System.out.println("PersonnelId must be an integer.");
            return false;
        }
        if (!isItValidInt(year)) {
            System.out.println("year must be an integer.");
            return false;
        }
        if (!isItValidInt(month)) {
            System.out.println("month must be an integer.");
            return false;
        }
        if (!isItValidInt(day)) {
            System.out.println("day must be an integer.");
            return false;
        }
        return true;
    }

    public static MyDate getBirthDate(String lineTokens1, String lineTokens2, String lineTokens3) {
        int year = Integer.parseInt(lineTokens1);
        int month = Integer.parseInt(lineTokens2);
        int day = Integer.parseInt(lineTokens3);
        MyDate birthDate = new MyDate(year, month, day);
        if (birthDate.isValidDate(day, month, year))
            return birthDate;
        System.out.println("There is no date with these inputs that you've entered.");
        return null;
    }

    public static void showAllDepartmentsInfo(ArrayList<Department> departments) {
        if (departments.size() == 0) {
            System.out.println("department table is empty. nothing to print.");
            return;
        }
        for (Department department : departments) {
            System.out.println(department);
        }
    }

    public static void showAllClerksInfo(ArrayList<Clerk> clerks) {
        if (clerks.size() == 0) {
            System.out.println("clerks table is empty. nothing to print.");
            return;
        }
        for (Clerk clerk : clerks) {
            System.out.println(clerk);
        }
    }

    public static ArrayList<Clerk> updateClerkInfo(ClerkDataBaseAccess clerkAccess, ArrayList<Department> departments,
                                                   ArrayList<Clerk> clerks, Scanner inputLine) throws SQLException {
        System.out.println("Please enter Id of clerk you'd like to update.");
        String idStr = deleteLastSpaces(inputLine.nextLine());
        if (!isItValidInt(idStr)) {
            System.out.println("Id of clerk must be an integer. Please try again.");
            return clerks;
        }
        int clerkId = Integer.parseInt(idStr);
        Clerk clerk = getClerk(clerks, clerkId);
        if (clerk == null) {
            System.out.println("clerk with clerk id " + clerkId + " is not existed.");
            return clerks;
        }
        System.out.println("Please enter new firstname of this clerk");
        String firstname = inputLine.nextLine();
        System.out.println("Please enter new lastname of this clerk");
        String lastname = inputLine.nextLine();
        int result = clerkAccess.updateClerk(clerk, firstname, lastname);
        if (result == 1) {
            System.out.println("This item is updated successfully.");
        }
        else
            System.out.println("Sorry! New Changes are discarded. Please try again.");
        return clerkAccess.getAllClerks(departments);
    }

    public static ArrayList<Department> updateDepartmentInfo(DepartmentDataBaseAccess departmentAccess, ArrayList<Department> departments,
                                                             Scanner inputLine, ClerkDataBaseAccess clerkAccess) throws SQLException {
        System.out.println("Please enter Id of department you'd like to update.");
        String idStr = deleteLastSpaces(inputLine.nextLine());
        if (!isItValidInt(idStr)) {
            System.out.println("Id of department must be an integer. Please try again.");
            return departments;
        }
        int departmentId = Integer.parseInt(idStr);
        Department department = getDepartment(departments, departmentId);
        if (department == null) {
            System.out.println("department with department id " + departmentId + " is not existed.");
            return departments;
        }

        System.out.println("Please enter new name of department.");
        String newDepartmentName = inputLine.nextLine();
        int result = departmentAccess.updateDepartment(department, newDepartmentName, clerkAccess);
        if (result == 1)
            System.out.println("This item is updated successfully.");
        else
            System.out.println("Sorry! New Changes are discarded. Please try again.");
        return departmentAccess.getAllDepartments();
    }

    public static ArrayList<Clerk> addNewClerk(ClerkDataBaseAccess clerkAccess, ArrayList<Clerk> clerks,
                                   ArrayList<Department> departments, Scanner inputLine,
                                   DepartmentDataBaseAccess departmentAccess) throws SQLException {
        System.out.println("Please enter firstname, lastname, personnelId, birthDate(year, month, day), department name of clerk you like to add.");
        String line = deleteLastSpaces(inputLine.nextLine());
        String[] lineTokens = line.split(" ");
        if (lineTokens.length != 7) {
            System.out.println("Invalid input");
            return clerks;
        }
        if (!validatePersonnelIdAndDateProperties(lineTokens[2], lineTokens[3], lineTokens[4], lineTokens[5]))
            return clerks;
        MyDate birthDate = getBirthDate(lineTokens[3], lineTokens[4], lineTokens[5]);
        if (birthDate == null)
            return clerks;
        Department department = getDepartment(departments, lineTokens[6]);
        if (department == null)
            return clerks;
        int personnelId = Integer.parseInt(lineTokens[2]);
        if (isClerkExisted(clerks, personnelId)){
            System.out.println("This clerk is already existed! Please try again.");
            return clerks;
        }
        Clerk newClerk = new Clerk(clerks.size() + 1, lineTokens[0], lineTokens[1], personnelId, birthDate, department);
        boolean checker = clerkAccess.saveClerk(clerks, newClerk);
        if (checker)
            System.out.println("new clerk is added successfully");
        return clerkAccess.getAllClerks(departments);
    }

    public static boolean isClerkExisted(ArrayList<Clerk> clerks, int personnelId){
        for (Clerk clerk: clerks){
            if (clerk.getPersonnelIdNumber() == personnelId)
                return true;
        }
        return false;
    }

    public static ArrayList<Department> addNewDepartment(DepartmentDataBaseAccess departmentAccess, ArrayList<Department> departments,
                                        Scanner inputLine) throws SQLException, ClassNotFoundException {
        System.out.println("Please enter name and phone number of department you like to add.");
        String line = deleteLastSpaces(inputLine.nextLine());
        String[] lineTokens = line.split(" ");
        if (lineTokens.length != 2) {
            System.out.println("Invalid input");
            return departments;
        }
        if(!isItValidInt(lineTokens[1])){
            System.out.println("Entered phone number is not valid.");
            return departments;
        }
        if (isDepartmentExisted(departments, lineTokens[1], lineTokens[0])){
            System.out.println("This department is already existed! Please try again.");
            return departments;
        }
        Department newDepartment = new Department(departments.size() + 1, lineTokens[0], lineTokens[1]);
        boolean checker = departmentAccess.saveDepartment(newDepartment, departments);
        if (checker)
            System.out.println("new department is added successfully");
        return departmentAccess.getAllDepartments();
    }

    public static boolean isDepartmentExisted(ArrayList<Department> departments, String phonenumber, String name){
        for (Department department: departments){
            if (department.getDepartmentName().equals(name))
                if (department.getPhoneNumber().equals(phonenumber))
                     return true;
        }
        return false;
    }
}

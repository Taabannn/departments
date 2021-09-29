package ir.maktab58.exercise1.dataaccess;

import ir.maktab58.exercise1.models.Clerk;
import ir.maktab58.exercise1.models.Department;
import ir.maktab58.exercise1.models.MyDate;

import java.sql.*;
import java.util.*;

public class ClerkDataBaseAccess extends DataBaseAccess{
    public ClerkDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public boolean saveClerk(ArrayList<Clerk> clerks, Clerk clerk) throws SQLException {
        if (connection != null) {
            int currentClerkId = clerks.size() + 1;
            Department department =  clerk.getCurrentDepartment();
            department.addNewClerk(clerk);
            MyDate clerkBirthDate = clerk.getBirthDate();
            String sqlQuery = String.format("INSERT INTO office.clerks (clerks_id, first_name, last_name, personnel_id, birth_date, department_name, fk_department_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            pstmt.setInt(1, currentClerkId);
            pstmt.setString(2, clerk.getFirstname());
            pstmt.setString(3, clerk.getLastname());
            pstmt.setInt(4, clerk.getPersonnelIdNumber());
            pstmt.setString(5, clerkBirthDate.toString());
            pstmt.setString(6, department.getDepartmentName());
            pstmt.setInt(7, department.getDepartmentId());
            boolean result = pstmt.execute();
            return !result;
        }
        return false;
    }

    public int updateClerk(Clerk clerk, String newFirstName, String newLastName) throws SQLException {
        if (connection != null) {
            String sqlQuery = String.format("UPDATE clerks SET first_name = '%s', last_name = '%s' WHERE clerks_id = %d",
                        newFirstName,newLastName, clerk.getClerkId(), clerk.getClerkId());
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            int result = preparedStatement.executeUpdate(sqlQuery);
            return result;
        }
        return 0;
    }

    public ArrayList<Clerk> getAllClerks(List<Department> departments) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clerks");
            ArrayList<Clerk> clerksList = new ArrayList<>();
            while (resultSet.next()) {
                MyDate birthDate = new MyDate(resultSet.getString("birth_date"));
                Department department = findDepartment(resultSet.getString("department_name"), departments);
                Clerk clerk = new Clerk(resultSet.getInt("clerks_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getInt("personnel_id"), birthDate, department);
                if (clerk == null)
                    break;
                clerksList.add(clerk);
            }
            return clerksList;
        }
        return null;
    }

    public Department findDepartment(String name, List<Department> departments) {
        for (Department department : departments) {
            if (department.getDepartmentName().equals(name)) {
                return department;
            }
        }
        return null;
    }

    public void updateDepartmentName(Department department) throws SQLException {
        if (connection != null) {
            String sqlQuery = String.format("UPDATE clerks SET department_name = '%s' WHERE fk_department_id = %d",
                    department.getDepartmentName(), department.getDepartmentId());
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            int result = preparedStatement.executeUpdate(sqlQuery);
            if (result == 1)
                System.out.println("clerks of this department are updated successfully.");
        }
    }
}

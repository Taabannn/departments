package ir.maktab58.exercise1.dataaccess;

import ir.maktab58.exercise1.models.*;

import java.sql.*;
import java.util.*;

public class DepartmentDataBaseAccess extends DataBaseAccess{
    public DepartmentDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public boolean saveDepartment(Department department, ArrayList<Department> departments) throws SQLException, ClassNotFoundException {
        if (connection == null)
            return false;
        int departmentId = departments.size() + 1;
        String sqlQuery = String.format("INSERT INTO office.departments " +
                "(department_id, department_name, department_phonenumber) VALUES (?, ?, ?)");
        PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
        pstmt.setInt(1, departmentId);
        pstmt.setString(2, department.getDepartmentName());
        pstmt.setString(3, department.getPhoneNumber());
        boolean result = pstmt.execute();
        return !result;
    }

    public int updateDepartment(Department department, String newDepartmentName, ClerkDataBaseAccess clerkAccess) throws SQLException {
        if (connection != null) {
            department.setDepartmentName(newDepartmentName);
            String sqlQuery = String.format("UPDATE office.departments SET department_name ='%s' WHERE department_id=%d", department.getDepartmentName(), department.getDepartmentId());
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            clerkAccess.updateDepartmentName(department);
            int result = preparedStatement.executeUpdate(sqlQuery);
            return result;
        }
        return 0;
    }

    public ArrayList<Department> getAllDepartments() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from departments");
            ArrayList<Department> departmentsList = new ArrayList<>();
            while (resultSet.next()) {
                Department department = new Department(resultSet.getInt("department_id"), resultSet.getString("department_name"), resultSet.getString("department_phonenumber"));
                departmentsList.add(department);
            }
            return departmentsList;
        }
        return null;
    }

    public void findClerksInEachDepartment(ArrayList<Department> departments, ClerkDataBaseAccess clerkAccess) throws SQLException {
        changeReferenceOfClerks(departments);
        ArrayList<Clerk> clerks = clerkAccess.getAllClerks(departments);
        for (Department department: departments)
            for (Clerk clerk : clerks)
                if (clerk.getCurrentDepartment().getDepartmentName().equals(department.getDepartmentName()))
                    department.addNewClerk(clerk);
    }

    public void changeReferenceOfClerks(ArrayList<Department> departments){
        for (Department department: departments) {
            ArrayList<Clerk> clerks = new ArrayList<>();
            department.setClerks(clerks);
        }
    }
}

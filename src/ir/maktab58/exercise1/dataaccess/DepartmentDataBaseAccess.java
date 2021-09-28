package ir.maktab58.exercise1.dataaccess;

import ir.maktab58.exercise1.models.Clerk;
import ir.maktab58.exercise1.models.Department;
import ir.maktab58.exercise1.models.MyDate;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentDataBaseAccess extends DataBaseAccess{
    public DepartmentDataBaseAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public boolean saveDepartment(Department department, ArrayList<Department> departments) throws SQLException {
        if (connection != null) {
            int departmentId = departments.size() + 1;
            if (department.getClerks() == null) {
                String sqlQuery = String.format("INSERT INTO department " +
                        "(department_id, department_name, department_phonenumber) VALUES (?, ?, ?)");
                PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
                pstmt.setInt(1, departmentId);
                pstmt.setString(2, department.getDepartmentName());
                pstmt.setString(3, department.getPhoneNumber());
                boolean result = pstmt.execute();
                departmentId++;
                return result;
            }
        }
        return false;
    }

    public int updateDepartment(Department department, String newDepartmentName) throws SQLException {
        if (connection != null) {
            department.setDepartmentName(newDepartmentName);
            String sqlQuery = String.format("update establishment.department \nset department_name = ? \nwhere department_id = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setInt(2, department.getDepartmentId());
            preparedStatement.executeUpdate(sqlQuery);
            return 1;
        }
        return 0;
    }

    public ArrayList<Department> getAllDepartments() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from department");
            ArrayList<Department> departmentsList = new ArrayList<>();
            while (resultSet.next()) {
                Department department = new Department(resultSet.getInt("department_id"), resultSet.getString("department_name"), resultSet.getString("department_phonenumber"));
                departmentsList.add(department);
            }
            return departmentsList;
        }
        return null;
    }

    /*public int updateDepartment(Department department, Clerk clerk) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("UPDATE department SET clerks_id = '%s', WHERE department_id = '%i'",
                    clerk.getClerkId(), department.getDepartmentId());
            int result = statement.executeUpdate(sqlQuery);
            return result;
        }
        return 0;
    }

    public boolean addNewClerkToDepartment(Department department, ArrayList<Department> departments, Clerk clerk) throws SQLException {
        int departmentId = departments.size() + 1;
        String sqlQuery = String.format("INSERT INTO department " +
                "(department_id, department_name, department_phonenumber, clerks_id) VALUES (?, ?, ?, ?)");
        PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
        pstmt.setInt(1, departmentId);
        pstmt.setString(2, department.getDepartmentName());
        pstmt.setString(3, department.getPhoneNumber());
        pstmt.setInt(4, clerk.getClerkId());
        boolean result = pstmt.execute();
        departmentId++;
        return result;
    }*/
}

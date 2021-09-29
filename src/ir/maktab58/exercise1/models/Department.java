package ir.maktab58.exercise1.models;

import java.util.ArrayList;

public class Department {
    private int departmentId;
    private String departmentName;
    private String phoneNumber;
    private ArrayList<Clerk> clerks = new ArrayList<Clerk>();

    public Department(int departmentId, String departmentName, String phoneNumber) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.phoneNumber = phoneNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Clerk> getClerks() {
        return clerks;
    }

    public void setClerks(ArrayList<Clerk> clerks) {
        this.clerks = clerks;
    }

    public void addNewClerk(Clerk clerk){
        boolean checker = false;
        for (Clerk instanceClerk: clerks)
            if (clerk.equals(instanceClerk)) {
                checker = true;
                break;
            }
        if (!checker)
            clerks.add(clerk);
    }

    public String printAllClerks(){
        String result = "";
        for (Clerk clerk: clerks) {
            result += clerk + "\n" ;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", phoneNumber='" + phoneNumber +
                ", clerks=" + '\n' + printAllClerks() +
                '}';
    }
}

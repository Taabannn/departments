package ir.maktab58.exercise1.models;

import java.util.Objects;

public class Clerk {
    private int clerkId;
    private String firstname;
    private String lastname;
    private int personnelIdNumber;
    private MyDate birthDate;
    private Department currentDepartment;

    public Clerk(int id, String firstname, String lastname, int personnelIdNumber,
                 MyDate birthDate, Department currentDepartment) {
        this.clerkId = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.personnelIdNumber = personnelIdNumber;
        this.birthDate = birthDate;
        this.currentDepartment = currentDepartment;
    }

    public int getClerkId() {
        return clerkId;
    }

    public void setClerkId(int clerkId) {
        this.clerkId = clerkId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPersonnelIdNumber() {
        return personnelIdNumber;
    }

    public void setPersonnelIdNumber(int personnelIdNumber) {
        this.personnelIdNumber = personnelIdNumber;
    }

    public MyDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(MyDate birthDate) {
        this.birthDate = birthDate;
    }

    public Department getCurrentDepartment() {
        return currentDepartment;
    }

    public void setCurrentDepartment(Department currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clerk clerk = (Clerk) o;
        return clerkId == clerk.clerkId && personnelIdNumber == clerk.personnelIdNumber && firstname.equals(clerk.firstname) && lastname.equals(clerk.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clerkId, firstname, lastname, personnelIdNumber, birthDate, currentDepartment);
    }

    @Override
    public String toString() {
        return "Clerk{" +
                "clerkId=" + clerkId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", personnelIdNumber=" + personnelIdNumber +
                ", birthDate=" + birthDate.toString() +
                ", currentDepartment=" + currentDepartment.getDepartmentName() +
                '}';
    }
}

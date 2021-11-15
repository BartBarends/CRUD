package nl.han.aim.oosevt.lamport.controllers.employees.dto;

import nl.han.aim.oosevt.lamport.data.entities.Employee;

public class EmployeeResponseDTO {
    private int id;
    private String firstName;
    private String lastName;

    public EmployeeResponseDTO(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EmployeeResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeResponseDTO fromData(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();

        return this;
    }
}

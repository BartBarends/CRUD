package nl.han.aim.oosevt.lamport.controllers.employees.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public class CreateEmployeeRequestRequestDTO extends RequestDTO {
    private String firstName;
    private String lastName;

    public CreateEmployeeRequestRequestDTO() {}

    public CreateEmployeeRequestRequestDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    protected void validateDTO() {
        if(firstName.isEmpty()) {
            super.addError("firstName", "Voornaam is leeg!");
        }
        if(lastName.isEmpty()) {
            super.addError("lastName", "Achternaam is leeg!");
        }
    }
}

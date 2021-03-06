package nl.han.aim.oosevt.lamport.controllers.employees.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

import java.util.ArrayList;

public class UpdateEmployeeRequestRequestDTO extends RequestDTO {
    private int id;
    private String firstName;
    private String lastName;
    private ArrayList<Integer> functions;

    public UpdateEmployeeRequestRequestDTO(int id, String firstName, String lastName, ArrayList<Integer> functions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.functions = functions;
    }

    public UpdateEmployeeRequestRequestDTO() {
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

    public ArrayList<Integer> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<Integer> functions) {
        this.functions = functions;
    }

    @Override
    protected void validateDTO() {
        if(id <= 0) {
            super.addError("id", "Id is leeg");
        }
        if(firstName.isEmpty()) {
            super.addError("firstName", "Voornaam is leeg!");
        }
        if(lastName.isEmpty()) {
            super.addError("lastName", "Achternaam is leeg!");
        }
    }
}

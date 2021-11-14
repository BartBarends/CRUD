package nl.han.aim.oosevt.lamport.services.employee.validators;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDtoException;

public class EmployeeDTOValidator {
    public void validate(EmployeeDTO employeeDTO) throws InvalidDtoException {
        InvalidDtoException invalidDtoException = new InvalidDtoException();

        if(employeeDTO.getFirstName().isEmpty()) {
            invalidDtoException.addError("firstName", "Voornaam is leeg!");
        }
        if(employeeDTO.getLastName().isEmpty()) {
            invalidDtoException.addError("lastName", "Achternaam is leeg!");
        }

        if(!invalidDtoException.getErrors().isEmpty()) {
            throw invalidDtoException;
        }
    }
}

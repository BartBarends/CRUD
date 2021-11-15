package nl.han.aim.oosevt.lamport.services.employee;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.CreateEmployeeRequestRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.employees.dto.UpdateEmployeeRequestRequestDTO;

import java.util.List;

public interface EmployeeService {
    void createEmployee(CreateEmployeeRequestRequestDTO employeeDTO);
    void updateEmployee(UpdateEmployeeRequestRequestDTO employeeDTO);
    void deleteEmployee(int id);
    EmployeeResponseDTO getEmployee(int id);
    List<EmployeeResponseDTO> getEmployees();
}

package nl.han.aim.oosevt.lamport.services.employee;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(int id);
    EmployeeDTO getEmployee(int id);
    List<EmployeeDTO> getEmployees();
}

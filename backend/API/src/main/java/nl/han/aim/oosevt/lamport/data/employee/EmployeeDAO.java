package nl.han.aim.oosevt.lamport.data.employee;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeDAO {
    List<EmployeeDTO> getEmployees();
    EmployeeDTO getEmployee(int id);
    void deleteEmployee(int id);
    void updateEmployee(EmployeeDTO employeeDTO);
    void createEmployee(EmployeeDTO employeeDTO);
}

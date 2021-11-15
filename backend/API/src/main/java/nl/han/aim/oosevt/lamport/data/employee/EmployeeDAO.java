package nl.han.aim.oosevt.lamport.data.employee;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeResponseDTO;
import nl.han.aim.oosevt.lamport.data.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getEmployees();
    Employee getEmployee(int id);
    void deleteEmployee(int id);
    void updateEmployee(int id, String firstName, String lastName);
    void createEmployee(String firstName, String lastName);
}

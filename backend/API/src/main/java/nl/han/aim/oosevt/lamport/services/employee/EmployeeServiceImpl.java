package nl.han.aim.oosevt.lamport.services.employee;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.CreateEmployeeRequestRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.employees.dto.UpdateEmployeeRequestRequestDTO;
import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAO;
import nl.han.aim.oosevt.lamport.data.entities.Employee;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void createEmployee(CreateEmployeeRequestRequestDTO employeeDTO) {
        employeeDTO.validate();
        employeeDAO.createEmployee(employeeDTO.getFirstName(), employeeDTO.getLastName());
    }

    @Override
    public void updateEmployee(UpdateEmployeeRequestRequestDTO employeeDTO) {
        employeeDTO.validate();

        if(employeeDAO.getEmployee(employeeDTO.getId()) == null) {
            throw new NotFoundException();
        }

        employeeDAO.updateEmployee(employeeDTO.getId(), employeeDTO.getFirstName(), employeeDTO.getLastName());
    }

    @Override
    public void deleteEmployee(int id) {
        if(employeeDAO.getEmployee(id) == null) {
            throw new NotFoundException();
        }
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public EmployeeResponseDTO getEmployee(int id) {
        final Employee employee = employeeDAO.getEmployee(id);

        if(employee == null) {
            throw new NotFoundException();
        }

        return new EmployeeResponseDTO().fromData(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        return employeeDAO
                .getEmployees()
                .stream()
                .map(x -> new EmployeeResponseDTO().fromData(x))
                .collect(Collectors.toList());
    }
}

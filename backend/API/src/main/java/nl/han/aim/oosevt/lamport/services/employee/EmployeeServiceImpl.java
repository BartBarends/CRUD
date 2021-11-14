package nl.han.aim.oosevt.lamport.services.employee;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeDTO;
import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAO;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.services.employee.validators.EmployeeDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final EmployeeDTOValidator employeeDTOValidator = new EmployeeDTOValidator();

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void createEmployee(EmployeeDTO employeeDTO) {
        employeeDTOValidator.validate(employeeDTO);
        employeeDAO.createEmployee(employeeDTO);
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        if(employeeDAO.getEmployee(employeeDTO.getId()) == null) {
            throw new NotFoundException();
        }
        employeeDTOValidator.validate(employeeDTO);
        employeeDAO.updateEmployee(employeeDTO);
    }

    @Override
    public void deleteEmployee(int id) {
        if(employeeDAO.getEmployee(id) == null) {
            throw new NotFoundException();
        }
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public EmployeeDTO getEmployee(int id) {
        final EmployeeDTO employeeDTO = employeeDAO.getEmployee(id);

        if(employeeDTO == null) {
            System.out.println("Throwing Exception");
            throw new NotFoundException();
        }

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        return employeeDAO.getEmployees();
    }
}

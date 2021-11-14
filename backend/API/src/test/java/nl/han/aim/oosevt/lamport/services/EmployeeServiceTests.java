package nl.han.aim.oosevt.lamport.services;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeDTO;
import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDtoException;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.services.employee.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTests {

    private EmployeeServiceImpl employeeService;

    private final EmployeeDTO updateDTO = new EmployeeDTO(1, "Bart1", "Barends1");
    private final EmployeeDTO wrongUpdateDTO = new EmployeeDTO(3, "Bart1", "Barends1");
    private final EmployeeDTO createDTO = new EmployeeDTO(0, "Test", "Test1");

    private final ArrayList<EmployeeDTO> employees = new ArrayList<>();

    @BeforeEach
    public void setup() {
        final EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
        employees.add(new EmployeeDTO(1, "Bart", "Barends"));
        employees.add(new EmployeeDTO(2, "Ard-Jan", "van Etten"));
        Mockito.when(employeeDAO.getEmployees()).thenReturn(employees);
        Mockito.when(employeeDAO.getEmployee(1)).thenReturn(employees.get(0));
        Mockito.when(employeeDAO.getEmployee(3)).thenReturn(null);
        Mockito.doAnswer(invocation -> {
            employees.get(0).setFirstName("Bart1");
            employees.get(0).setLastName("Barends1");
            return null;
        }).when(employeeDAO).updateEmployee(updateDTO);

        Mockito.doAnswer(invocation -> {
            employees.add(createDTO);
            return null;
        }).when(employeeDAO).createEmployee(createDTO);

        Mockito.doAnswer(invocation -> {
            employees.remove(0);
            return null;
        }).when(employeeDAO).deleteEmployee(1);

        employeeService = new EmployeeServiceImpl(employeeDAO);
    }

    @Test
    public void getAllReturnsAllEmployees() {
        final List<EmployeeDTO> employeeDTOS = employeeService.getEmployees();

        Assertions.assertEquals(2, employeeDTOS.size());
        Assertions.assertEquals("Bart", employeeDTOS.get(0).getFirstName());
    }

    @Test
    public void getByIdReturnsEmployeeWithId() {
        final EmployeeDTO employeeDTO = employeeService.getEmployee(1);

        Assertions.assertEquals(1, employeeDTO.getId());
        Assertions.assertEquals("Bart", employeeDTO.getFirstName());
    }

    @Test
    public void getByIdReturnsThrowsExceptionWithoutValidId() {
        Assertions.assertThrows(NotFoundException.class, () -> employeeService.getEmployee(3));
    }

    @Test
    public void updateEmployeeUpdatesAllFields() {
        employeeService.updateEmployee(updateDTO);

        final EmployeeDTO updated = employeeService.getEmployee(1);

        Assertions.assertEquals("Bart1", updated.getFirstName());
        Assertions.assertEquals("Barends1", updated.getLastName());
    }

    @Test
    public void updateEmployeeThrowsErrorWithoutValidDTO() {
        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.updateEmployee(new EmployeeDTO(1, "", "Test")));
        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.updateEmployee(new EmployeeDTO(1, "Test", "")));
    }

    @Test
    public void updateEmployeeThrowsErrorWithoutValidId() {
        Assertions.assertThrows(NotFoundException.class, () -> employeeService.updateEmployee(wrongUpdateDTO));
    }

    @Test
    public void createEmployeeAddsEmployeeWithValidId() {
        employeeService.createEmployee(createDTO);

        final List<EmployeeDTO> employeeDTOS = employeeService.getEmployees();

        Assertions.assertEquals(3, employeeDTOS.size());
        Assertions.assertEquals("Test", employeeDTOS.get(2).getFirstName());
    }

    @Test
    public void createEmployeeThrowsErrorWithoutValidDTO() {
        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.createEmployee(new EmployeeDTO(0, "", "Test")));
        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.createEmployee(new EmployeeDTO(0, "Test", "")));
    }

    @Test
    public void deleteEmployeeDeletesEmployeeWithValidId() {
        employeeService.deleteEmployee(1);

        final List<EmployeeDTO> employeeDTOS = employeeService.getEmployees();

        Assertions.assertEquals(1, employeeDTOS.size());
    }

    @Test
    public void deleteEmployeeThrowsErrorWithoutValidId() {
        Assertions.assertThrows(NotFoundException.class, () -> employeeService.deleteEmployee(3));
    }
}

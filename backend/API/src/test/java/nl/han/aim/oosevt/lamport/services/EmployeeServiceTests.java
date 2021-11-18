//package nl.han.aim.oosevt.lamport.services;
//
//import nl.han.aim.oosevt.lamport.controllers.employees.dto.CreateEmployeeRequestRequestDTO;
//import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeResponseDTO;
//import nl.han.aim.oosevt.lamport.controllers.employees.dto.UpdateEmployeeRequestRequestDTO;
//import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAO;
//import nl.han.aim.oosevt.lamport.data.entities.Employee;
//import nl.han.aim.oosevt.lamport.exceptions.InvalidDtoException;
//import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
//import nl.han.aim.oosevt.lamport.services.employee.EmployeeServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmployeeServiceTests {
//
//    private EmployeeServiceImpl employeeService;
//
//    private final String updateFirstName = "Bart1";
//    private final String updateLastName = "Barends1";
//    private final UpdateEmployeeRequestRequestDTO updateDTO = new UpdateEmployeeRequestRequestDTO(1, updateFirstName, updateLastName);
//    private final UpdateEmployeeRequestRequestDTO wrongUpdateDTO = new UpdateEmployeeRequestRequestDTO(3, "Bart1", "Barends1");
//    private final CreateEmployeeRequestRequestDTO createDTO = new CreateEmployeeRequestRequestDTO("Test", "Test1");
//
//    private final ArrayList<Employee> employees = new ArrayList<>();
//
//    @BeforeEach
//    public void setup() {
//        final EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
//
//        employees.add(new Employee(1, "Bart", "Barends"));
//        employees.add(new Employee(2, "Ard-Jan", "van Etten"));
//
//        Mockito.when(employeeDAO.getEmployees()).thenReturn(employees);
//        Mockito.doAnswer(invocation -> {
//            employees.remove(employees.stream().filter(x -> x.getId() == 1).findFirst().get());
//            employees.add(new Employee(1, updateFirstName, updateLastName));
//            return null;
//        }).when(employeeDAO).updateEmployee(1, updateFirstName, updateLastName);
//        Mockito.when(employeeDAO.getEmployee(1)).thenReturn(employees.stream().filter(x -> x.getId() == 1).findFirst().get());
//        Mockito.when(employeeDAO.getEmployee(3)).thenReturn(null);
//        Mockito.doAnswer(invocation -> {
//            employees.add(new Employee(3, "Test", "Test1"));
//            return null;
//        }).when(employeeDAO).createEmployee("Test", "Test1");
//
//        Mockito.doAnswer(invocation -> {
//            employees.remove(0);
//            return null;
//        }).when(employeeDAO).deleteEmployee(1);
//
//        employeeService = new EmployeeServiceImpl(employeeDAO);
//    }
//
//    @Test
//    public void getAllReturnsAllEmployees() {
//        final List<EmployeeResponseDTO> employeeDTOS = employeeService.getEmployees();
//
//        Assertions.assertEquals(2, employeeDTOS.size());
//        Assertions.assertEquals("Bart", employeeDTOS.get(0).getFirstName());
//    }
//
//    @Test
//    public void getByIdReturnsEmployeeWithId() {
//        final EmployeeResponseDTO employeeDTO = employeeService.getEmployee(1);
//
//        Assertions.assertEquals(1, employeeDTO.getId());
//        Assertions.assertEquals("Bart", employeeDTO.getFirstName());
//    }
//
//    @Test
//    public void getByIdReturnsThrowsExceptionWithoutValidId() {
//        Assertions.assertThrows(NotFoundException.class, () -> employeeService.getEmployee(3));
//    }
//
//    @Test
//    public void updateEmployeeUpdatesAllFields() {
//        employeeService.updateEmployee(updateDTO);
//
//        final Employee updated = employees.stream().filter(x -> x.getId() == 1).findFirst().get();
//
//        Assertions.assertEquals(updateFirstName, updated.getFirstName());
//        Assertions.assertEquals(updateLastName, updated.getLastName());
//    }
//
//    @Test
//    public void updateEmployeeThrowsErrorWithoutValidDTO() {
//        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.updateEmployee(new UpdateEmployeeRequestRequestDTO(1, "", "Test")));
//        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.updateEmployee(new UpdateEmployeeRequestRequestDTO(1, "Test", "")));
//    }
//
//    @Test
//    public void updateEmployeeThrowsErrorWithoutValidId() {
//        Assertions.assertThrows(NotFoundException.class, () -> employeeService.updateEmployee(wrongUpdateDTO));
//    }
//
//    @Test
//    public void createEmployeeAddsEmployeeWithValidId() {
//        employeeService.createEmployee(createDTO);
//
//        final List<EmployeeResponseDTO> employeeDTOS = employeeService.getEmployees();
//
//        Assertions.assertEquals(3, employeeDTOS.size());
//        Assertions.assertEquals("Test", employeeDTOS.get(2).getFirstName());
//    }
//
//    @Test
//    public void createEmployeeThrowsErrorWithoutValidDTO() {
//        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.createEmployee(new CreateEmployeeRequestRequestDTO("", "Test")));
//        Assertions.assertThrows(InvalidDtoException.class, () -> employeeService.createEmployee(new CreateEmployeeRequestRequestDTO("Test", "")));
//    }
//
//    @Test
//    public void deleteEmployeeDeletesEmployeeWithValidId() {
//        employeeService.deleteEmployee(1);
//
//        final List<EmployeeResponseDTO> employeeDTOS = employeeService.getEmployees();
//
//        Assertions.assertEquals(1, employeeDTOS.size());
//    }
//
//    @Test
//    public void deleteEmployeeThrowsErrorWithoutValidId() {
//        Assertions.assertThrows(NotFoundException.class, () -> employeeService.deleteEmployee(3));
//    }
//}

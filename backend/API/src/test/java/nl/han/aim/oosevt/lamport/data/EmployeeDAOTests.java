package nl.han.aim.oosevt.lamport.data;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAO;
import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAOImpl;
import nl.han.aim.oosevt.lamport.data.entities.Employee;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeDAOTests {
    private EmployeeDAO employeeDAO;
    private static DB db;
    @BeforeAll
    public static void setupDB() {
        new DatabaseProperties();
        try {
            db = DB.newEmbeddedDB(1001);
            db.start();
            final File directory = new File(EmployeeDAOTests.class.getResource("/sql/").getFile());
            final List<String> fileNames = Arrays.stream(Objects.requireNonNull(directory.listFiles())).map(File::getName).sorted().collect(Collectors.toList());
            fileNames.removeIf(x -> x.equals("clear_db.sql"));
            fileNames.removeIf(x -> x.equals("3_data.sql"));
            for (String fileName : fileNames) {
                db.source("sql/" + fileName);
            }
        } catch (ManagedProcessException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() {
        try {
            db.source("sql/clear_db.sql");
            db.source("sql/3_data.sql");
        } catch (ManagedProcessException e) {
            e.printStackTrace();
        }
        employeeDAO = new EmployeeDAOImpl();
    }

    @Test
    public void getAllReturnsListOfEmployees() {
        final List<Employee> employeeDTOList = employeeDAO.getEmployees();

        Assertions.assertEquals(3, employeeDTOList.size());
    }

    @Test
    public void getReturnEmployee() {
        final Employee employeeDTO = employeeDAO.getEmployee(1);

        Assertions.assertEquals("Bart", employeeDTO.getFirstName());
    }

    @Test
    public void updateEmployeeUpdatesDatabase() {
        employeeDAO.updateEmployee(1, "Test", "Test");

        final Employee employeeDTO = employeeDAO.getEmployee(1);

        Assertions.assertEquals("Test", employeeDTO.getFirstName());
    }

    @Test
    public void createEmployeeUpdatesDatabase() {
        employeeDAO.createEmployee("Test", "Test");

        final List<Employee> employeeList = employeeDAO.getEmployees();

        Assertions.assertEquals(4, employeeList.size());
    }

    @Test
    public void deleteEmployeeUpdatesDatabase() {
        employeeDAO.deleteEmployee(1);

        final Employee employeeDTO = employeeDAO.getEmployee(1);

        Assertions.assertNull(employeeDTO);
    }
}

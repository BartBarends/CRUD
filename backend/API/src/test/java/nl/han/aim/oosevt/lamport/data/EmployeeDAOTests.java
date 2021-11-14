package nl.han.aim.oosevt.lamport.data;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeDTO;
import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAO;
import nl.han.aim.oosevt.lamport.data.employee.EmployeeDAOImpl;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import nl.han.aim.oosevt.lamport.data.util.ScriptRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOTests {
    private EmployeeDAO employeeDAO;
    @BeforeEach
    public void setup() {
        new DatabaseProperties();
        try {
            Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            ScriptRunner scriptRunner = new ScriptRunner(connection, true, true);

            scriptRunner.runScript(new InputStreamReader(ClassLoader.getSystemResourceAsStream("import.sql")));
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        employeeDAO = new EmployeeDAOImpl();
    }

    @Test
    public void getAllReturnsListOfEmployees() {
        final List<EmployeeDTO> employeeDTOList = employeeDAO.getEmployees();

        Assertions.assertEquals(2, employeeDTOList.size());
    }

    @Test
    public void getReturnEmployee() {

    }

    @Test
    public void updateEmployeeUpdatesDatabase() {

    }

    @Test
    public void createEmployeeUpdatesDatabase() {

    }

    @Test
    public void deleteEmployeeUpdatesDatabase() {

    }
}

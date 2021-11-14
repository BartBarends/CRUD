package nl.han.aim.oosevt.lamport.data.employee;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeDTO;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<EmployeeDTO> items = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL getEmployees");
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()) {
                EmployeeDTO item = new EmployeeDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public EmployeeDTO getEmployee(int id) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getEmployee(?)")) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new EmployeeDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteEmployee(?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateEmployee(?,?,?)")) {
            statement.setInt(1, employeeDTO.getId());
            statement.setString(2, employeeDTO.getFirstName());
            statement.setString(3, employeeDTO.getLastName());
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployee(EmployeeDTO employeeDTO) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createEmployee(?,?)")) {
            statement.setString(1, employeeDTO.getFirstName());
            statement.setString(2, employeeDTO.getLastName());
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

package nl.han.aim.oosevt.lamport.data.employee;

import nl.han.aim.oosevt.lamport.data.entities.Employee;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> getEmployees() {
        List<Employee> items = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL getEmployees");
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()) {
                Employee item = new Employee(
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
    public Employee getEmployee(int id) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getEmployee(?)")) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Employee(
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
    public void updateEmployee(int id, String firstName, String lastName) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateEmployee(?,?,?)")) {
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployee(String firstName, String lastName) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createEmployee(?,?)")) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

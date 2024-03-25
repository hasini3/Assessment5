// Java program to store  Department(id, name) object in database, use PreparedStatement and required steps to complete the given problem statement.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDAO {
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/departments";
    private final String USERNAME = "your_username";
    private final String PASSWORD = "your_password";

    public DepartmentDAO() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDepartment(Department department) {
        String query = "INSERT INTO department (id, name) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());

            preparedStatement.executeUpdate();
            System.out.println("Department added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        Department department = new Department(1, "IT");
        departmentDAO.addDepartment(department);
    }
}
class Department {
    private int id;
    private String name;
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

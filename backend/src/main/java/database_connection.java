import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class database_connection {
    // Declare the connection object.
    private Connection connection = null;
    private Map<Integer, Map<String, Object>> dataMap;

    // This method establishes a connection to a MySQL database.
    public database_connection() {
        // Define the database connection details.
        String url = "jdbc:mysql://localhost:3306/social_app";
        String username = "root";
        String password = "1319Rip@#";

        // Add the MySQL JDBC driver to the classpath.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver.
        } catch (ClassNotFoundException e) {
            // Handle the exception if the driver is not found.
            System.out.println("Failed to load MySQL JDBC driver");
            System.out.println("download driver from https://dev.mysql.com/downloads \n  get the platform independent one");
            e.printStackTrace();
            return;
        }

        try {
            // Attempt to establish a connection to the database.
            connection = DriverManager.getConnection(url, username, password);

            // Print a success message to the console.
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            // Handle the exception if the connection fails.
            System.out.println("Failed to connect to the database");

            // Print the stack trace of the exception.
            e.printStackTrace();
        }
    }

    // This method retrieves all data from a specified table in a MySQL database.
    public void readFromDatabase(String tableName, String[] columnNames, int id, String type) {
        try {
            // Create a statement object to execute the query.
            java.sql.Statement statement = connection.createStatement();

            // Construct the SQL query based on the type parameter.
            String query;
            if (type.equals("normal")) {
                query = "SELECT " + String.join(", ", columnNames) + " FROM " + tableName;
            } else if (type.equals("specific")) {
                // ensure id is the first in the columnNames Array.
                query = "SELECT " + String.join(",", columnNames) + " FROM " + tableName + " WHERE " + columnNames[0] + " = " + id;
            } else {
                System.out.println("Invalid type parameter");
                return;
            }
            // Execute the query and store the results in a result set.
            ResultSet resultSet = statement.executeQuery(query);

            // Create a map to store the data from the table.
            dataMap = new HashMap<>();

            // Initialize row ID counter
            int rowId = 0;

            // Iterate over the result set and store each row of data in the map.
            while (resultSet.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (String columnName : columnNames) {
                    Object value = resultSet.getObject(columnName);
                    rowData.put(columnName, value);
                }
                dataMap.put(rowId, rowData);
                rowId++;
            }
            // Print a success message to the console.
            System.out.println("Successfully retrieved all data from the database");

        } catch (SQLException e) {
            // Print an error message to the console.
            System.out.println("Failed to retrieve data from the database");

            // Print the stack trace of the exception.
            e.printStackTrace();
        }finally {
            Close_connection();
        }
    }

    // Getter method to access the data map.
    public Map<Integer, Map<String, Object>> getDataMap() {
        return dataMap;
    }

    // This method writes or deletes data to/from a specified table in the database.
    public void writeToDatabase(String tableName, String type, String idColumnName, int idValue, Map<String, Object> dataMap) {
        try {
            StringBuilder queryBuilder = new StringBuilder();

            if (type.equals("add")) {
                // Construct the SQL INSERT statement.
                queryBuilder.append("INSERT INTO ").append(tableName).append(" (");
                queryBuilder.append(String.join(", ", dataMap.keySet()));
                queryBuilder.append(") VALUES (");
                for (int i = 0; i < dataMap.size(); i++) {
                    queryBuilder.append("?");
                    if (i < dataMap.size() - 1) {
                        queryBuilder.append(", ");
                    }
                }
                queryBuilder.append(")");
            } else if (type.equals("delete")) {
                // Construct the SQL DELETE statement.
                queryBuilder.append("DELETE FROM ").append(tableName).append(" WHERE ").append(idColumnName).append(" = ?");
            } else {
                // Invalid type parameter
                System.out.println("Invalid type parameter");
                return;
            }

            // Prepare the SQL statement.
            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());

            // Set values for the parameters in the prepared statement.
            int parameterIndex = 1;
            if (type.equals("add")) {
                for (Object value : dataMap.values()) {
                    statement.setObject(parameterIndex++, value);
                }
            } else {
                statement.setInt(parameterIndex, idValue);
            }

            // Execute the SQL statement.
            statement.executeUpdate();

            // Print a success message to the console.
            System.out.println("Successfully executed the database operation");
        } catch (SQLException e) {
            // Print an error message to the console.
            System.out.println("Failed to execute the database operation");

            // Print the stack trace of the exception.
            e.printStackTrace();
        }finally {
            readTableData(tableName);
        }
    }

    // This method updates data in a specified table in the database.
    public void updateDatabase(String tableName, String idColumnName, int idValue, Map<String, Object> dataMap) {
        try {
            // Construct the SQL UPDATE statement.
            StringBuilder queryBuilder = new StringBuilder("UPDATE ");
            queryBuilder.append(tableName).append(" SET ");
            for (String column : dataMap.keySet()) {
                queryBuilder.append(column).append(" = ?").append(",");
            }
            queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Remove the trailing comma
            queryBuilder.append(" WHERE ").append(idColumnName).append(" = ?");

            // Prepare the SQL statement.
            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());

            // Set values for the parameters in the prepared statement.
            int parameterIndex = 1;
            for (Object value : dataMap.values()) {
                statement.setObject(parameterIndex++, value);
            }
            statement.setInt(parameterIndex, idValue);

            // Execute the SQL statement.
            statement.executeUpdate();

            // Print a success message to the console.
            System.out.println("Successfully updated data in the database");

        } catch (SQLException e) {
            // Print an error message to the console.
            System.out.println("Failed to update data in the database");

            // Print the stack trace of the exception.
            e.printStackTrace();
        }finally {
            readTableData(tableName);
        }
    }

    // This method reads data from a specified table in the database and prints it.
    public void readTableData(String tableName) {
        try {
            // Construct the SQL SELECT statement.
            String query = "SELECT * FROM " + tableName;

            // Execute the query and store the results in a result set.
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Print the retrieved data.
            System.out.println("Contents of table " + tableName + ":");
            while (resultSet.next()) {
                // Print each row of data
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(resultSet.getMetaData().getColumnName(i) + ": " + resultSet.getString(i) + ", ");
                }
                System.out.println(); // Move to the next line for the next row
            }
        } catch (SQLException e) {
            // Print an error message to the console.
            System.out.println("Failed to read data from the database");

            // Print the stack trace of the exception.
            e.printStackTrace();
        }finally {
            Close_connection();
        }
    }
    private void Close_connection(){
        // Ensure that the connection is closed, if it exists.
        if (connection != null) {
            try {
                // Attempt to close the connection.
                connection.close();
            } catch (SQLException e) {
                // Print an error message to the console.
                System.out.println("Failed to close connection");

                // Print the stack trace of the exception.
                e.printStackTrace();
            }
        }
    }
}

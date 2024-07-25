package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class FetchEmployeeDetails {
    // Database URL, username, and password
    static final String DB_URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root"; // Replace with your MySQL username
    static final String PASS = "Saumya@12"; // Replace with your MySQL password

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Step 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Open a connection
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Step 3: Execute a query to fetch employee details
            System.out.println("Fetching employee details...");
            stmt = conn.createStatement();
            String sql = "SELECT id, first_name, last_name, email, department, salary FROM employees";
            ResultSet rs = stmt.executeQuery(sql);

            // Step 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", First Name: " + firstName);
                System.out.print(", Last Name: " + lastName);
                System.out.print(", Email: " + email);
                System.out.print(", Department: " + department);
                System.out.println(", Salary: " + salary);
            }

            // Step 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}


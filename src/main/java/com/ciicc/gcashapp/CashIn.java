package com.ciicc.gcashapp;

import java.sql.*;


public class CashIn {
    //sql credentials
    private static String url = "jdbc:mysql://localhost/gcash";

    private static String username = "root";

    private static String password = "";


    //code to access the database sql
    public static Connection con() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection.toString());
        } catch (SQLException e) {
            System.out.println(e);
        }

        return connection;
    }


//update and insert amount to balance
    public void cashIn(int ID, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid cash-in amount.");
            return;
        }

        try {
            Statement st = con().createStatement(); // Establish connection
            String getBalanceSQL = "SELECT Balance FROM userdata WHERE ID = " + ID;//column name from desire table path

            ResultSet rs = st.executeQuery(getBalanceSQL);

            if (rs.next()) {
                double currentBalance = rs.getDouble("Balance"); // Get current balance

                double newBalance = currentBalance + amount; // Add cash-in amount

                //update balance statement
                String updateBalanceSQL = "UPDATE userdata SET Balance = " + newBalance + " WHERE ID = " + ID;
                int rowsUpdated = st.executeUpdate(updateBalanceSQL);//output

                if (rowsUpdated > 0) {//condition if executeUpdate is successful
                    System.out.println("Cash-in successful! New balance: " + newBalance);
                } else {
                    System.out.println("Error updating balance.");
                }
            } else {
                System.out.println("User ID not found.");
            }

            con().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}

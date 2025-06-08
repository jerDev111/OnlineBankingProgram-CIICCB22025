package com.ciicc.gcashapp;

import java.sql.*;

public class CheckBalance {

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




    public double checkBalance(int ID) {
        double Balance = 0.0;
        try {
            Statement st = con().createStatement();
            String sql = "SELECT Balance FROM userdata WHERE ID= '"+ID+"'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Balance = rs.getDouble("Balance");

            } else {
                System.out.println("ID not found.");
            }

            con().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return Balance;
    }

}









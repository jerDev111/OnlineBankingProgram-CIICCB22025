package com.ciicc.gcashapp;

import java.sql.*;

public class Transactions {

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



//displays a transaction log of how many amounts is recieve and also the corresponding senderID
    public void viewUserAll(int userID) {
        try {
            Statement st = con().createStatement();
            String sql = "SELECT lastTransaction FROM userdata WHERE ID = " + userID;

            ResultSet rs = st.executeQuery(sql);

            System.out.println("Transaction history for User ID: " + userID);
            System.out.println("----------------------------------------------------");

            if (rs.next()) {
                String transactionLog = rs.getString("lastTransaction");
                System.out.println("Latest Transaction: " + transactionLog);
            } else {
                System.out.println("User ID not found.");
            }

            con().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



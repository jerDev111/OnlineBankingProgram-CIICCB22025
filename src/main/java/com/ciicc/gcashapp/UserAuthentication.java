package com.ciicc.gcashapp;
import java.sql.*;


public class UserAuthentication {

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




    //create
    public void Registration(String Name, String Email, int Number, int PIN) {
        try {
            Statement st = con().createStatement();
            String sql = "INSERT INTO userdata (Name, Email, Number, PIN) VALUES ('"
                    + Name + "', '" + Email + "', " + Number + ", " + PIN + ")";

            int rowsIn = st.executeUpdate(sql); //when the program runs it updates the data each time

            if (rowsIn > 0) {
                System.out.println("User registered successfully!");
            }
            con().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }





    //read
    public boolean Login(String Name, int PIN) {
    boolean isAccess = false;
        try {
            Statement st = con().createStatement();//engine
            String sql = "SELECT * FROM userdata WHERE Name ='"+Name+"' AND PIN = "+PIN;//car

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                isAccess = true;
                System.out.println("Login successful!");
            } else {

                System.out.println("Invalid name or PIN. Please try again.");
            }

            con().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return isAccess;
    }




    //update
    public void changePIN(String Name, int oldPIN, int newPIN ) {
        try {
            Statement st = con().createStatement();
            String sql = "UPDATE userdata SET PIN = '"+newPIN+"' WHERE Name = '"+Name+"'  AND PIN = "+oldPIN;

            int rowsUpdated = st.executeUpdate(sql); // execute update

            if (rowsUpdated > 0) {
                System.out.println("PIN updated successfully!");
            } else {
                System.out.println("Incorrect current PIN or username not found.");
            }

            con().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}


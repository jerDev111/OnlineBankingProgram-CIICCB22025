package com.ciicc.gcashapp;

import java.sql.*;


public class CashTransfer {
    //sql credencials
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




    public void cashTransfer(int senderID, int receiverID, double cash) {
        if (cash <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }

        try {
            Statement st = con().createStatement();

            // Get senders balance
            String getSenderBalanceSQL = "SELECT Balance FROM userdata WHERE ID = " + senderID;
            ResultSet rsSender = st.executeQuery(getSenderBalanceSQL);

            if (rsSender.next()) {//condition if true for sender
                double senderBalance = rsSender.getDouble("Balance");

                // Check if sender has enough balance
                if (senderBalance < cash) {
                    System.out.println("Insufficient funds for transfer.");
                    return;
                }

                // Get receivers balance
                String getReceiverBalanceSQL = "SELECT Balance FROM userdata WHERE ID = " + receiverID;
                ResultSet rsReceiver = st.executeQuery(getReceiverBalanceSQL);


                if (rsReceiver.next()) {//condition if true for reciever
                    double receiverBalance = rsReceiver.getDouble("Balance");

                    // Update balances
                    double newSenderBalance = senderBalance - cash;
                    double newReceiverBalance = receiverBalance + cash;

                    //sender update statement
                    String updateSenderSQL = "UPDATE userdata SET Balance = " + newSenderBalance +
                            ", lastTransaction = 'Sent " + cash + " to User ID " + receiverID + "' WHERE ID = " + senderID;

                   //reciever update statement
                    String updateReceiverSQL = "UPDATE userdata SET Balance = " + newReceiverBalance +
                            ", lastTransaction = 'Received " + cash + " from User ID " + senderID + "' WHERE ID = " + receiverID;

                    int senderUpdate = st.executeUpdate(updateSenderSQL);//output
                    int receiverUpdate = st.executeUpdate(updateReceiverSQL);//output



                    if (senderUpdate > 0 && receiverUpdate > 0) {//condition if succesfully executeUpdate
                        System.out.println("Transfer successful! New balance of sender: " + newSenderBalance);
                    } else {
                        System.out.println("Error updating balances.");
                    }
                } else {
                    System.out.println("Receiver ID not found.");
                }
            } else {
                System.out.println("Sender ID not found.");
            }

            con().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}

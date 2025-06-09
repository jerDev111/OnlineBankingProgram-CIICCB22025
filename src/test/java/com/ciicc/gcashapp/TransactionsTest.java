package com.ciicc.gcashapp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;

public class TransactionsTest {

    @Test
    public void testViewUserAllDisplaysTransaction() throws SQLException {
        int testUserID = 3; //user 3, and the lastTransaction column on the database should update depending on the string inserted
        String testTransaction = "Received 1000.0 from User ID 6"; // this should properly change the current transactLog on the lastTransaction column on the database

        // connection to database
        Connection con = Transactions.con();
        Statement st = con.createStatement();

        // valid user credentials from the database.
        st.execute("DELETE FROM userdata WHERE ID = " + testUserID );
        st.execute("INSERT INTO userdata (ID, Name, Email, Number, PIN, Balance, lastTransaction) " +
                "VALUES (" + testUserID + ", 'maui', 'maui@',76, 8888, 88, '" + testTransaction + "')");

        st.close();
        con.close();

        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        // Call method
        Transactions transactions = new Transactions();
        transactions.viewUserAll(testUserID);

        // Restore console output
        System.setOut(originalOut);


        
        // Assert that output contains expected transaction
        String output = outputStream.toString();
        assertTrue(output.contains("Transaction history for User ID: " + testUserID));
        assertTrue(output.contains("Latest Transaction: " + testTransaction));
    }
}
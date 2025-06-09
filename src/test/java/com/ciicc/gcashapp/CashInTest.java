package com.ciicc.gcashapp;

import org.junit.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class CashInTest {
    @Test
   public void testCashInUpdatesBalance() throws SQLException {

        Connection con = CashIn.con();
        Statement st = con.createStatement();
        st.execute("DELETE FROM userdata WHERE ID = 6");

        // Add valid credentials for : ID, Name, Email, Number , PIN,  Balance, lastTransaction
        //make sure it was the same as the existing data on the database.
        st.execute("INSERT INTO userdata (ID, Name, Email,Number,PIN, Balance, lastTransaction) VALUES (6, 'jade', 'jade@', 87, 9999, 625, '')");
        con.close();

        // adding an amount you want to test on the specific ID
        CashIn cashIn = new CashIn();
        cashIn.cashIn(6, 4);

        // Assert Check if balance is updated to expected value which is 629
        con = CashIn.con();
        ResultSet rs = con.createStatement().executeQuery("SELECT Balance FROM userdata WHERE ID = 6");
        assertTrue(rs.next()); // Make sure record exists
        double updatedBalance = rs.getDouble("Balance");
        con.close();

//629 is the expected amount when you add 4 to 625 which is the actual balance of the ID
        assertEquals(629, updatedBalance, 0.001);
    }
    }


package com.ciicc.gcashapp;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CashTransferTest {

    @Test
    public void testCashTransferUpdatesBalances() throws SQLException {

        //enter valid user credentials from database
        int senderID = 6; // user 6 balance will be decrease according to the amount
        int receiverID = 7; // user 7 balance should increase also according to amount that the sender has transfer
        double transferAmount = 1.0;

        // Perform transfer
        CashTransfer r1 = new CashTransfer();
        r1.cashTransfer(senderID, receiverID, transferAmount);

        // get database connection
        Connection con = CashTransfer.con();
        Statement st = con.createStatement();

        // Verify sender balance decreased by transferAmount
        ResultSet rsSender = st.executeQuery("SELECT Balance FROM userdata WHERE ID = " + senderID);
        assertTrue(rsSender.next());
        assertEquals(27.0, rsSender.getDouble("Balance"), 0.001);  // Assuming original  was 27
        rsSender.close();

        // Verify receiver balance increased by transferAmount
        ResultSet rsReceiver = st.executeQuery("SELECT Balance FROM userdata WHERE ID = " + receiverID);
        assertTrue(rsReceiver.next());
        assertEquals(602.0, rsReceiver.getDouble("Balance"), 0.001);  // Assuming original was 602
        rsReceiver.close();

        st.close();
        con.close();
    }
}

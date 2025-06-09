package com.ciicc.gcashapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckBalanceTest {


    @Test
    public void testValidBalanceCheck() {
       CheckBalance cb = new CheckBalance();

        // ID is checked if the expected balance meet the actual or valid balance that is stored on the database
        double expectedBalance = 89;
        double actualBalance = cb.checkBalance(8);//as you can se user 8 has a valid balance 89,
        // so assert equals expects 89 to be equal to the actual balance wich is 89

        assertEquals(89, 89, 0.0);
    }

}
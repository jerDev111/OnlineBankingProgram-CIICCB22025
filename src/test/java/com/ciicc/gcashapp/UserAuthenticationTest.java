package com.ciicc.gcashapp;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserAuthenticationTest {



    //test for valid credentials
    @Test
    public void testValidLogin() {
        UserAuthentication auth = new UserAuthentication();
        assertTrue(auth.Login("jade", 1914)); //valid credentials from database
    }


    //test for invalid credentials
    @Test
    public void testInvalidLogin() {
        UserAuthentication auth = new UserAuthentication();
        assertFalse(auth.Login("j", 9));
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Reservation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emmablomsterberg
 */
public class ReservationFacadeTest {
    
    public ReservationFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addReservation() {
        ReservationFacade rf = new ReservationFacade();
        Reservation reserve = new Reservation(1, "2040");
        boolean assertResult = true;
        boolean result = rf.addReservation(reserve);
        
        assertEquals(assertResult, result);
    }
    
}

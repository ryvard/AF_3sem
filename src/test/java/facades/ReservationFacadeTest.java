/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Reservation;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    EntityManagerFactory emf;
    ReservationFacade instance;
    public ReservationFacadeTest() {
        instance = new ReservationFacade();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("PU_AFReserve");
        instance.setEmf(emf);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addReservation() {
       
//        Reservation reserve = new Reservation(1, "2040");
//        boolean assertResult = true;
//        boolean result = instance.addReservation(reserve);
//        
//        assertEquals(assertResult, result);
    }
    
}

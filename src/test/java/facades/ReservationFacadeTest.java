/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Airline;
import entities.Airport;
import entities.Flight;
import entities.FlightInstance;
import entities.Passenger;
import entities.Reservation;
import java.util.ArrayList;
import java.util.List;
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
    
    ArrayList<Reservation> reservations;
    FlightInstance flightInstance;
    List<Passenger> passengers;
    Airline airline;
    ArrayList<Flight> flights;
    Airport airport;
    

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
        emf = Persistence.createEntityManagerFactory("PU_Derby");
        instance.setEmf(emf);
        
        reservations = new ArrayList<Reservation>();
        reservations.add(new Reservation("test"));
        
        flightInstance = new FlightInstance("test", "test", "test", "test", "test");

        passengers = new ArrayList();
        passengers.add(new Passenger("test", "test"));

        airline = new Airline("test");

        flights = new ArrayList();
        flights.add(new Flight("test", 2, "test"));
        
        airport = new Airport("test","test","test","test","test");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addReservation() {
        
        boolean result = instance.addReservation(reservations, flightInstance, passengers, airline, flights, airport);
        
        assertTrue(result);
    }
    
    @Test
    public void addReservationExceptionCheck() {
        
        airport = null;

        boolean result = instance.addReservation(reservations, flightInstance, passengers, airline, flights, airport);
        
        assertFalse(result);
    }

}

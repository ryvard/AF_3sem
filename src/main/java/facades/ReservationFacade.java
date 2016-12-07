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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emmablomsterberg
 */
public class ReservationFacade {
    
    EntityManagerFactory emf;

    public ReservationFacade()
    {
        emf = Persistence.createEntityManagerFactory("PU_AFReserve");
    }
    
     public ReservationFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public boolean addReservation(
            List<Reservation> reserve, 
            FlightInstance flightInstance, 
            List<Passenger> passengers, 
            Airline airline,
            Flight flight,
            Airport airport) {
        
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            flight.setAirport(airport);
            flight.setAirline(airline);
            flightInstance.setFlight(flight);
            flightInstance.setReserve(reserve);
            
            
            
//            flight.setAirline(airline);
//            flight.setAirport(airport);
//       
//            reserve.setFlightInstance(flightInstance);
//            reserve.setPassengers(passengers);
            
            em.persist(flightInstance);
            em.getTransaction().commit();
            return true;
            
        } finally {
            em.close();
        }
        
    }

}

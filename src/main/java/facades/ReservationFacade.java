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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emmablomsterberg
 */
public class ReservationFacade {

    EntityManagerFactory emf;

    public ReservationFacade() {
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
            ArrayList<Reservation> reservations,
            FlightInstance flightInstance,
            List<Passenger> passengers,
            Airline airline,
            List<Flight> flights,
            Airport airport) {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

//            FlightInstance fI = new FlightInstance("test", "test", "test", "test", "test");
            //em.persist(flightInstance);
//            List<Reservation> reserve2 = new ArrayList();
//            Reservation ikkeDetSamme = new Reservation("blavla");
//            reserve2.add(ikkeDetSamme);
//            fI.setReserve(reserve2);
//            ikkeDetSamme.setFlightInstance(fI);
//            reservation.setFlightInstance(flightInstance);
//            em.persist(reservation);
            for (Reservation r : reservations) {
                for (Passenger p : passengers) {
                    p.setReservation(r);
                    
                }
                r.setPassengers(passengers);
                r.setFlightInstance(flightInstance);
            }
            
            flightInstance.setReserve(reservations);
            
            
            for(Flight f : flights)
            {
                f.setAirline(airline);
                f.setArivalAirport(airport);
                flightInstance.setFlight(f);
            }
            airline.setFlights(flights);
            airport.setFlights(flights);
            
            
            em.persist(flightInstance);

            em.getTransaction().commit();
            return true;

        } finally {
            em.close();
        }
    }
}

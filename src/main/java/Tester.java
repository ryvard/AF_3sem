
import entities.Airline;
import entities.Airport;
import entities.Flight;
import entities.FlightInstance;
import entities.Passenger;
import entities.Reservation;
import facades.ReservationFacade;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmablomsterberg
 */
public class Tester {
    
    public static void main(String[] args) {
         ReservationFacade rf = new ReservationFacade();
        List<Reservation> reserve = new ArrayList();
        reserve.add(new Reservation("204"));
        FlightInstance fI = new FlightInstance("test", "test", "test", "test", "test");
        List<Passenger> passengers = new ArrayList();
        passengers.add(new Passenger("mia", "ryvard"));
        Airline airline = new Airline("nameair");
        Flight flight = new Flight("flightnum", 2, "flight");
        Airport airport = new Airport("aiport1", "airport2", "airport3", "airport4", "airport5");
        
        rf.addReservation(reserve, fI, passengers, airline, flight, airport);
    }
}

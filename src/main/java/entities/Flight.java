package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author miaryvard
 */

//@Inheritance(strategy = InheritanceType.JOINED)
// joined skal under entity 
@Entity
public class Flight
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String flightNumber;
    private int seats;
    private String flightTime;
    
    
    @OneToMany(mappedBy = "flight")
    private List<FlightInstance> flightInstances;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Airline airline;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Airport arivalAirport;
    
    
    
    public Flight()
    {
    }
    
    public Flight(String flightNumber, int seats, String flightTime)
    {
        this.flightNumber = flightNumber;
        this.seats = seats;
        this.flightTime = flightTime;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFlightNumber()
    {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public int getSeats()
    {
        return seats;
    }

    public void setSeats(int seats)
    {
        this.seats = seats;
    }

    public String getFlightTime()
    {
        return flightTime;
    }

    public void setFlightTime(String flightTime)
    {
        this.flightTime = flightTime;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public List<FlightInstance> getFlightInstances()
    {
        return flightInstances;
    }

    public void setFlightInstances(List<FlightInstance> flightInstances)
    {
        this.flightInstances = flightInstances;
    }

    public Airport getArivalAirport() {
        return arivalAirport;
    }

    public void setArivalAirport(Airport arivalAirport) {
        this.arivalAirport = arivalAirport;
    }

    
    
    
}

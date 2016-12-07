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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Flight
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String flightNumber;
    private int seats;
    private String flightTime;
    
   
   // @ManyToOne
    @ManyToOne
    private Airline airline;
    
    
   // @ManyToOne
    @ManyToOne
    private Airport airport;
    
   
   // @OneToMany
    @OneToMany(mappedBy = "flight", cascade = CascadeType.PERSIST)
    private List<FlightInstance> flInstance = new ArrayList();
    
    public Flight()
    {
    }
    
    public Flight(String flightNumber, int seats, String flightTime)
    {
       // this.id = id;
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public List<FlightInstance> getFlInstance() {
        return flInstance;
    }

    public void setFlInstance(List<FlightInstance> flInstance) {
        this.flInstance = flInstance;
    }
    
    
}

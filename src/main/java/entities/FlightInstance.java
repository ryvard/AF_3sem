package entities;

import java.util.ArrayList;
import java.util.List;
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

public class FlightInstance extends Flight
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String flightId;
    private String date;
    private String time;
    private String availebleSeats;
    private String price;
    
   
    @ManyToOne
    private Flight flight;
    
    

    //@OneToMany
   
    @OneToMany(mappedBy = "flightInstance")
    private List<Reservation> reserve = new ArrayList();

    public FlightInstance()
    {
    }

    public FlightInstance(long id, String flightId, String date, String time, String availebleSeats, String price)
    {
        this.id = id;
        this.flightId = flightId;
        this.date = date;
        this.time = time;
        this.availebleSeats = availebleSeats;
        this.price = price;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFlightId()
    {
        return flightId;
    }

    public void setFlightId(String flightId)
    {
        this.flightId = flightId;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getAvailebleSeats()
    {
        return availebleSeats;
    }

    public void setAvailebleSeats(String availebleSeats)
    {
        this.availebleSeats = availebleSeats;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
    
    
    
    
}

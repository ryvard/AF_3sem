package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author miaryvard
 */
@Entity
public class FlightInstance
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String flightId;
    private String date;
    private String time;
    private String availebleSeats;
    private String price;

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

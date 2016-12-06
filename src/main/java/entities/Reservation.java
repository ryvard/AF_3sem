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

public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String totalPrice;
    
    
  //  @ManyToOne
    @ManyToOne
    private FlightInstance flightInstance;
    
    
   
    //@OneToMany
    @OneToMany(mappedBy = "reserves")
    private List<Passenger> passengers = new ArrayList();
    
    public Reservation()
    {
    }

    public Reservation(long id, String totalPrice)
    {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice)
    {
        this.totalPrice = totalPrice;
    }
    
    
    
}
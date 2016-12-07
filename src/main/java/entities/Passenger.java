package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 *
 * @author miaryvard
 */
@Entity

public class Passenger
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    
   
    //@ManyToOne
    @ManyToOne
    private Reservation reserves;
    
    public Passenger()
    {
    }
    
    public Passenger(String firstName, String lastName)
    {
        //this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Reservation getReserves() {
        return reserves;
    }

    public void setReserves(Reservation reserves) {
        this.reserves = reserves;
    }

    
    
    
    
    
}

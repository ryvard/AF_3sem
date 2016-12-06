package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;


/**
 *
 * @author miaryvard
 */
@Entity

public class Airline extends Flight
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    
    
    
   // @OneToMany
    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;
    

    public Airline()
    {
        this.flights = new ArrayList();
    }

    public Airline(long id, String name)
    {
        this.flights = new ArrayList();
        this.id = id;
        this.name = name;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    
    
}

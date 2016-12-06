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

public class Airport extends Flight
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String IATACode;
    private String timezone;
    private String name;
    private String country;
    private String city;
    
    
  //  @OneToMany
    @OneToMany(mappedBy = "airport")
    private List<Flight> fligths = new ArrayList();
    
    public Airport()
    {
    }
    
    public Airport(long id, String IATACode, String timezone, String name, String country, String city)
    {
        this.id = id;
        this.IATACode = IATACode;
        this.timezone = timezone;
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getIATACode()
    {
        return IATACode;
    }

    public void setIATACode(String IATACode)
    {
        this.IATACode = IATACode;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
    
    
    

}

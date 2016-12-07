import entities.Airline;
import entities.Airport;
import entities.Flight;
import entities.FlightInstance;
import entities.Passenger;
import entities.Reservation;
import facades.ReservationFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author miaryvard
 */
public class Structure
{
    public static void main(String[] args)
    {
        HashMap<String,Object> puproperties = new HashMap<>();
        
        puproperties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
//        puproperties.put("java.persistence.sql-load-script-source","scripts/addData.sql");

       
        
        Persistence.generateSchema("PU_AFReserve", puproperties); 
                
        Persistence.generateSchema("PU_AFReserve", null);    
    }
}
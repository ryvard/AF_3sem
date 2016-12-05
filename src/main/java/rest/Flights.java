package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

@Path("flights")
public class Flights
{

    private static final String STARTURL = "http://airline-plaul.rhcloud.com/api/flightinfo/";

    @Context
    private UriInfo context;
    private Calendar c = Calendar.getInstance();

    /**
     * Creates a new instance of GenericResource
     */
    public Flights()
    {
    }

    /**
     * Retrieves representation of an instance of rest.Flights
     *
     * @param from
     * @param date
     * @param tickets
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{from}/{date}/{tickets}")
    public String flightsParam(@PathParam("from") String from, @PathParam("date") String date, @PathParam("tickets") String tickets)
    {
      

        try
        {
            setDate(date);
            
            StringBuilder result = new StringBuilder();

            URL requestURL = new URL(STARTURL + from + "/" + formatDate(c.getTime()) + "/" + tickets);
            InputStream in = requestURL.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null)
            {
                result.append(line);
            }
            JSONObject o = new JSONObject(result.toString());

            return o.toString(2);

        } catch (IOException ex)
        {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{from}/{to}/{date}/{tickets}")
    public String flightsParam(@PathParam("from") String from, @PathParam("to") String to, @PathParam("date") String date, @PathParam("tickets") String tickets)
    {

        try
        {   
            setDate(date);

            StringBuilder result = new StringBuilder();

            URL requestURL = new URL(STARTURL + from + "/" + to + "/" + formatDate(c.getTime()) + "/" + tickets);
            InputStream in = requestURL.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null)
            {
                result.append(line);
            }
            JSONObject o = new JSONObject(result.toString());

            return o.toString(2);

        } catch (IOException ex)
        {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String formatDate(Date date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return df.format(date);
    }

    private void setDate(String date)
    {
        String[] array = date.split("-");
        
        int year = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]);
        int day = Integer.parseInt(array[2]);

        c.set(year, month - 1, day);
    }

}

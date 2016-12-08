package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Airline;
import entities.Airport;
import entities.Flight;
import entities.FlightInstance;
import entities.Passenger;
import entities.Reservation;
import facades.ReservationFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import restException.httpException;

@Path("flights")
public class Flights {

    private static final String STARTURL = "http://airline-plaul.rhcloud.com/api/flightinfo/";

    @Context
    private UriInfo context;
    private Calendar c = Calendar.getInstance();
    private ReservationFacade rf = new ReservationFacade();

    /**
     * Creates a new instance of GenericResource
     */
    public Flights() {
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
    public String flightsParam(@PathParam("from") String from, @PathParam("date") String date, @PathParam("tickets") String tickets) {

        try {
            setDate(date);

            StringBuilder result = new StringBuilder();

            URL requestURL = new URL(STARTURL + from + "/" + formatDate(c.getTime()) + "/" + tickets);
            InputStream in = requestURL.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            JSONObject o = new JSONObject(result.toString());

            return o.toString(2);

        } catch (IOException ex) {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{from}/{to}/{date}/{tickets}")
    public String flightsParam(@PathParam("from") String from, @PathParam("to") String to, @PathParam("date") String date, @PathParam("tickets") String tickets) {

        try {
            setDate(date);

            StringBuilder result = new StringBuilder();

            URL requestURL = new URL(STARTURL + from + "/" + to + "/" + formatDate(c.getTime()) + "/" + tickets);
            InputStream in = requestURL.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            JSONObject o = new JSONObject(result.toString());

            return o.toString(2);

        } catch (IOException ex) {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("reservation")
    public void addReservation(String jsonString) {
        try {
            
            System.out.println("START ADD RESERVATION");
            JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
            System.out.println("TEEEEST*** 1 ");
            
            String airline = json.get("airline").getAsString();
            System.out.println("TEEEEST*** 2 " + airline);
            String flightID = json.get("flightID").getAsString();
            System.out.println("TEEEEST*** 3 " + flightID);
            String flightNumber = json.get("flightNumber").getAsString();
            String origin = json.get("origin").getAsString();
            System.out.println("TEEEEST*** 4 " + flightNumber + " + " + origin);
            
            String date = json.get("date").getAsString();
            System.out.println("TEEEEST*** 5.1 "+date );
            
            String traveltime = json.get("travelTime").getAsString();
            System.out.println("TEEEEST*** 5.2 "+traveltime ); 
            
            String flightTime = json.get("flightTime").getAsString();
            System.out.println("TEEEEST*** 5.3 "+flightTime);
            
            int seats = json.get("seats").getAsInt();
            
           
            System.out.println("TEEEEST*** 6 : INT"+seats);
            String totalPrice = json.get("totalPrice").getAsString();
            
            String firstName = json.get("firstName").getAsString();
            
            String lastName = json.get("lastName").getAsString();
            
            String availebleSeats = "100";
            String timeZone = "CET", country = "country", city = "city";

            
            System.out.println("@@@@@@@@@@@@@@@ -- firstName" + firstName);
            
            
            ArrayList<Reservation> reservation = new ArrayList<Reservation>();
            reservation.add(new Reservation(totalPrice));

            FlightInstance flightInstance = new FlightInstance(flightID, date, date, availebleSeats, date);

            List<Passenger> passengers = new ArrayList();
            passengers.add(new Passenger(firstName, lastName));

            Airline airliner = new Airline(airline);

            ArrayList<Flight> flights = new ArrayList();
            flights.add(new Flight(flightNumber, seats, flightTime));

            Airport airport = new Airport(origin, timeZone, origin, country, city);
            
            System.out.println("EFTER SHITTET");

            rf.addReservation(reservation, flightInstance, passengers, airliner, flights, airport);
            System.out.println("HEJ IGEN");
            
        } catch (Exception ex) {
            System.out.println("FAAAIL :  "+ ex);
            throw ex;
        }
    }

    private String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return df.format(date);
    }

    private void setDate(String date) {
        String[] array = date.split("-");

        int year = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]);
        int day = Integer.parseInt(array[2]);

        c.set(year, month - 1, day);
    }

}

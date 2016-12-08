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
import org.json.JSONObject;

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
    public boolean addReservation(String jsonString) {
        try {
            JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();

            
            String airline = json.get("airline").getAsString();
            
            String flightID = json.get("flightID").getAsString();
            String flightNumber = json.get("flightNumber").getAsString();
            String origin = json.get("origin").getAsString();
            String date = json.get("date").getAsString();
            String traveltime = json.get("travelTime").getAsString();
            String flightTime = json.get("flightTime").getAsString();
            int seats = json.get("seats").getAsInt();
            String totalPrice = json.get("totalPrice").getAsString();

            String firstName = json.get("firstName").getAsString();
            String lastName = json.get("lastName").getAsString();
            
            String availebleSeats = "100";
            String timeZone = "CET", country = "country", city = "city";

            

            ArrayList<Reservation> reservation = new ArrayList<Reservation>();
            reservation.add(new Reservation(totalPrice));

            FlightInstance flightInstance = new FlightInstance(flightID, date, date, availebleSeats, date);

            List<Passenger> passengers = new ArrayList();
            passengers.add(new Passenger(firstName, lastName));

            Airline airliner = new Airline(airline);

            ArrayList<Flight> flights = new ArrayList();
            flights.add(new Flight(flightNumber, seats, flightTime));

            Airport airport = new Airport(origin, timeZone, origin, country, city);

            rf.addReservation(reservation, flightInstance, passengers, airliner, flights, airport);

            return true;
        } catch (Exception ex) {
            return false;
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

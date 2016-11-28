/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.http.HTTPException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Bruger
 */
@Path("flights")
public class Flights {
    
    private static final String STARTURL = "http://airline-plaul.rhcloud.com/api";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public Flights() {
    }

    /**
     * Retrieves representation of an instance of rest.Flights
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getflights")
    public String getFlights() {
        
            StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(STARTURL + "/flightinfo/CPH/2017-01-20T00:00:00.000Z/1");
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
            
        } catch (IOException ex) {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Gson().toJson(result);
        
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{from}/{date}/{ticket}")
    public String getFlightFromDate(@PathParam("from") String from, @PathParam("date") String date, @PathParam("ticket") String ticket){
        
        try {
            String[] array = date.split("-");
            Calendar c = Calendar.getInstance();
            
            int year = Integer.parseInt(array[0]);
            int month = Integer.parseInt(array[1]);
            int day = Integer.parseInt(array[2]);
            
            c.set(year, month - 1, day);
            
            String requestURL = STARTURL + "/flightinfo/" + from + "/" + formatDate(c.getTime()) + "/" + ticket;
            
            System.out.println("From " + from); // skal ikke converteres
            System.out.println("Array index 0 " + year);
            System.out.println("Array index 1 " + month);
            System.out.println("Array index 2 " + day);
            System.out.println("Date Object " + c.getTime());
            
            String response;
            
            response = sendGet(requestURL);
            JSONObject o = new JSONObject(response);

            return o.toString(2);
        } catch (IOException ex) {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HTTPException ex) {
            Logger.getLogger(Flights.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of Flights
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    private String formatDate(Date date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return df.format(date);
    }
    
    private String sendGet(String requestURL) throws MalformedURLException, IOException, HTTPException
    {
        String response;
        URL url = new URL(requestURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200)
        {
            response = getResponseBody(conn.getInputStream());
        } else
        {
            response = getResponseBody(conn.getErrorStream());
            //throw new HTTPException(conn.getResponseCode()+" "+conn.getResponseMessage());
        }
        return response;
    }
    
    private String getResponseBody(InputStream is) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

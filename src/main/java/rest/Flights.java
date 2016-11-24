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
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Bruger
 */
@Path("flights")
public class Flights {

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
    public String getFlights() {
        
            StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20T00:00:00.000Z/1");
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

    /**
     * PUT method for updating or creating an instance of Flights
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}

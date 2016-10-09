package com.goeuro.service;

import com.goeuro.domain.Location;
import com.goeuro.service.exception.LocationNotFoundException;
import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * Created by brito on 10/5/16.
 */
@Service
public class GoEuroApiClient {
    private static final Logger logger = Logger.getLogger(GoEuroApiClient.class);

    public Location[] getByLocation(String locationName) throws LocationNotFoundException {
        logger.debug(String.format("Fetching location. locationName=%s", locationName));

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://api.goeuro.com/api/v2/position/suggest/en/" + locationName);

        Response response = null;
        try {
            response = target.request().get();

            if (response.getStatus() != 200) {
                throw new LocationNotFoundException(String.format("Error when fetching location based on " +
                        "locationName=%s.", locationName), response.getStatus());
            }

            Location[] locations = response.readEntity(Location[].class);
            return locations;
        } catch (LocationNotFoundException ex) {
            logger.error(String.format("%s HttpStatus=%s", ex.getMessage(), ex.getHttpStatus()) );
            throw ex;
        } finally {
            try {
                response.close();
            } catch (Exception ex) {
                logger.error(String.format("Error when trying to close Response."), ex);
                throw ex;
            }
        }

    }
}

package com.goeuro;

import com.goeuro.domain.Location;
import com.goeuro.service.CSVGenerator;
import com.goeuro.service.GoEuroApiClient;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by brito on 10/5/16.
 */
@Configuration
@ComponentScan
public class Application {
    private static final Logger logger = Logger.getLogger(Application.class);

    private static final String FILE_NAME = "locations.csv";

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        GoEuroApiClient clientApi = context.getBean(GoEuroApiClient.class);
        CSVGenerator csvGenerator =  context.getBean(CSVGenerator.class);

        String locationName = null;
        try {
            locationName = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(String.format("City Name cannot be null"));
            throw new Exception("City Name cannot be null");
        }

        Location[] locations = clientApi.getByLocation(locationName);
        String csv = csvGenerator.parse(locations);
        csvGenerator.generate(csv, FILE_NAME);
    }
}

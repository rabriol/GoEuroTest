package com.goeuro.service;

import com.goeuro.domain.Location;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by brito on 10/6/16.
 */
@Service
public class CSVGenerator {
    private static final Logger logger = Logger.getLogger(CSVGenerator.class);

    //CSV file header
    private static final String FILE_HEADER = "_id,name,type,latitude,longitude\n";

    //CSV new line separator
    public static final String NEW_LINE = "\n";

    public void generate(String csv, String fileDestination) throws Exception {
        logger.debug("Generating CSV file...");

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileDestination);
            fileWriter.append(csv);

            logger.debug("CSV File generated successfully...");
        } catch (Exception e) {
            logger.error(String.format("Error when writing the csv file %s. Cause=%s", fileDestination, e.getMessage()), e);
            throw new Exception(e);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                logger.error(String.format("Erro when closing fileWriter. Cause=", e.getMessage()), e);
                throw e;
            }
        }
    }

    private String composeWithHeader(String header, String content) {
        return new StringBuilder()
                .append(header)
                .append(content)
                .toString();
    }

    public String parse(Location[] locations) {
        String csv = Arrays.stream(locations)
                .map(l -> new StringBuilder()
                        .append(l.getId())
                        .append(",")
                        .append(l.getName())
                        .append(",")
                        .append(l.getType())
                        .append(",")
                        .append(l.getGeoPosition().getLatitude())
                        .append(",")
                        .append(l.getGeoPosition().getLongitude())
                        .toString())
                .collect(Collectors.joining(NEW_LINE));

        return composeWithHeader(FILE_HEADER, csv);
    }
}

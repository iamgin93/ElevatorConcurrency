package com.fdmgroup.elevator.service;

import com.fdmgroup.elevator.controller.ElevatorController;
import com.fdmgroup.elevator.data.Elevator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
    private static final Logger logger = LogManager.getLogger(UserValidation.class);

    public boolean validateUserInput(String input){
        String floorDelimiter = null;
        String journeyDelimiter = null;
        File configFile = new File("src/main/resources/config.properties");
        try (FileReader reader = new FileReader(configFile)) {
            Properties props = new Properties();
            props.load(reader);
            int maxNumberOfFloors = Integer.parseInt(props.getProperty("ElEVATOR_FLOORS"));
            System.out.println("Maximum Number of floors: "+maxNumberOfFloors);
            floorDelimiter = props.getProperty("FLOOR_DELIMITER");
            journeyDelimiter = props.getProperty("JOURNEY_DELIMITER");
            System.out.println("Floor Delimiter: "+floorDelimiter);
            System.out.println("Journey Delimiter: "+journeyDelimiter);
        } catch (IOException e) {
            logger.error(e);
        }
        String regex = "((\\d"+floorDelimiter+"\\d+)("+journeyDelimiter+"|$))*";
        input = input.trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.matches();
        if(!matches){
            System.out.println("Please provide your input in this format: \n" +
                    "Pick-up Floor"+floorDelimiter+"Drop-Off Floor\n"
                    +"Example: 1"+floorDelimiter+"2"+journeyDelimiter+"3"+floorDelimiter+"4");
        }

        return matches;
    }
}

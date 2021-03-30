package com.fdmgroup.elevator.service;

import com.fdmgroup.elevator.controller.ElevatorController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Provides the functionality of reading input from console
 * @author Gin
 * @version 1.0
 */
public class UserInputService {
    private static final Logger logger = LogManager.getLogger(UserInputService.class);
    ElevatorController elevatorController;
    UserValidation userValidation = new UserValidation();
    /**
     * <p>Constructor
     * </p>
     * @param elevatorController ElevatorController class
     */
    public UserInputService(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    /**
     * <p>This method reads the user input from the console and calls other methods to validate the input
     * </p>
     */
    public void readUserInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         try {
             String input = reader.readLine();
             while (!userValidation.validateUserInput(input)){
                 input = reader.readLine();
             }
             elevatorController.configureNumberOfElevators(input);

        } catch (
        IOException e) {
            logger.error(e);
        }

    }

}

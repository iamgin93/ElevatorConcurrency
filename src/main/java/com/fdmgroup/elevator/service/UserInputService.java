package com.fdmgroup.elevator.service;

import com.fdmgroup.elevator.controller.ElevatorController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Gin
 * @version 1.0
 */

public class UserInputService {
    private static final Logger logger = LogManager.getLogger(UserInputService.class);
    ElevatorController elevatorController;
    UserValidation userValidation = new UserValidation();

    public UserInputService(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

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

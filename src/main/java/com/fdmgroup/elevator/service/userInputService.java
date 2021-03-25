package com.fdmgroup.elevator.service;

import com.fdmgroup.elevator.controller.ElevatorController;
import com.fdmgroup.elevator.data.Elevator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class userInputService {
    private static final Logger logger = LogManager.getLogger(Elevator.class);
    ElevatorController elevatorController = new ElevatorController();

    public void readUserInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         try {
            String input = reader.readLine();
            elevatorController.configureNumberOfElevators(input);

        } catch (
        IOException e) {
            logger.error(e);
        }

    }

}

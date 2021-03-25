package com.fdmgroup.elevator.main;

import com.fdmgroup.elevator.controller.ElevatorController;
import com.fdmgroup.elevator.data.Elevator;
import com.fdmgroup.elevator.service.ElevatorService;
import com.fdmgroup.elevator.service.userInputService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
/**
 * @author Gin
 * @version 1.0
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static ElevatorController elevatorController = new ElevatorController();
    private static userInputService userInputService = new userInputService();
    public static void main(String[] args) {
        elevatorController.initialiseElevators();
        while(true){
            userInputService.readUserInput();
        }


    }
}

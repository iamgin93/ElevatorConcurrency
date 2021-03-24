package com.fdmgroup.elevator.main;

import com.fdmgroup.elevator.controller.ElevatorController;
import com.fdmgroup.elevator.data.Elevator;
import com.fdmgroup.elevator.service.ElevatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
/**
 * @author Gin
 * @version 1.0
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        while(true){
//            System.out.println("Number of active threads: "+Thread.activeCount());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = reader.readLine();
                String src;
                String dest;

                input = input.trim();
                if(input.contains(",")){
                    String[] srcDest = input.split(",");
                    System.out.println("Number of lifts: "+srcDest.length);
                    int numberOfElevators = srcDest.length;
                    for (String pair: srcDest) {
                        src = pair.split(":")[0];
                        dest = pair.split(":")[1];
                        ElevatorController elevatorController = new ElevatorController(new ElevatorService(new Elevator(), src, dest));
                        elevatorController.operateElevator();
                    }

                }else {

                    src = input.split(":")[0];
                    dest = input.split(":")[1];
                    System.out.println("Going from level: "+src+ " to level "+dest);
                    ElevatorController elevatorController = new ElevatorController(new ElevatorService(new Elevator(), src, dest));
                    elevatorController.operateElevator();

                }
            } catch (IOException e) {
                logger.error(e);
            }
        }


    }
}

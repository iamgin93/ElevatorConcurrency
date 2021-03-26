package com.fdmgroup.elevator.controller;

import com.fdmgroup.elevator.data.Elevator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author Gin
 * @version 1.0
 */
public class ElevatorController{
    private static final Logger logger = LogManager.getLogger(Elevator.class);
    private static int maxNumberOfElevators;
    List<Thread> listOfElevatorThreads = new ArrayList<>();
    List<Elevator> listOfElevators = new ArrayList<>();
    public static String pickUpLevel;
    public static String dropOffLevel;

    public void checkElevatorsStatus(){
        Iterator<Thread> elevatorItr = listOfElevatorThreads.iterator();
        while(elevatorItr.hasNext()){
            Thread elevatorThread = elevatorItr.next();
            System.out.println("State of "+elevatorThread.getName()+": "+elevatorThread.getState());
        }
    }
    public void initialiseElevators(){
        File configFile = new File("src/main/resources/config.properties");
        try (FileReader reader = new FileReader(configFile)) {
            Properties props = new Properties();
            props.load(reader);
            maxNumberOfElevators = Integer.parseInt(props.getProperty("ELEVATOR_NUMBER"));
            System.out.println("Maximum Number of elevators: "+maxNumberOfElevators);

            for (int i = 0; i < maxNumberOfElevators ; i++) {
                listOfElevators.add(new Elevator());
                listOfElevatorThreads.add(new Thread(listOfElevators.get(i)));
            }
            int j = 1;
            Iterator<Thread> elevatorItr = listOfElevatorThreads.iterator();
            while(elevatorItr.hasNext()){
                Thread elevatorThread = elevatorItr.next();
                elevatorThread.setName("Lift"+j);
                elevatorThread.start();
                System.out.println("State of "+elevatorThread.getName()+": "+elevatorThread.getState());
//                System.out.println("Current Level of Elevator "+j+": "+listOfElevators.get(j-1).getLevel());
                j++;
            }
//            System.out.println("Active Thread Count: "+Thread.activeCount()+"\n");
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void configureNumberOfElevators(String input){
        String src;
        String dest;
        input = input.trim();
        if(input.contains(",")){
            String[] srcDest = input.split(",");
            System.out.println("Number of lifts: "+srcDest.length);
            if(srcDest.length > maxNumberOfElevators){
                System.out.println("The maximum allowable number of lifts is: " + maxNumberOfElevators+"\nPlease try again");
            }else{

                checkElevatorsStatus();
                int k = 0;
                for (String pair: srcDest) {
                    src = pair.split(":")[0];
                    dest = pair.split(":")[1];
                    pickUpLevel = src;
                    dropOffLevel = dest;
                    listOfElevators.get(k).setPickUpLevel(src);
                    listOfElevators.get(k).setDropOffLevel(dest);
                    synchronized (listOfElevators.get(k)){
                        listOfElevators.get(k).notify();
                        listOfElevators.get(k).setMove(true);
                    }
                    k++;
                }

            }

        }else {
            if(!input.contains(":")){
                System.out.println("Please provide your input in this format: \nPick-up Floor:Drop-Off Floor");
            }else{
                src = input.split(":")[0];
                dest = input.split(":")[1];
                System.out.println("Going from level: "+src+ " to level "+dest);

            }

        }
    }
    public void updateElevatorView(){

    }

}

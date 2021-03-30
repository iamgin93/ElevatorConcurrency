package com.fdmgroup.elevator.controller;

import com.fdmgroup.elevator.data.Elevator;
import com.fdmgroup.elevator.view.ElevatorView;
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
 * Entertains user requests and fetch necessary resource
 * @author Gin
 * @version 1.0.0
 */
public class ElevatorController{
    private static final Logger logger = LogManager.getLogger(ElevatorController.class);
    private ElevatorView elevatorView = new ElevatorView();
    private static int maxNumberOfElevators;
    List<Thread> listOfElevatorThreads = new ArrayList<>();
    List<Elevator> listOfElevators = new ArrayList<>();
    /**
     * The level at which the elevator will stop to pick up passengers
     *
     */
    public static String pickUpLevel;
    /**
     * The level at which the elevator will stop to drop off passengers
     *
     */
    public static String dropOffLevel;
    /**
     * <p>This method is a getter that returns the list of elevators
     * </p>
     */
    public List<Elevator> getListOfElevators() {
        return listOfElevators;
    }
    /**
     * <p>This method is a getter that returns the list of threads
     * </p>
     */
    public List<Thread> getListOfElevatorThreads() {
        return listOfElevatorThreads;
    }

    /**
     * <p>This method checks the thread status
     * </p>
     * @return
     */
    public boolean checkifElevatorsAreRunning(){
        boolean stillRunning = false;
        Iterator<Thread> elevatorItr = listOfElevatorThreads.iterator();
        while(elevatorItr.hasNext()){
            Thread elevatorThread = elevatorItr.next();
            if (!elevatorThread.getState().toString().equals("WAITING")){
                    stillRunning = true;
            }
//            System.out.println("Current State of "+elevatorThread.getName()+": "+elevatorThread.getState());
        }
        return stillRunning;
    }

    /**
     * <p>This method starts the threads that the elevators will be running on.
     * </p>
     */
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

    /**
     * <p>This method gets the number of elevators to run and wakes up available threads that are waiting to execute
     * </p>
     * @param input String
     */
    public void configureNumberOfElevators(String input){
        try {
            String src;
            String dest;
            input = input.trim();

            String[] srcDest = input.split(",");
            System.out.println("Number of lifts required: "+srcDest.length);

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
                }
                k++;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
    /**
     * <p>This method updates the state of the application so the ElevatorView class can update accordingly
     * </p>
     */
    public void updateElevatorView(){
        elevatorView.printElevatorStatus(listOfElevators);
    }

}

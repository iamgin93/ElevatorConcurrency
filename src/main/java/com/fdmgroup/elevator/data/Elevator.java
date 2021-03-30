package com.fdmgroup.elevator.data;

import com.fdmgroup.elevator.controller.ElevatorController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents an elevator
 * @author Gin
 * @version 1.0
 */
public class Elevator implements Runnable{
    private static final Logger logger = LogManager.getLogger(Elevator.class);
    private int level = 1;
    private String pickUpLevel;
    private String dropOffLevel;
    /**
     * <p>This method is setter which sets the pick up level of the elevator
     * </p>
     * @param pickUpLevel int: The level at which the elevator will stop to pick up passengers
     */
    public void setPickUpLevel(String pickUpLevel) {
        this.pickUpLevel = pickUpLevel;
    }
    /**
     * <p>This method is a setter which sets the drop off level of the elevator
     * </p>
     * @param dropOffLevel int: The level at which the elevator will stop to drop off passengers
     */
    public void setDropOffLevel(String dropOffLevel) {
        this.dropOffLevel = dropOffLevel;
    }
    /**
     * <p>This method is a getter which returns the current elevator level
     * </p>
     * @return current level the elevator is at
     */
    public int getLevel() {
        return level;
    }
    /**
     * <p>This method is a setter which sets the current elevator level
     * </p>
     * @param level elevator level
     */
    public void setLevel(int level) {
        this.level = level;
    }
    /**
     * <p>This method validates user input and returns a string
     * </p>
     * @param pickUpLevel String: pick up level for the elevator
     * @param dropOffLevel String: drop off level for the elevator
     */
    public void move(String pickUpLevel, String dropOffLevel) {
        try {
            int pickUpLvl = Integer.parseInt(pickUpLevel);
            int dropOffLvl = Integer.parseInt(dropOffLevel);

            String currentThread = Thread.currentThread().getName();
            int currentLevel = getLevel();

            while(currentLevel != pickUpLvl){
                if(currentLevel < pickUpLvl){
                    currentLevel++;
                    System.out.println(currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);
                }else{
                    currentLevel--;
                    System.out.println(currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);

                }
            }
            System.out.println(currentThread + " is picking up passengers at level "+currentLevel);
            while(currentLevel != dropOffLvl){
                if(currentLevel < dropOffLvl){
                    currentLevel++;
                    System.out.println(currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);

                }else{
                    currentLevel--;
                    System.out.println(currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);

                }
            }
            System.out.println(currentThread + " is dropping off passengers at level "+currentLevel);
            setLevel(currentLevel);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }
    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        synchronized (this){
            try {
                while(true){
//                    System.out.println(move);
                    wait();
                    System.out.println(Thread.currentThread().getName() + " is " + Thread.currentThread().getState());
//                    move(ElevatorController.pickUpLevel,ElevatorController.dropOffLevel);
                    move(pickUpLevel,dropOffLevel);
                }
            } catch (InterruptedException e) {
               logger.error(e);
            }
        }
    }

}

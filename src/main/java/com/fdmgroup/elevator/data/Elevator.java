package com.fdmgroup.elevator.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Gin
 * @version 1.0
 */
public class Elevator implements Runnable{
    private static final Logger logger = LogManager.getLogger(Elevator.class);

    private int level = 1;
    private String pickUpLevel;
    private String dropOffLevel;
    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }
    public void move() {
        try {
            int pickUpLvl = Integer.parseInt(pickUpLevel);
            int dropOffLvl = Integer.parseInt(dropOffLevel);

            String currentThread = Thread.currentThread().getName().split("-")[1];
            int currentLevel = getLevel();
            System.out.println("Current Level: "+currentLevel);
            System.out.println("Pick Up Level: "+pickUpLvl);
            System.out.println("Drop Off Level: "+dropOffLvl+"\n");
            while(currentLevel != pickUpLvl){
                if(currentLevel < pickUpLvl){
                    currentLevel++;
                    System.out.println("Lift " + currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);
                }else{
                    currentLevel--;
                    System.out.println("Lift " + currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);

                }
            }
            System.out.println("Lift " + currentThread + " is picking up passengers at level "+currentLevel);
            while(currentLevel != dropOffLvl){
                if(currentLevel < dropOffLvl){
                    currentLevel++;
                    System.out.println("Lift " + currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);

                }else{
                    currentLevel--;
                    System.out.println("Lift " + currentThread + " is at level "+currentLevel);
                    Thread.sleep(1000);

                }
            }
            System.out.println("Lift " + currentThread + " is dropping off passengers at level "+currentLevel);
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
        move();
    }
}

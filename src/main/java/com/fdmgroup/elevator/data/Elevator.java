package com.fdmgroup.elevator.data;

import com.fdmgroup.elevator.controller.ElevatorController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Gin
 * @version 1.0
 */
public class Elevator implements Runnable{
    private static final Logger logger = LogManager.getLogger(Elevator.class);
    private int level = 1;
    private boolean move;
    private String pickUpLevel;
    private String dropOffLevel;

    public String getPickUpLevel() {
        return pickUpLevel;
    }

    public void setPickUpLevel(String pickUpLevel) {
        this.pickUpLevel = pickUpLevel;
    }

    public String getDropOffLevel() {
        return dropOffLevel;
    }

    public void setDropOffLevel(String dropOffLevel) {
        this.dropOffLevel = dropOffLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

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
            System.out.println("Current Level of "+currentThread+": "+currentLevel);
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
    Runnable elevatorThreadRunnable;
    public void setRunnable(Runnable runnable){
        elevatorThreadRunnable = runnable;
        synchronized (this){
            this.notify();
        }
    }
}

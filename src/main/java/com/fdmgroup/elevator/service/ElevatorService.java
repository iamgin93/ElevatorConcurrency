package com.fdmgroup.elevator.service;

import com.fdmgroup.elevator.data.Elevator;

public class ElevatorService implements Runnable{
    private final Elevator elevator;
    private String pickUpLevel;
    private String dropOffLevel;

    public ElevatorService(Elevator elevator, String pickUpLevel, String dropOffLevel) {
        this.elevator = elevator;
        this.pickUpLevel = pickUpLevel;
        this.dropOffLevel = dropOffLevel;
    }

    public void move() {
        try {
            int currentLevel = elevator.getLevel();
            int pickUpLvl = Integer.parseInt(pickUpLevel);
            int dropOffLvl = Integer.parseInt(dropOffLevel);

            String currentThread = Thread.currentThread().getName().split("-")[1];

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
            elevator.setLevel(currentLevel);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

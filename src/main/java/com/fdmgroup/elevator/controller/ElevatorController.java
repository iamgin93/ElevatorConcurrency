package com.fdmgroup.elevator.controller;

import com.fdmgroup.elevator.service.ElevatorService;

public class ElevatorController{
    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    public void operateElevator(){
        Thread t1 = new Thread(elevatorService);
        t1.start();
    }
    public void updateElevatorView(){

    }

}

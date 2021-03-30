package com.fdmgroup.elevator.view;

import com.fdmgroup.elevator.data.Elevator;

import java.util.Iterator;
import java.util.List;

/**
 * Provides details to the user
 * @author Gin
 * @version 1.0
 */
public class ElevatorView {
    /**
     * <p>This method prints to console the necessary information such as current elevator level
     * </p>
     * @param elevatorList List of elevators
     */
    public void printElevatorStatus(List<Elevator> elevatorList){
        Iterator<Elevator> elevatorIterator= elevatorList.iterator();
        int i = 1;
        while(elevatorIterator.hasNext()){
            System.out.println("Current level of Lift " + i + ": " + elevatorIterator.next().getLevel());
            i++;
        }

    }
}

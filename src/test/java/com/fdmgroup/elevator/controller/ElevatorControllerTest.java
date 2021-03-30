package com.fdmgroup.elevator.controller;

import com.fdmgroup.elevator.data.Elevator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ElevatorControllerTest {

    @Test
    void getListOfElevators() {
        ElevatorController elevatorController = new ElevatorController();
        Elevator elevator = new Elevator();

        elevatorController.getListOfElevators().add(elevator);

        assertThat(elevatorController.getListOfElevators().size()).isEqualTo(1);
    }

    @Test
    void getListOfElevatorThreads() {
        ElevatorController elevatorController = new ElevatorController();
        Elevator elevator = new Elevator();

        elevatorController.getListOfElevatorThreads().add(new Thread(elevator));

        assertThat(elevatorController.getListOfElevatorThreads().size()).isEqualTo(1);
    }

    @Test
    void initialiseElevators() {
        ElevatorController elevatorController = new ElevatorController();

        elevatorController.initialiseElevators();

        assertThat(elevatorController.getListOfElevators().size()).isEqualTo(3);
        assertThat(elevatorController.getListOfElevatorThreads().size()).isEqualTo(3);

    }

    @Test
    void updateElevatorView() {
    }

}
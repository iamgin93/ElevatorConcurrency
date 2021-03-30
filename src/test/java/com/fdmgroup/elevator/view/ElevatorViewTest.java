package com.fdmgroup.elevator.view;

import com.fdmgroup.elevator.controller.ElevatorController;
import com.fdmgroup.elevator.data.Elevator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;


class ElevatorViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    void printElevatorStatus() {
        ElevatorView elevatorView = new ElevatorView();
        ElevatorController elevatorController = new ElevatorController();
        Elevator elevator = new Elevator();
        elevatorController.getListOfElevators().add(elevator);

        elevatorView.printElevatorStatus(elevatorController.getListOfElevators());

        assertThat(outputStream.toString().trim()).isEqualTo("Current level of Lift 1: 1");
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }
}
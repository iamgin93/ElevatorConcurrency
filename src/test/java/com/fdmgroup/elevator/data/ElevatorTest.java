package com.fdmgroup.elevator.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    @Test
    void getLevel() {
        assertEquals(1,new Elevator().getLevel());
    }

}
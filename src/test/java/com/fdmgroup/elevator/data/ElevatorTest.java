package com.fdmgroup.elevator.data;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElevatorTest {

    @Test
    void getLevel() {
        assertEquals(1,new Elevator().getLevel());
        assertThat(new Elevator().getLevel()).isEqualTo(1);

    }


    @Test
    void move() {
        Elevator elevator = new Elevator();
        assertThat(elevator.getLevel()).isEqualTo(1);
        elevator.move("1","4");
        assertThat(elevator.getLevel()).isEqualTo(4);
    }
}
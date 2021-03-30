package com.fdmgroup.elevator.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {

    @Test
    void validateUserInput() {
        UserValidation userValidation = new UserValidation();

        Boolean valid = userValidation.validateUserInput("1:2");
        Boolean valid1 = userValidation.validateUserInput("1:a");

        assertThat(valid).isEqualTo(true);
        assertThat(valid1).isEqualTo(false);
    }
}
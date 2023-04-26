package com.crud.tasks.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class EmailSchedulerTest {

    @Autowired
    private EmailScheduler emailScheduler;

    @Test
    public void addSTest() {
        //Given
        Long size = 2L;

        //When
        String sTest = emailScheduler.addS(size);

        //Then
        assertEquals("s",sTest);
    }
}

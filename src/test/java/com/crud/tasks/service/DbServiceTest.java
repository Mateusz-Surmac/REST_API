package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    public void getAllTasksTest() {
        //Given
        Task task = new Task();
        int taskListSize = dbService.getAllTasks().size();

        //When
        dbService.saveTask(task);

        //Then
        assertEquals(taskListSize + 1, dbService.getAllTasks().size());

        //Clean up
        dbService.deleteTask(task.getId());
    }

    @Test
    public void getTaskByIdTest() throws TaskNotFoundException {
        //Given
        Task task = new Task();

        //When
        dbService.saveTask(task);

        //Then
        assertEquals(task.getContent(),dbService.getTaskById(task.getId()).getContent());

        //Clean up
        dbService.deleteTask(task.getId());
    }
}

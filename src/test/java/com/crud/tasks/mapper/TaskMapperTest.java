package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test - title", "Test - content");

        //When
        Task testTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(1L, testTask.getId().longValue());
        assertEquals("Test - title", testTask.getTitle());
    }

    @Test
    public void shouldMapToTaskDto () {
        //Given
        Task task = new Task(1L, "Test - title", "Test - content");

        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1L, testTaskDto.getId().longValue());
        assertEquals("Test - title", testTaskDto.getTitle());
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "Test title1", "Test - content1");
        Task task2 = new Task(2L, "Test title2", "Test - content2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(2, taskDtoList.size());
        assertEquals("Test title2", taskDtoList.get(1).getTitle());
        assertEquals("Test - content1", taskDtoList.get(0).getContent());
    }
}

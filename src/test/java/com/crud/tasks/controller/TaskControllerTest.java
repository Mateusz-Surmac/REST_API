package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetAllTasks() throws Exception {
        //Given
        when(dbService.getAllTasks()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0))
                );
    }

    @Test
    void shouldGetTask() throws Exception {
        // Given
        Task task = new Task(1L, "Test - title", "Test - content");
        TaskDto taskDto = new TaskDto(1L, "Test - title", "Test - content");

        when(dbService.getTaskById(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test - title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test - content")))
                .andExpect(status().is(200)
                );
    }

    @Test
    public void shouldDeleteTaskTest() throws Exception{
        //Given

        //When & Then
        mockMvc
                .perform(delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void shouldUpdateTaskTest() throws Exception {
        //Given
        Task task = new Task(1L, "Test - title", "Test - content");
        TaskDto taskDto = new TaskDto(1L, "Test - title", "Test - content");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(any())))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc
                .perform(put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test - title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test - content")))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldCreateTaskTest() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test - title", "Test - content");
        Task task = new Task(1L, "Test - title", "Test - content");

        when(dbService.saveTask(taskMapper.mapToTask(any(TaskDto.class)))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test - title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test - content")))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}

package main.ru.home.todo;

import main.ru.home.todo.controllers.ToDoController;
import main.ru.home.todo.models.ToDoDto;
import main.ru.home.todo.models.ToDoEntity;
import main.ru.home.todo.repository.ToDoRepository;
import main.ru.home.todo.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {

    @MockBean
    private ToDoRepository repository;
    @MockBean
    private ToDoService service;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testReturn200AndContainsTextContext() throws Exception {
        given(service.findAll()).willReturn(List.of( ToDoDto.toDto(new ToDoEntity(42, "to-do name","to-do description"))));
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("to-do name")))
                .andExpect(content().string(containsString("to-do description")));
    }

    public void test(){

    }

}

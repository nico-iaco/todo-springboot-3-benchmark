package it.iacovelli.todospringboot3benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.iacovelli.todospringboot3benchmark.model.dto.TodoDto;
import it.iacovelli.todospringboot3benchmark.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TodoSpringboot3BenchmarkApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    private final TodoDto todoDto = new TodoDto(
            UUID.randomUUID(),
            "Test task",
            LocalDateTime.now()
    );

    @Test
    void contextLoads() {
    }


    /**
     * This test the creation of a new todo
     */
    @Test
    void testInsert() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/todo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(todoDto.message())));
    }

    /**
     * This test the update of a todo
     */
    @Test
    void testUpdate() throws Exception {
        TodoDto savedTodo = todoService.createTodo(todoDto);
        assert (savedTodo != null);

        TodoDto editedDto = new TodoDto(
                savedTodo.id(),
                "edited task",
                LocalDateTime.now()
        );

        mvc.perform(MockMvcRequestBuilders.patch("/todo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(editedDto.message())));
    }

    /**
     *  This test the update on a non existing todo
      */
    @Test
    void testUpdateNonExisting() throws Exception {
        TodoDto editedDto = new TodoDto(
                UUID.randomUUID(),
                "edited task",
                LocalDateTime.now()
        );

        mvc.perform(MockMvcRequestBuilders.patch("/todo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editedDto)))
                .andExpect(status().isNotFound());
    }

    /**
     * This test the deletion of a todo
     */
    @Test
    void testDelete() throws Exception {
        TodoDto savedTodo = todoService.createTodo(todoDto);
        assert (savedTodo != null);

        mvc.perform(MockMvcRequestBuilders.delete("/todo/" + savedTodo.id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     *  This test the deletion of a non existing todo
      */
    @Test
    void testDeleteNonExisting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/todo/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

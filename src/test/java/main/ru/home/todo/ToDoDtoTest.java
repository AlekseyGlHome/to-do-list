package main.ru.home.todo;

import main.ru.home.todo.models.ToDoDto;
import main.ru.home.todo.models.ToDoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
public class ToDoDtoTest {

    @Autowired
    private JacksonTester<ToDoDto> json;

    @Test
    public void testToDto() {
        ToDoDto toDoDto = ToDoDto.toDto(new ToDoEntity(1, "One to-do", "one to-do"));
        assertEquals(1, toDoDto.getId());
        assertEquals("One to-do", toDoDto.getName());
        assertEquals("one to-do", toDoDto.getDescription());
    }

    @Test
    public void testToEntity() {
        ToDoEntity toDoEntity = ToDoDto.toEntity(new ToDoDto(2, "Two to-do", "two to-do"));
        assertEquals(2, toDoEntity.getId());
        assertEquals("Two to-do", toDoEntity.getName());
        assertEquals("two to-do", toDoEntity.getDescription());
    }

    @Test
    void testSerializeToDo() throws Exception {
        ToDoDto toDoDto = ToDoDto.toDto(new ToDoEntity(2, "Two to-do", "two to-do"));

        assertThat(this.json.write(toDoDto))
                .isStrictlyEqualToJson("simple-todo.json");
    }

    @Test
    void testDeserializeToDo() throws Exception {
        ToDoDto dto = this.json.read("new-todo.json").getObject();
        assertEquals(55, dto.getId());
        assertEquals("New to-do", dto.getName());
        assertEquals("new to-do",dto.getDescription());
    }
}

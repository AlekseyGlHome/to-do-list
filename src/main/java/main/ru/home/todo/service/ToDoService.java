package main.ru.home.todo.service;

import lombok.RequiredArgsConstructor;
import main.ru.home.todo.models.ToDoDto;
import main.ru.home.todo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public Collection<ToDoDto> findAll() {
        return toDoRepository.findAll()
                .stream()
                .map(ToDoDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ToDoDto> findById(int id) {
        return toDoRepository.findById(id).map(ToDoDto::toDto);
    }

    public void deleteById(int id) {
        toDoRepository.deleteById(id);
    }

    public void add(ToDoDto toDoDto) {

//        toDoRepository.save(new ToDoEntity(toDoDto));
        toDoRepository.save(ToDoDto.toEntity(toDoDto));
    }

    public void update(ToDoDto toDoDto) {
        if (toDoRepository.existsById(toDoDto.getId())) {
//            toDoRepository.save(new ToDoEntity(toDoDto));
            toDoRepository.save(ToDoDto.toEntity(toDoDto));
        }
    }
}

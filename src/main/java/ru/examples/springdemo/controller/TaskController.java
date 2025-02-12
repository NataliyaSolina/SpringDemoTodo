package ru.examples.springdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.examples.springdemo.model.Task;
import ru.examples.springdemo.model.User;
import ru.examples.springdemo.repository.TaskRepository;
import ru.examples.springdemo.service.UserServiceImpl;

@Log4j2
@RestController
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Методы для работы со списками задач")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserServiceImpl userService;

    @PostMapping("/tasks")
    @Operation(summary = "Создание задачи",
            description = "Позволяет создать задачу пользователю")
    public Task create(
            @RequestBody Task task) {
        log.debug("Post /tasks task");
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    @Operation(summary = "Вывод всех задач",
            description = "Позволяет вывести всего списка задач")
    public Iterable<Task> getAll() {
        User user = userService.getCurrentUser();

        if (user.getLogin().equalsIgnoreCase("admin")) {
            return taskRepository.findAll();
        }
        return taskRepository.findTasksByUserId(user.getId());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Сотрудник успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    @GetMapping("/tasks/{id}")
    @Operation(summary = "Вывод задачи по id",
            description = "Позволяет вывести задачу по заданному id")
    public Task getById(
            @Parameter(
                    description = "ID задачи, данные по которой запрашиваются",
                    required = true)
            @PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Редактирование задачи по id",
            description = "Позволяет редактировать задачу с заданным id")
    public Task putById(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    @Operation(summary = "Удаление задачи по id",
            description = "Позволяет удалить задачу по заданному id")
    public void deleteById(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    @Operation(summary = "Редактирование отметки выполнения задачи по id",
            description = "Позволяет изменить отметку выполнения на противоположную у задачи с заданным id")
    public Task patchById(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        task.setDone(!task.isDone());
        return taskRepository.save(task);
    }

    @PatchMapping("/tasks/{id}:mark-is-done")
    @Operation(summary = "Редактирование отметки выполнения задачи по id",
            description = "Позволяет изменить отметку выполнения на 'Выполнено' у задачи с заданным id")
    public void patchByIdMark(@PathVariable Long id) {
        taskRepository.markIsDone(id);
    }
}

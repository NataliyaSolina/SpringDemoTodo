package ru.examples.springdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.examples.springdemo.model.Task;
import ru.examples.springdemo.service.TaskServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Методы для работы со списками задач")
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostMapping("/tasks")
    @Operation(summary = "Создание задачи",
            description = "Позволяет создать задачу пользователю")
    public Task createTask(@RequestBody Task task) {
        return taskService.create(task);
    }

    @GetMapping("/tasks")
    @Operation(summary = "Вывод всех задач",
            description = "Позволяет вывести всего списка задач")
    public List<Task> getAllTasks() {
        return taskService.getAllByUser();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Сотрудник успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    @GetMapping("/tasks/{id}")
    @Operation(summary = "Вывод задачи по id",
            description = "Позволяет вывести задачу по заданному id")
    public Task getTaskById(
            @Parameter(description = "ID задачи, данные по которой запрашиваются",
                    required = true)
            @PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Редактирование задачи по id",
            description = "Позволяет редактировать задачу с заданным id")
    public Task putTaskById(@PathVariable Long id, @RequestBody Task task) {
        return taskService.putById(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    @Operation(summary = "Удаление задачи по id",
            description = "Позволяет удалить задачу по заданному id")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    @Operation(summary = "Редактирование отметки выполнения задачи по id",
            description = "Позволяет изменить отметку выполнения на противоположную у задачи с заданным id")
    public Task patchTaskById(@PathVariable Long id) {
        return taskService.patchById(id);
    }

    @PatchMapping("/tasks/{id}:mark-is-done")
    @Operation(summary = "Редактирование отметки выполнения задачи по id",
            description = "Позволяет изменить отметку выполнения на 'Выполнено' у задачи с заданным id")
    public Task patchTaskByIdMark(@PathVariable Long id) {
        return taskService.patchByIdMark(id);
    }
}

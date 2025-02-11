package ru.examples.springdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.examples.springdemo.model.Task;
import ru.examples.springdemo.model.User;
import ru.examples.springdemo.repository.TaskRepository;
import ru.examples.springdemo.service.UserServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserServiceImpl userService;


    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAll() {
        User user = userService.getCurrentUser();

        if (user.getLogin().equalsIgnoreCase("admin")) {
            return taskRepository.findAll();
        }
        return taskRepository.findTasksByUserId(user.getId());
    }

    @GetMapping("/tasks/{id}")
    public Task getById(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @PutMapping("/tasks/{id}")
    public Task putById(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteById(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    public Task patchById(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        task.setDone(!task.isDone());
        return taskRepository.save(task);
    }

    @PatchMapping("/tasks/{id}:mark-is-done")
    public void patchByIdMark(@PathVariable Long id) {
        taskRepository.markIsDone(id);
    }
}

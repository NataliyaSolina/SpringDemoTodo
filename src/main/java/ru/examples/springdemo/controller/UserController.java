package ru.examples.springdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.examples.springdemo.model.User;
import ru.examples.springdemo.repository.UserRepository;
import ru.examples.springdemo.service.UserServiceImpl;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

//    @GetMapping("/tasks")
//    public Iterable<Task> getAll() {
//        return (List<Task>) taskRepository.findAll();
//    }
//
//    @GetMapping("/tasks/{id}")
//    public Task getById(@PathVariable Long id) {
//        return taskRepository.findById(id).orElse(null);
//    }
//
//    @PutMapping("/tasks/{id}")
//    public Task putById(@PathVariable Long id, @RequestBody Task task) {
//        task.setId(id);
//        return taskRepository.save(task);
//    }
//
//    @DeleteMapping("/tasks/{id}")
//    public void deleteById(@PathVariable Long id) {
//        taskRepository.deleteById(id);
//    }
//
//    @PatchMapping("/tasks/{id}")
//    public Task patchById(@PathVariable Long id) {
//        Task task = taskRepository.findById(id).orElse(null);
//        assert task != null;
//        task.setDone(!task.isDone());
//        return taskRepository.save(task);
//    }
//
//    @PatchMapping("/tasks/{id}:mark-is-done")
//    public void patchByIdMark(@PathVariable Long id) {
//        taskRepository.markIsDone(id);
//    }
}

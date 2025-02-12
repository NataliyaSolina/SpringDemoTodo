package ru.examples.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.examples.springdemo.model.Task;
import ru.examples.springdemo.model.User;
import ru.examples.springdemo.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl {

    private final TaskRepository taskRepository;
    private final UserServiceImpl userService;

    public Task create(Task task) {
        User user = userService.getCurrentUser();
        task.setUserId(user.getId());

        return taskRepository.save(task);
    }

    public List<Task> getAllByUser() {
        User user = userService.getCurrentUser();

        return (List<Task>) (user.getLogin().equalsIgnoreCase("admin")
                ? taskRepository.findAll(Sort.by("id").ascending())
                : taskRepository.findTasksByUserIdOrderById(user.getId()));
    }

    public Task getById(Long id) {
        User user = userService.getCurrentUser();

        return (user.getLogin().equalsIgnoreCase("admin")
                ? taskRepository.findById(id).orElse(null)
                : taskRepository.findTasksByIdAndUserId(id, user.getId()).orElse(null));
    }

    public Task putById(Long id, Task task) {
        Task taskOld = getById(id);

        if (taskOld != null) {
            task.setId(id);
            task.setUserId(taskOld.getUserId());
            task.setDone(taskOld.isDone());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        Task task = getById(id);

        if (task != null) {
            taskRepository.deleteById(id);
        } else {

        }
    }

    public Task patchById(Long id) {
        Task task = getById(id);

        if (task != null) {
            task.setDone(!task.isDone());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public Task patchByIdMark(Long id) {
        Task task = getById(id);

        if (task != null) {
            taskRepository.markIsDone(id);
            return getById(id);
        } else {
            return null;
        }
    }
}

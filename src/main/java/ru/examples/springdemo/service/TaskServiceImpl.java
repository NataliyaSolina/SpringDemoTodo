package ru.examples.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.examples.springdemo.repository.TaskRepository;
import ru.examples.springdemo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;


}

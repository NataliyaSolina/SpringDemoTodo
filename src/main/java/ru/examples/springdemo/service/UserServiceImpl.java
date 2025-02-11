package ru.examples.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.examples.springdemo.model.User;
import ru.examples.springdemo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

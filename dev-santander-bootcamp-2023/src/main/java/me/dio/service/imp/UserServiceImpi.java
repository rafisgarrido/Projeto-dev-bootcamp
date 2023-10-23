package me.dio.service.imp;

import me.dio.domain.model.User;
import me.dio.domain.model.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpi implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpi(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if(userToCreate.getId() != null && userRepository.existsById(userToCreate.getId())){
            throw new IllegalArgumentException("This User ID already exists." );
        }
        return userRepository.save(userToCreate);
    }
}

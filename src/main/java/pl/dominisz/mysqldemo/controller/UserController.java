package pl.dominisz.mysqldemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominisz.mysqldemo.model.User;
import pl.dominisz.mysqldemo.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

}

package pl.dominisz.mysqldemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominisz.mysqldemo.dto.CreateUserDto;
import pl.dominisz.mysqldemo.dto.UserDto;
import pl.dominisz.mysqldemo.model.User;
import pl.dominisz.mysqldemo.repository.UserRepository;
import pl.dominisz.mysqldemo.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserDto create(@RequestBody CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        return ResponseEntity.of(userService.findById(id));
    }

    @GetMapping("/users")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

}

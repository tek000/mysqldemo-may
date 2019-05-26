package pl.dominisz.mysqldemo.service;

import pl.dominisz.mysqldemo.dto.CreateUserDto;
import pl.dominisz.mysqldemo.dto.PasswordDto;
import pl.dominisz.mysqldemo.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(CreateUserDto createUserDto);

    Optional<UserDto> findById(Integer id);

    List<UserDto> findAll();

    void addPassword(Integer userId, PasswordDto passwordDto);
}

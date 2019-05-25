package pl.dominisz.mysqldemo.service;

import org.springframework.stereotype.Service;
import pl.dominisz.mysqldemo.dto.CreateUserDto;
import pl.dominisz.mysqldemo.dto.UserDto;
import pl.dominisz.mysqldemo.model.User;
import pl.dominisz.mysqldemo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(CreateUserDto createUserDto) {
        User user = toUser(createUserDto);
        user = userRepository.save(user);
        return toUserDto(user);
    }

    private UserDto toUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    private User toUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        return user;
    }

    @Override
    public Optional<UserDto> findById(Integer id) {

        return userRepository.findById(id).map(user -> toUserDto(user));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> toUserDto(user)).collect(Collectors.toList());

    }
}

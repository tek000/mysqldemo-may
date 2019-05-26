package pl.dominisz.mysqldemo.service;

import org.springframework.stereotype.Service;
import pl.dominisz.mysqldemo.dto.CreateUserDto;
import pl.dominisz.mysqldemo.dto.PasswordDto;
import pl.dominisz.mysqldemo.dto.UserDto;
import pl.dominisz.mysqldemo.exception.InvalidPasswordException;
import pl.dominisz.mysqldemo.exception.UserNotFoundException;
import pl.dominisz.mysqldemo.model.Password;
import pl.dominisz.mysqldemo.model.User;
import pl.dominisz.mysqldemo.repository.UserRepository;


import java.time.LocalDateTime;
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

    @Override
    public void addPassword(Integer userId, PasswordDto passwordDto) {
        //znajdz użytkownika o userID
        //jeśli nie istniej to wyrzuc wyjątek 404


        //Sprawdzic!!
//        Optional<User> optionalUser = userRepository.findById(userId).
//                orElseThrow( UserNotFoundException(userId));

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        //porównaj ostatnich 5 haseł

        //jeśli jest takie samo to wyrzuc wjątek
        if (!validPassword(optionalUser.get().getPasswords(), passwordDto.getPassword())) {
            throw new InvalidPasswordException();
        }

        //zapis do bazy

        Password password = new Password();
        password.setValue(passwordDto.getPassword());
        password.setCreationDate(LocalDateTime.now());
        password.setUser(optionalUser.get());

        optionalUser.get().getPasswords().add(password);
        userRepository.save(optionalUser.get());
    }

    private boolean validPassword(List<Password> passwords, String password) {
        List<Password> lastFivePasswords = passwords.stream()
                .sorted((password1, password2) -> password2.getCreationDate()
                        .compareTo(password1.getCreationDate()))
                .limit(5)
                .collect(Collectors.toList());

        return lastFivePasswords.stream().noneMatch(password1 -> password1.getValue().equals(password));

    }
}

package pl.dominisz.mysqldemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer id;
    private String name;
    private String email;
}

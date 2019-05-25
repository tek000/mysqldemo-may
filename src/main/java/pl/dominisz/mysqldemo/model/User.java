package pl.dominisz.mysqldemo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Password> passwords;
}

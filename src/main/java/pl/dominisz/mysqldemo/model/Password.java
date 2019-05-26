package pl.dominisz.mysqldemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Password {

    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    User user;

    LocalDateTime creationDate;

    private String value;

}

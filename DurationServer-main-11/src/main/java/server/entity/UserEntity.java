package server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Сущность Пользователя
 * <p>
 * Поля:
 * <p>
 * id - уникальный идентификатор
 * <p>
 * login - логин
 * <p>
 * firstName - имя
 * <p>
 * lastName - фамилия
 * <p>
 * email - почта
 * <p>
 * password - пароль
 * <p>
 * phoneNumber - номер телефона
 * <p>
 * birthDate - дата рождения
 * <p>
 * portfolio - множество объектов портфелей
 */
@Entity
@Table(name = "users")
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(length = 40, nullable = false, unique = true)
    private String login;
    @Column(length = 40, nullable = false)
    private String firstName;
    @Column(length = 40, nullable = false)
    private String lastName;
    @Column(length = 40, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 20)
    private String phoneNumber;
    @Column
    private LocalDate birthDate;

    @OneToMany
    @JoinTable
    private Set<PortfolioEntity> portfolio;
}

package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий Пользователя
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Найти пользователя по email
     *
     * @param email почта пользователя
     * @return пользователя
     */
    Optional<UserEntity> findUserByEmail(String email);

    /**
     * Обновить пользователя в БД
     *
     * @param email     почта
     * @param password  пароль
     * @param firstName имя
     * @param lastName  фамилия
     * @param birthDate дата рождения
     * @param phoneNumber номер телефона
     */
    @Modifying
    @Query(value = "update users u set u.email=:email, u.first_name=:firstName, u.last_name= :lastName," +
            " u.login=:email, u.password=:password, u.birth_date=:birthDate, u.phone_number=:phoneNumber " +
            "where u.email=:email", nativeQuery = true)
    void updateByEmail(String email, String password, String firstName, String lastName, LocalDate birthDate, String phoneNumber);

    /**
     * Удалить пользователя из БД по email
     *
     * @param email почта
     */
    void deleteByEmail(String email);

    /**
     * Найти пользователя по почте и паролю
     *
     * @param email    почта
     * @param password пароль
     * @return список пользователей
     */
    Optional<UserEntity> findUserEntityByEmailAndPassword(String email, String password);

    /**
     * Найти пользователя по login
     *
     * @param login логин пользователя
     * @return список пользователей
     */
    List<UserEntity> findUserEntityByLogin(String login);
}

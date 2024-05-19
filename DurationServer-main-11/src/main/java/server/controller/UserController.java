package server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.UserAuthDTO;
import server.dto.UserRegistrationDTO;
import server.entity.UserEntity;
import server.service.UserService;
import server.utils.StringToHashUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс контроллер Пользователя
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Регистрации для пользователя
     *
     * @param user UserRegistrationDTO, поля регистрации
     * @return map и HTTP статус "OK"
     */
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegistrationDTO user) {
        String hashPassword = StringToHashUtil.convert(user.getPassword());
        UserEntity result = userService.checkAuth(user.getLogin(), hashPassword);
        Map<String, Object> map = new HashMap<>();
        if (result == null) {
            userService.saveUser(user);
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Авторизация пользователя
     *
     * @param user userAuthDTO, поля авторизации
     * @return map и HTTP статус "OK"
     */
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDTO user) {
        if (user.getPassword() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String hashPassword = StringToHashUtil.convert(user.getPassword());
        UserEntity result = userService.checkAuth(user.getEmail(), hashPassword);
        Map<String, Object> map = new HashMap<>();
        if (result == null) {
            map.put("success", false);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        map.put("success", true);
        map.put("result", result);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Обновление пользователя
     *
     * @param user сущность пользователя
     * @return map и HTTP статус "OK"
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateByEmail(@RequestBody UserEntity user) {
        Map<String, Object> map = new HashMap<>();
        userService.updateByEmail(user);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /**
     * Удаление пользователя
     *
     * @param email    почта
     * @param password пароль
     * @return HTTP статус
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserByEmail(@RequestParam(name = "email") String email,
                                               @RequestParam(name = "password") String password) {
        String hashPassword = StringToHashUtil.convert(password);
        if (userService.checkAuth(email, hashPassword) != null) {
            userService.deleteByEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}

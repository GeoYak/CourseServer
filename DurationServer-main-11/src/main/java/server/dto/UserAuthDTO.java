package server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO для авторизации пользователя
 */
@Getter
@Setter
@AllArgsConstructor
public class UserAuthDTO {
    private String email;
    private String password;
}

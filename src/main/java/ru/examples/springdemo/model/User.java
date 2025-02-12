package ru.examples.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @Schema(description = "Уникальный логин пользователя", example = "Nata", accessMode = Schema.AccessMode.READ_WRITE)
    private String login;

    @Schema(description = "Уникальный пароль пользователя (храниться в зашифрованном виде)", example = "Spring3", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

}

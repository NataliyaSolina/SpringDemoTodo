package ru.examples.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @Schema(description = "Уникальный логин пользователя", example = "Nata", accessMode = Schema.AccessMode.READ_WRITE)
    private String login;

    @Schema(description = "Уникальный пароль пользователя (храниться в зашифрованном виде)", example = "Spring3", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private List<Task> tasks = new ArrayList<>();
}


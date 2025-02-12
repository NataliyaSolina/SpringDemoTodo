package ru.examples.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор задачи", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Дата (дедлайн) задачи", example = "2025-02-12", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate date;

    @Schema(description = "Описание задачи", example = "Закончить курс", accessMode = Schema.AccessMode.READ_WRITE)
    private String description;

    @Column(name = "user_id")
    // TODO: 12.02.2025 12:07 один ко многим привязку сделать
    @Schema(description = "Идентификатор пользователя которому принадлежит задача", example = "2", accessMode = Schema.AccessMode.READ_ONLY)
    private Long userId;

    @Schema(description = "Отметка выполнения задачи", example = "false", accessMode = Schema.AccessMode.READ_ONLY)
    private boolean done;

}

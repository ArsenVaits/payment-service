package com.example.project5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO (
        @NotBlank(message = "Поле username обязательно!")
        @Size(message = "Поле username не должно превышать 30 символов")
        String username,
        @NotBlank(message = "Поле password обязательно!")
        @Size(message = "Поле password не может превышать 30 символов")
        String password) {
}

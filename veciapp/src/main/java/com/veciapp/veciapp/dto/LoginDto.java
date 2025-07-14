package com.veciapp.veciapp.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "email cannot be null")
        String email,
        @NotBlank(message = "password cannot be null")
        String password
) {
}

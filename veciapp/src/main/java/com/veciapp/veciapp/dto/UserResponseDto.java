package com.veciapp.veciapp.dto;

import com.veciapp.veciapp.entities.UserEntity;

public record UserResponseDto(
        String name,
        String imp
) {
    public UserResponseDto(UserEntity user) {
        this(user.getName(), user.getImg());
    }
}

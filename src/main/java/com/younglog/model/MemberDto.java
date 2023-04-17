package com.younglog.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@ToString
public class MemberDto {
    @NotBlank(message = "Email Cannot be empty!")
    private String email;
    @NotBlank(message = "Password Cannot be empty!")
    private String password;

    public MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

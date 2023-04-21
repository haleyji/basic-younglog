package com.younglog.model;


import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {
    private Long id;

    private String email;

    @Builder
    public MemberResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}

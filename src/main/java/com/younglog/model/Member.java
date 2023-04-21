package com.younglog.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String password;
    private LocalDateTime signupDate;

    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.signupDate = LocalDateTime.now();
    }

    public MemberResponse toResponse() {
        return MemberResponse.builder().id(this.id).email(this.email).build();
    }
}

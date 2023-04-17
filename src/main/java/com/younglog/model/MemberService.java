package com.younglog.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(MemberDto request) {
        Member member = Member.builder().email(request.getEmail()).password(request.getPassword()).build();
        return null;
    }

    public Member login() {

        return null;
    }


}

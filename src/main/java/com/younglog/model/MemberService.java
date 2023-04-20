package com.younglog.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(MemberDto request) {
        Member member = Member.builder().email(request.getEmail()).password(request.getPassword()).build();
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    public Member login(MemberDto request) {
        Optional<Member> member = memberRepository.findByEmail(request.getEmail());
        if (member.isPresent()) {
            Member loggedInMember = member.get();
            if (loggedInMember.getPassword().equals(request.getPassword())) {
                return loggedInMember;
            }else{
                throw new RuntimeException();
            }
        }
        throw new RuntimeException();
    }
}

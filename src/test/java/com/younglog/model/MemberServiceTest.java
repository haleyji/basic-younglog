package com.younglog.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("이메일, 패스워드로 가입한다")
    void test() {
        MemberDto memberDto = MemberDto.builder().email("test@co.kr").password("test1234").build();
        Member joinMember = memberService.join(memberDto);
        Assertions.assertThat(joinMember.getId()).isNotNull();
        Assertions.assertThat(memberRepository.findByEmail(joinMember.getEmail()).get().getId()).isEqualTo(joinMember.getId());
    }

    @Test
    @DisplayName("가입한 이메일 패스워드로 로그인 한다")
    void test1(){
        MemberDto joinMemberDto = MemberDto.builder().email("test@co.kr").password("test1234").build();
        Member joinMember = memberService.join(joinMemberDto);

        MemberDto memberDto = MemberDto.builder().email("test@co.kr").password("test1234").build();
        Member login = memberService.login(memberDto);
        Assertions.assertThat(login.getId().longValue()).isEqualTo(joinMember.getId().longValue());
    }
}
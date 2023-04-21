package com.younglog.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("test@co.kr, test1234로 회원가입한다")
    void test() throws Exception {
        String email = "test@co.kr";
        String password = "test1234";
        MemberDto memberDto = MemberDto.builder().email(email).password(password).build();

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isOk());
        Member member = memberRepository.findByEmail(email).get();
        Assertions.assertNotNull(member);

        System.out.println(member.toString());
    }


    @Test
    @DisplayName("아이디를 빈값으로 하면 회원가입할 수 없다")
    void test1() throws Exception {
        String email = "";
        String password = "test1234";
        MemberDto memberDto = MemberDto.builder().email(email).password(password).build();

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("test@co.kr, test1234로 회원가입 후, 로그인한다")
    void test2() throws Exception {
        String email = "test@co.kr";
        String password = "test1234";
        MemberDto memberDto = MemberDto.builder().email(email).password(password).build();

        memberRepository.save(Member.builder().email(email).build());

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isOk());
        Member member = memberRepository.findByEmail(email).get();
        Assertions.assertNotNull(member);

        System.out.println(member.toString());
    }


}
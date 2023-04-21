package com.younglog.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/join")
    public ResponseEntity<MemberResponse> join(@RequestBody @Valid MemberDto memberDto) {
        Member member = memberService.join(memberDto);
        return new ResponseEntity<>(member.toResponse(), HttpStatus.OK);
    }

    @PostMapping("/member/login")
    public ResponseEntity<MemberResponse> login(@RequestBody @Valid MemberDto memberDto) {
        Member member = memberService.login(memberDto);
        return new ResponseEntity<>(member.toResponse(), HttpStatus.OK);
    }
}

package com.Mutation.Tr.member.controller;

import ch.qos.logback.core.model.Model;
import com.Mutation.Tr.api.kakao.KakaoService;
import com.Mutation.Tr.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final KakaoService kakaoService;

    @GetMapping(value = "/kakaoAuthenticate")
    public String kakaoAuthenticate(Model model) {

        return "/member/testContent";
    }
}

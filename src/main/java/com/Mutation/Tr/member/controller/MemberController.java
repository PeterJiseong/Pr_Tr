package com.Mutation.Tr.member.controller;

import ch.qos.logback.core.model.Model;
import com.Mutation.Tr.api.kakao.KakaoService;
import com.Mutation.Tr.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final KakaoService kakaoService;
    @Value("${kakao.restApi.authorizeUrl}")
    private String authorizeUrl;
    @Value("${kakao.restApi.url}")
    private String kakaoUrl;
    @Value("${kakao.restApi.key}")
    private String kakaoAPiKey;

    @GetMapping(value = "/kakaoAuthorize")
    public String kakaoAuthorize(Model model) {
        System.err.println("kakaoAuthorize 동작");
        String resp = kakaoService.kakaoAuthorize();
        System.err.println("resp : " + resp);
        String redirectUrl = UriComponentsBuilder.fromUriString(kakaoUrl+"/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoAPiKey)
                .queryParam("redirect_uri", "http://localhost:8000/member/oauthToken")
                .build()
                .toUriString();
        System.err.println("redirectUrl : " + redirectUrl);
        return "redirect:"+ redirectUrl;
    }

    @GetMapping(value="/oauthToken")
    public String joinByKakao(Model model, @RequestParam(value = "code",required = false) String code,
                              @RequestParam(value = "error", required = false) String error, @RequestParam(value="error_description", required=false) String errorDescription,
                              @RequestParam(value = "state" , required = false) String state) {

        System.err.println("oauthToken");
        System.err.println("code : "+code);
        System.err.println("error : "+error);
        System.err.println("error_des : "+errorDescription);
        System.err.println("state : "+state);
        String redirectUrl = UriComponentsBuilder.fromUriString(kakaoUrl + "/oauth/token")
                .queryParam("grant_type",  "authorization_code")
                .queryParam("client_id",  kakaoAPiKey)
                .queryParam("redirect_uri",  "http://localhost:8000/member/joinByKakao")
                .queryParam("code",  code)
                .build()
                .toString();
        System.err.println("redirectUrl : " + redirectUrl);
        return "redirect:"+ redirectUrl;
    }

    @GetMapping(value = "/joinByKakao")
    public String joinByKakao(Model model, @RequestParam(value = "token_type") String TokenType,
                              @RequestParam(value = "access_token") String accessToken,
                              @RequestParam(value = "refresh_token") String refreshToken,
                              @RequestParam(value = "expires_in") int expiresIn,
                              @RequestParam(value = "refresh_token_expires_in") int refreshTokenExpiresIn,
                              @RequestParam(value = "id_token", required = false) String idToken,
                              @RequestParam(value = "scope" , required = false) String scope) {


        System.err.println("joinByKakao");
        System.err.println("tokenType : "+TokenType);
        System.err.println("accessToken : "+accessToken);
        System.err.println("refreshToken : "+refreshToken);
        System.err.println("expiresIn : "+expiresIn);
        System.err.println("refreshTokenExpiresIn : "+refreshTokenExpiresIn);
        System.err.println("idToken : "+idToken);
        System.err.println("scope : "+scope);

        return "main/member/testContent2";
    }
}

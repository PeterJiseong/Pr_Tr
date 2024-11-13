package com.Mutation.Tr.api.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class KakaoService {

    @Autowired
    @Qualifier("kakaoAuthorize")
    private WebClient kakaoAuthorize;

    @Value("${kakao.restApi.url}")
    private String kakaoRestApiUrl;

    public void kakaoAuthorize(){
        String response = kakaoAuthorize.get()
                .uri(kakaoRestApiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.err.println(response);
    }

}

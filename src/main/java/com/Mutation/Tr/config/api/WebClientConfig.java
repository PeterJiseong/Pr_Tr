package com.Mutation.Tr.config.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${notion.apiKey}")
    private String notionApiKey;

    @Value("${notion.version}")
    private String notionVersion;

//    @Value("${notion.database.id}")
//    private String notionDatabaseId;

    @Value("${notion.api.url}")
    private String notionApiUrl;

    @Value("${kakao.restApi.url}")
    private String kakaoRestApiUrl;

    @Value("${kakao.restApi.key}")
    private String kakaoRestApiKey;


    @Bean
    @Qualifier("notionWebclient")
    public WebClient notionWebClient() {
        return WebClient.builder()
                .baseUrl(notionApiUrl)
                .defaultHeader("Authorization", "Bearer " + notionApiKey)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Notion-Version", notionVersion)
                .build();
    }
    @Bean
    @Qualifier("kakaoAuthorize")
    public WebClient kakaoWebClient() {
        return WebClient.builder()
                .baseUrl(kakaoRestApiUrl)
                .defaultHeader("response_type","code")
                .defaultHeader("client_id","kakaoRestApiKey")
                .defaultHeader("redirect_uri","http://www.projectyrion.com")
                .build();
    }

    @Bean(name = "notionObjectMapper")
    public ObjectMapper notionObjectMapper() {
        return new ObjectMapper();
    }
}

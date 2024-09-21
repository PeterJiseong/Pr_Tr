package com.Mutation.Tr.config.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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


    @Bean(name = "notionWebclient")
    public WebClient notionWebClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(notionApiUrl)
                .defaultHeader("Authorization", "Bearer " + notionApiKey)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Notion-Version", notionVersion)
                .build();

        return webClient;
    }

    @Bean(name = "notionObjectMapper")
    public ObjectMapper notionObjectMapper() {
        return new ObjectMapper();
    }
}

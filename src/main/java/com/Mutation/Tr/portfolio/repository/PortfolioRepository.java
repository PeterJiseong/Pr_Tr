package com.Mutation.Tr.portfolio.repository;

import com.Mutation.Tr.util.jsonParsing.NotionParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Repository
@RequiredArgsConstructor
public class PortfolioRepository {

    private final NotionParser notionParser;

    @Autowired
    @Qualifier("notionObjectMapper")
    private ObjectMapper notionObjectMapper;

    @Autowired
    @Qualifier("notionWebclient")
    private WebClient notionWebclient;

    @Value("${notion.apiKey}")
    private String notionApiKey;

    @Value("${notion.database.id}")
    private String notionDatabaseId;

    @Value("${notion.api.url}")
    private String notionApiUrl;

    @Value("${portfolio.htmlContentPath}")
    private String path;

    public JsonNode getBlockNotionAPI(String blockId){
        String testBlock = "551a7ee9c26d4fa5be054bd5f7afa1bb";
//        String testBlock = "1de891a5a1f747a78b7899e7184f66a6";
        try {
            String response = notionWebclient.get()
                    .uri("/blocks/{block_id}/children", blockId)  // 여기에 페이지 ID를 사용하여 블록 자식을 조회
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.err.println("response : "+response);
//            System.err.println("notion : " + notion);
            JsonNode notionNode = notionObjectMapper.readTree(response);

            return notionNode;  // JSON 형식으로 반환된 블록 데이터를 문자열로 반환
        } catch (WebClientResponseException e) {
            System.err.println(e.getMessage());
            return null;
        }catch (JsonMappingException e) {
            e.printStackTrace();
            System.err.println("jsonMappingException");
            return null;
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            System.err.println("jsonProcessingException");
            return null;
        }
    }
}

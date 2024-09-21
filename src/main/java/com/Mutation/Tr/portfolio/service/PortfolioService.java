package com.Mutation.Tr.portfolio.service;

import com.Mutation.Tr.portfolio.repository.PortfolioRepository;
import com.Mutation.Tr.util.jsonParsing.NotionParser;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final NotionParser notionParser;

//    public int getDatabase(){
//
//        Mono<String> response = notionWebclient.post()
//                .uri("database/" + notionDatabaseId + "/query"+"?filter_properties=title")
//                .retrieve().bodyToMono(String.class);
//
//        String responseBody = response.block();
//        System.err.println(responseBody);
//
//        return 0;
//    }
    //최초 페이지 요청
    public String getNotionPage(String pageId){
        JsonNode jsonNode = getNotionApi(pageId);
        StringBuilder notionBuilder = new StringBuilder();
        blockToResponse(jsonNode, notionBuilder);

        return notionBuilder.toString();
    }
    //블록순회 모듈
    private void blockToResponse(JsonNode jsonNode, StringBuilder notionBuilder){
        for(JsonNode block : jsonNode){
            System.err.println("block :  " + block);
            boolean hasChildren = block.get("has_children").asBoolean();

            notionParser.jsonDestruction(block, notionBuilder);

            //자식 요소가 있을 시 notion에 요청
            if(hasChildren){
                String blockId = block.get("id").asText().replaceAll("-","");
                JsonNode childrenNode = getNotionApi(blockId);
                blockToResponse(childrenNode, notionBuilder);
            }
        }
    }

    private JsonNode getNotionApi(String blockId) {
        return portfolioRepository.getBlockNotionAPI(blockId).get("results");
//        boolean hasChild = jsonNode.get("has_children").asBoolean();
//        notionParser.jsonDestruction(jsonNode, notionBuilder);
//        return hasChild ? jsonToHtml(
//                jsonNode.get("id").asText()
//        ) : 0;
    }

//        "bulleted_list_item"
//        "callout"
//        "child_database"
//        "child_page"
//        "column"
//        "column_list"
//        "divider"
//        "embed"
//        "equation"
//        "file"
//        "link_preview"
//        "link_to_page"
//        "numbered_list_item"
//        "paragraph"
//        "pdf"
//        "quote"
//        "synced_block"
//        "table_of_contents"
//        "table_row"
//        "template"
//        "to_do"
//        "toggle"
//        "unsupported"
//        "video"
}

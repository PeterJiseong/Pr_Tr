package com.Mutation.Tr.util.jsonParsing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotionParser implements JsonParser {
    @Qualifier("notionObjectMapper")
    private final ObjectMapper notionObjectMapper;
    private final JacksonProperties jacksonProperties;

    @Override
    public void jsonDestruction(JsonNode json, StringBuilder stringBuilder) {
//        System.err.println("jsonDestruction_json : " + json);
        String type = json.get("type").asText();
        JsonNode richText = json.get(type).get("rich_text");
        stringBuilder.append("<div class='");
        stringBuilder.append(type);
        stringBuilder.append("'>");
        switch (type) {
            case "table":
                stringBuilder.append("<div>테이블 잠시대기</div>");
                createTable(json, stringBuilder);
                break;
            case "table_row":
                System.out.println(json);
                break;
            case "callout":
                break;
            case "image" :
                createImage(json, stringBuilder);
                break;
            case "heading_1" :
                stringBuilder.append("<h1 class='heading_1");
                addClassByAnnotation(richText.get(0), stringBuilder);
                stringBuilder.append("'>");
                addContent(richText.get(0), stringBuilder);
                stringBuilder.append("</h1>");
                break;
            case "heading_2" :
                stringBuilder.append("<h2 class='");
                addClassByAnnotation(richText.get(0), stringBuilder);
                stringBuilder.append("'>");
                addContent(richText.get(0), stringBuilder);
                stringBuilder.append("</h2>");
                break;
            case "heading_3" :
                stringBuilder.append("<h3 class='");
                addClassByAnnotation(richText.get(0), stringBuilder);
                stringBuilder.append("'>");
                addContent(richText.get(0), stringBuilder);
                stringBuilder.append("</h3>");
                break;
//            case "bulleted_list_item" :
//
//                break;
                default:

                    richText(richText, stringBuilder);

                    break;

        }
        stringBuilder.append("</div>");

    }

    private void addClassByAnnotation(JsonNode json, StringBuilder notionBuilder) {
        if(json == null ){
            return;
        }
        json = json.get("annotations");
        json.fields().forEachRemaining(field -> {
            if(!field.getValue().asText().equals("false")){
//                System.err.println("True !!");
                notionBuilder.append(" "+field.getKey() + "_" + field.getValue().asText());
            }
        });
    }

    private void createImage(JsonNode json, StringBuilder notionBuilder) {
//        System.err.println("image_json : " + json);
        String url = json.get("image").get("file").get("url").asText();
        notionBuilder.append("<img src='");
        notionBuilder.append(url);
        notionBuilder.append("' alt='error'>");
    }

    private void createBookmark(){

    }

    private void addClassList(JsonNode json, StringBuilder notionBuilder) {

    }
    private void createTable(JsonNode json, StringBuilder notionBuilder) {
    }
    private void createDefaultDiv(JsonNode json, StringBuilder stringBuilder) {
    }
    private void richText(JsonNode richText, StringBuilder notionBuilder) {
//        System.err.println("richText : " + richText);
        if(richText.isEmpty()){
            notionBuilder.append("<p class='notion_empty'></p>");
            return;
        }

//        if(richText.size() <= 1){
//
//            notionBuilder.append("<p class='notion_content");
//            addClassByAnnotation(richText.get(0), notionBuilder);
//            notionBuilder.append("'>");
//
//            notionBuilder.append("</p>");
//            return;
//        }
        for(JsonNode each : richText){
            String href = each.get("href")==null||each.get("href").isNull() ? null : each.get("href").asText();
            if(href != null){
                notionBuilder.append("<a class='notion_link");
//                System.err.println("each : " + each);
                addClassByAnnotation(each, notionBuilder);
                notionBuilder.append("'>");
                addContent(each, notionBuilder);
                notionBuilder.append("</a>");
                return;
            }
            notionBuilder.append("<p class='notion_content");
//            System.err.println("each : " + each.toString());
            addClassByAnnotation(each, notionBuilder);
            notionBuilder.append("'>");
            addContent(each, notionBuilder);
            notionBuilder.append("</p>");

        }

    }
    private void addContent(JsonNode json, StringBuilder notionBuilder) {
        if(json!=null){
            notionBuilder.append(json.get("plain_text").asText());
        }
    }

}

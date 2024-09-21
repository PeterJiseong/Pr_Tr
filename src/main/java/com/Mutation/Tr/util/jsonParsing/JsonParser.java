package com.Mutation.Tr.util.jsonParsing;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonParser {
//    public String jsonDestruction(JsonNode json);
    public void jsonDestruction(JsonNode json, StringBuilder stringBuilder);
}

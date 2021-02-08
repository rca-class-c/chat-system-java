package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Messages;
import utils.ChatBetweenTwo;

public class MessageDecoder {
    String data;

    public MessageDecoder(String data) {
        this.data = data;
    }

    public ChatBetweenTwo returnChatMembers() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new ChatBetweenTwo(dataDecrypt.get("first").asInt(),dataDecrypt.get("last").asInt());
    }

    public Messages returnMessageContent() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new Messages(dataDecrypt.get("content").asText(),dataDecrypt.get("sender").asInt(),dataDecrypt.get("user_receiver").asInt(),dataDecrypt.get("group_receiver").asInt(),dataDecrypt.get("original_message").asInt());
    }
}

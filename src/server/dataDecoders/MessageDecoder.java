package server.dataDecoders;

import client.interfaces.MessageResponseDataFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Messages;
import utils.ChatBetweenTwo;
import utils.GroupMessage;

public class MessageDecoder {
    String data;

    public MessageDecoder(String data) {
        this.data = data;
    }

    public ChatBetweenTwo returnChatMembers() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new ChatBetweenTwo(dataDecrypt.get("firstUser").asInt(),dataDecrypt.get("lastUser").asInt());
    }

    //author: Edine Noella
    public Messages returnMessageContent() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new Messages(dataDecrypt.get("content").asText(),dataDecrypt.get("sender").asInt(),dataDecrypt.get("user_receiver").asInt(),dataDecrypt.get("group_receiver").asInt(),dataDecrypt.get("original_message").asInt());
    }
    public GroupMessage returnMessageContentGroup() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new GroupMessage(dataDecrypt.get("content").asText(),dataDecrypt.get("sender").asInt(),dataDecrypt.get("group_receiver").asInt(),dataDecrypt.get("original_message").asInt());
    }

    public Messages returnMessageReplies() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new Messages(dataDecrypt.get("content").asText(), dataDecrypt.get("sender").asInt(), dataDecrypt.get("user_receiver").asInt(), dataDecrypt.get("group_receiver").asInt(), dataDecrypt.get("original_message").asInt());
    }
        public MessageResponseDataFormat returnMessageDeleteData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
       JsonNode  dataDecrypt = objectMapper.readTree(data);
        return new MessageResponseDataFormat(dataDecrypt.get("user").asInt(),dataDecrypt.get("message_id").asInt());
    }
    public  MessageResponseDataFormat returnMessageEditData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new MessageResponseDataFormat(dataDecrypt.get("user").asInt(),dataDecrypt.get("message_id").asInt(), dataDecrypt.get("content").asText());
    }
}

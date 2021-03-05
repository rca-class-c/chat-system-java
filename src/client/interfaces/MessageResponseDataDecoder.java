package client.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Messages;
import server.models.User;
import utils.GroupMessage;
import utils.GroupNotifications;

public class MessageResponseDataDecoder {
    Object data;
    String success;

    public Object getData() {
        return data;
    }

    public String getSuccess() {
        return success;
    }
    public Messages[] returnMessagesNotificationsList(String data)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Messages[] messages = objectMapper.readValue(data, Messages[].class);
        return messages;
    }
    public GroupNotifications[] returnGroupNotifications(String data)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        GroupNotifications[] messages = objectMapper.readValue(data, GroupNotifications[].class);
        return messages;
    }
    public Messages[] returnDecodedReplies(String data)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();

        Messages[] messages = objectMapper.readValue(data, Messages[].class);
        return messages;
    }
    public  Messages returnDecodedMessage(String data) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();

        Messages messages = objectMapper.readValue(data, Messages.class);
        return messages;
    }
}

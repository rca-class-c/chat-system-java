package client.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public class NotificationDecoder {
    public Set notificationsList(String data)throws JsonProcessingException {
        System.out.println(data);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(data, Set.class);
    }
}

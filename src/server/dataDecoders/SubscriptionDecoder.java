package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Subscription;

public class SubscriptionDecoder {
    String Data;
    public  SubscriptionDecoder(String Data){
        this.Data=Data;
    }
    public Subscription CreateSubscriptionDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode dataDecrypt= objectMapper.readTree(Data);
        return new Subscription(dataDecrypt.get("SubscriptionId").asInt(),dataDecrypt.get("UserID").asInt(),dataDecrypt.get("PackageId").asInt());
    }
}

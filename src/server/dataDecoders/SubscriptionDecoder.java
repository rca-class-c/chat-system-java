package server.dataDecoders;

public class SubscriptionDecoder {
    String Data;
    public  SubscriptionDecoder(String Data){
        this.Data=Data;
    }
    public server.models.Subscription CreateSubscriptionDecoder() throws com.fasterxml.jackson.core.JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper=new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt= objectMapper.readTree(Data);
        return new server.models.Subscription(dataDecrypt.get("SubscriptionId").asInt(),dataDecrypt.get("UserID").asInt(),dataDecrypt.get("PackageId").asInt());
    }
}

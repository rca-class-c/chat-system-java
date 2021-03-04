package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Payment;

public class PaymentDecoder {
    String data;
    public PaymentDecoder(String data){
        this.data = data;
    }

    public Payment createPaymentDecoder() throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);

        return new Payment(dataDecrypt.get("subId").asInt(),dataDecrypt.get("discount").asInt(),dataDecrypt.get("totalAmount").asInt());
    }

    public int getPaymentDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);

        return dataDecrypt.get("id").asInt();
    }
}

package server.dataDecoders;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class PaymentDecoder {
    String data;
    public PaymentDecoder(String data){
        this.data = data;
    }

    public server.models.Payment createPaymentDecoder() throws com.fasterxml.jackson.core.JsonProcessingException{
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt = objectMapper.readTree(data);
        return new server.models.Payment(dataDecrypt.get("subId").asInt(),dataDecrypt.get("discount").asInt(),dataDecrypt.get("totalAmount").asInt());
    }

    public int getPaymentDecoder() throws com.fasterxml.jackson.core.JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt = objectMapper.readTree(data);

        return dataDecrypt.get("id").asInt();
    }
}

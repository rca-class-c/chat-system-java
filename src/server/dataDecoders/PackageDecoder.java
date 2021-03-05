package server.dataDecoders;
import server.models.Package;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class PackageDecoder {
    String data;
    public PackageDecoder(String data) {
        this.data = data;
    }

    public Package createPackageDecoder() throws com.fasterxml.jackson.core.JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt = objectMapper.readTree(data);

        return new Package(dataDecrypt.get("packageName").asText(),dataDecrypt.get("period").asInt(),dataDecrypt.get("price").asInt());
    }

    public int getPackageInfoDecoder() throws com.fasterxml.jackson.core.JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt = objectMapper.readTree(data);

        return dataDecrypt.get("id").asInt();
    }
    public server.models.Package updatePackageDecoder() throws com.fasterxml.jackson.core.JsonProcessingException{
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt = objectMapper.readTree(data);

        return new server.models.Package(dataDecrypt.get("id").asInt(), dataDecrypt.get("packageName").asText(),dataDecrypt.get("period").asInt(),dataDecrypt.get("price").asInt());
    }
    public int deletePackageDecoder() throws com.fasterxml.jackson.core.JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper= new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt=objectMapper.readTree(data);
        return dataDecrypt.get("id").asInt();
    }
    public String searchPackageDecoder() throws com.fasterxml.jackson.core.JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.JsonNode dataDecrypt = objectMapper.readTree(data);
        return dataDecrypt.get("search_data").asText();
    }
}

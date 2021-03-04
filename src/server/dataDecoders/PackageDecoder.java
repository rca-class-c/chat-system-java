package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public Package createPackageDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);

        return new Package(dataDecrypt.get("packageName").asText(),dataDecrypt.get("period").asInt(),dataDecrypt.get("price").asInt());
    }

    public int getPackageInfoDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);

        return dataDecrypt.get("id").asInt();
    }
    public Package updatePackageDecoder() throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);

        return new Package(dataDecrypt.get("id").asInt(), dataDecrypt.get("packageName").asText(),dataDecrypt.get("period").asInt(),dataDecrypt.get("price").asInt());
    }
    public int deletePackageDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode dataDecrypt=objectMapper.readTree(data);
        return dataDecrypt.get("id").asInt();
    }
    public String searchPackageDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return dataDecrypt.get("search_data").asText();
    }
}

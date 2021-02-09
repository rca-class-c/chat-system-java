package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.File;


/**
 * File related request data decoder
 * @author: Divin Irakiza
 */
public class FileDecoder {

    String data;
    public FileDecoder(String data) {
        this.data = data;
    }

    public File SaveFileDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);

        return new File(dataDecrypt.get("fileLocalPath").asText(),dataDecrypt.get("fileName").asText(),dataDecrypt.get("fileType").asText(),dataDecrypt.get("fileSize").asInt(),dataDecrypt.get("fileSizeType").asText(),dataDecrypt.get("senderId").asInt());
    }
}

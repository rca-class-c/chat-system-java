package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *Description: This class is a decoder of all data sent to sending invitations service
 @author Didier Munezero
 */
public class SendInvitationDecoder {
    String data;
    public SendInvitationDecoder(String data) {
        this.data = data;
    }

    public String[] retrieveEmails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String[] emails = objectMapper.readValue(data, String[].class);
        return emails;
    }
}

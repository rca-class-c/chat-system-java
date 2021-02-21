package server.requestHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import com.fasterxml.jackson.core.JsonProcessingException;
import server.dataDecoders.PasswordResetsDecoder;
import server.models.PasswordResets;
import server.models.Response;
import server.services.PasswordResetService;

import java.io.PrintWriter;
import java.util.Map;

/**
 * password reset request handling class
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class PasswordResetsRequestHandler {

    /**
     * handling password reset initiation request
     *
     * @param data data needed to initiate password reset
     * @param writer writer instance
     * @param objectMapper object mapper instance
     * @throws JsonProcessingException json proccess exception
     * @throws SQLException sql exception
     * @author Ntwari Clarance Liberiste
     */
    public void handlePasswordResetInitiation(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException{
        PasswordResets passwordReset = new PasswordResetService().create(new PasswordResetsDecoder(data).initiatePasswordResetDecode());

        if(passwordReset == null){
            System.out.println("password reset initiation failed");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }else{
            Response response = new Response(passwordReset,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println("password reset email was sent to " + passwordReset.getEmail()+"");
            writer.println(ResponseAsString);
        }
    }


    public void handlePasswordReset(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException{
        Map<String,String> passwordResetObject = new PasswordResetsDecoder(data).resetPasswordDecode();

        boolean passwordReset = new PasswordResetService().resetPassword(passwordResetObject.get("userEmail"),Integer.parseUnsignedInt(passwordResetObject.get("otp")),passwordResetObject.get("newPassword"));

        if(!passwordReset){
            System.out.println("can't reset password");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }else{
            Response response = new Response(passwordResetObject, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(passwordResetObject.get("userEmail")+ " changed password successfully");
            writer.println(ResponseAsString);
        }
    }
}

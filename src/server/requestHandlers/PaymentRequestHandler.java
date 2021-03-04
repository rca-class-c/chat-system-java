package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.dataDecoders.PaymentDecoder;
import server.models.Payment;
import server.models.Response;
import server.services.PaymentService;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class PaymentRequestHandler {

    public void HandleSavePayment(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Payment payment=new PaymentDecoder(data).createPaymentDecoder();
        payment.setTotalAmount(new utils.Discount().TotalAmount(payment.getDiscount(), payment.getTotalAmount()).getTotalAmount());
        Payment returned = new PaymentService().savePayment(payment);

        if(returned == null){
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }else{
            Response response = new Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleGetPaymentDetails(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Payment  payment = new PaymentService().getPaymentDetails(new PaymentDecoder(data).getPaymentDecoder());

        if(payment == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(payment,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

}

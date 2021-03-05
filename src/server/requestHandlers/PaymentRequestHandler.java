package server.requestHandlers;

import server.dataDecoders.PaymentDecoder;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class PaymentRequestHandler {

    public void HandleSavePayment(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException {
        server.models.Payment payment=new PaymentDecoder(data).createPaymentDecoder();
        payment.setTotalAmount(new utils.Discount().TotalAmount(payment.getDiscount(), payment.getTotalAmount()).getTotalAmount());
        server.models.Payment returned = new server.services.PaymentService().savePayment(payment);

        if(returned == null){
            server.models.Response response = new server.models.Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }else{
            server.models.Response response = new server.models.Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void HandleGetPaymentDetails(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException {
        server.models.Payment  payment = new server.services.PaymentService().getPaymentDetails(new PaymentDecoder(data).getPaymentDecoder());

        if(payment == null){
            server.models.Response response = new server.models.Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            server.models.Response response = new server.models.Response(payment,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

}

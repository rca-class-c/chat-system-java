package server.requestHandlers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.dataDecoders.SubscriptionDecoder;
import server.models.Response;
import server.models.Subscription;
import server.services.SubscriptionService;
import utils.ValidSubscription;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @authors:
 * - Cyusa Keny
 * - Loraine Irakoze
 */
public class SubscriptionHandler {
    public void HandleNewSubscriptions(String Data, ObjectMapper mapper, PrintWriter writer) throws JsonProcessingException, SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subscription subscription=new SubscriptionDecoder(Data).CreateSubscriptionDecoder();
        subscription.setSubscribedAt(timestamp);
           int returned= new SubscriptionService().AddNewSubscription(subscription);
        if (returned == 0) {
            Response response = new Response(null,false);
            String ResponseAsString = mapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = mapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
   public  void ValidatingSubscriptionHandler(String Data , ObjectMapper mapper,PrintWriter writer) throws JsonProcessingException {
       Subscription subscription = new SubscriptionDecoder(Data).CreateSubscriptionDecoder();
       if (subscription == null) {
           Response response = new Response(null, false);
           String responseString = mapper.writeValueAsString(response);
           writer.println(responseString);
       } else if (new ValidSubscription().isValid(subscription.getExpirationDate().toString()) == true) {
           Response response = new Response(true, true);
           String responseString = mapper.writeValueAsString(response);
           writer.println(responseString);
       } else {
           Response response = new Response(false, true);
           String responseString = mapper.writeValueAsString(response);
           writer.println(responseString);
       }
   }
  public void DeleteSubscription(String Data ,ObjectMapper mapper,PrintWriter writer) throws JsonProcessingException, SQLException {
      Subscription subscription = new SubscriptionDecoder(Data).CreateSubscriptionDecoder();
        if (new SubscriptionService().GetSubscription(subscription.getSubscriptionId())==null){
            Response response = new Response(null, false);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
        else{
            int returned=new SubscriptionService().DeleteSubscription(subscription.getSubscriptionId());
            Response response = new Response(returned, true);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
  }
  public  void ShowSubscriptionHandler(String Data,ObjectMapper mapper,PrintWriter writer) throws JsonProcessingException, SQLException {
        Subscription subscription=new SubscriptionDecoder(Data).CreateSubscriptionDecoder();
        if (new SubscriptionService().GetSubscription(subscription.getSubscriptionId())==null){
            Response response = new Response(null, false);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
        else {
            Subscription returned= new SubscriptionService().GetSubscription(subscription.getSubscriptionId());
            Response response = new Response(returned, true);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
  }
}

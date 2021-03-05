package server.requestHandlers;

/**
 *
 * @authors:
 * - Cyusa Keny
 * - Loraine Irakoze
 */
public class SubscriptionHandler {
    public void HandleNewSubscriptions(String Data, com.fasterxml.jackson.databind.ObjectMapper mapper, java.io.PrintWriter writer) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        server.models.Subscription subscription=new server.dataDecoders.SubscriptionDecoder(Data).CreateSubscriptionDecoder();
        subscription.setSubscribedAt(timestamp);
           int returned= new server.services.SubscriptionService().AddNewSubscription(subscription);
        if (returned == 0) {
            server.models.Response response = new server.models.Response(null,false);
            String ResponseAsString = mapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            server.models.Response response = new server.models.Response(returned,true);
            String ResponseAsString = mapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
   public  void ValidatingSubscriptionHandler(String Data , com.fasterxml.jackson.databind.ObjectMapper mapper, java.io.PrintWriter writer) throws com.fasterxml.jackson.core.JsonProcessingException {
       server.models.Subscription subscription = new server.dataDecoders.SubscriptionDecoder(Data).CreateSubscriptionDecoder();
       if (subscription == null) {
           server.models.Response response = new server.models.Response(null, false);
           String responseString = mapper.writeValueAsString(response);
           writer.println(responseString);
       } else if (new utils.ValidSubscription().isValid(subscription.getExpirationDate().toString()) == true) {
           server.models.Response response = new server.models.Response(true, true);
           String responseString = mapper.writeValueAsString(response);
           writer.println(responseString);
       } else {
           server.models.Response response = new server.models.Response(false, true);
           String responseString = mapper.writeValueAsString(response);
           writer.println(responseString);
       }
   }
  public void DeleteSubscription(String Data , com.fasterxml.jackson.databind.ObjectMapper mapper, java.io.PrintWriter writer) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException {
      server.models.Subscription subscription = new server.dataDecoders.SubscriptionDecoder(Data).CreateSubscriptionDecoder();
        if (new server.services.SubscriptionService().GetSubscription(subscription.getSubscriptionId())==null){
            server.models.Response response = new server.models.Response(null, false);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
        else{
            int returned=new server.services.SubscriptionService().DeleteSubscription(subscription.getSubscriptionId());
            server.models.Response response = new server.models.Response(returned, true);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
  }
  public  void ShowSubscriptionHandler(String Data, com.fasterxml.jackson.databind.ObjectMapper mapper, java.io.PrintWriter writer) throws com.fasterxml.jackson.core.JsonProcessingException, java.sql.SQLException {
        server.models.Subscription subscription=new server.dataDecoders.SubscriptionDecoder(Data).CreateSubscriptionDecoder();
        if (new server.services.SubscriptionService().GetSubscription(subscription.getSubscriptionId())==null){
            server.models.Response response = new server.models.Response(null, false);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
        else {
            server.models.Subscription returned= new server.services.SubscriptionService().GetSubscription(subscription.getSubscriptionId());
            server.models.Response response = new server.models.Response(returned, true);
            String responseString = mapper.writeValueAsString(response);
            writer.println(responseString);
        }
  }
}

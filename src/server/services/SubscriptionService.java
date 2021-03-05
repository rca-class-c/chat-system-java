package server.services;

/**
 * @authors
 * Cyusa Keny
 * Irakoze Loraine
 */
public class SubscriptionService {
private  final  server.repositories.SubscriptionRepository repository=new server.repositories.SubscriptionRepository();
public  int AddNewSubscription(server.models.Subscription subscription) throws java.sql.SQLException {
    return repository.AddNewSubcription(subscription);
}
public int UpdateSubscription(server.models.Subscription subscription) throws java.sql.SQLException {
    return repository.UpdateSubscription(subscription);
}
public  int DeleteSubscription(int Id) throws java.sql.SQLException {
    return repository.DeleteSubscription(Id);
}
public server.models.Subscription GetSubscription(int Id) throws java.sql.SQLException {
    return repository.GetSubscription(Id);
}
}

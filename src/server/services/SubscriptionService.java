package server.services;
import server.models.Payment;
import server.models.Subscription;
import server.repositories.SubscriptionRepository;
import server.services.PaymentService;
import java.sql.SQLException;
import utils.ValidSubscription;

/**
 * @authors
 * Cyusa Keny
 * Irakoze Loraine
 */
public class SubscriptionService {
private  final  SubscriptionRepository repository=new SubscriptionRepository();
public  int AddNewSubscription(Subscription subscription) throws SQLException {
    return repository.AddNewSubcription(subscription);
}
public int UpdateSubscription(Subscription subscription) throws SQLException {
    return repository.UpdateSubscription(subscription);
}
public  int DeleteSubscription(int Id) throws SQLException {
    return repository.DeleteSubscription(Id);
}
public Subscription GetSubscription(int Id) throws SQLException {
    return repository.GetSubscription(Id);
}
}

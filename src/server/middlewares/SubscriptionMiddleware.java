package server.middlewares;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class SubscriptionMiddleware {

    private final utils.ValidSubscription validSubscription = new utils.ValidSubscription();

    public boolean checkValid(server.models.Subscription subscribe){
        return validSubscription.isValid(subscribe.getExpirationDate().toString());
    }
}

package server.middlewares;

import server.models.Subscription;
import utils.ValidSubscription;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class SubscriptionMiddleware {

    private final ValidSubscription validSubscription = new ValidSubscription();

    public boolean checkValid(Subscription subscribe){
        return validSubscription.isValid(subscribe.getExpirationDate().toString());
    }
}

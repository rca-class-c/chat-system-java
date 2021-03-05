package server.repositories;

/**
 * @author
 * Cyusa Keny
 * Irakoze Loraine
 */
public class SubscriptionRepository {
    public int AddNewSubcription(server.models.Subscription subscription) throws java.sql.SQLException {
        int period=new server.services.PackageService().getPackageInfoById(subscription.getPackageId()).getPeriod();
        java.sql.Connection connection = server.config.PostegresConfig.getConnection();
        String Query="Insert  into  subscription ( user_id ,package_id,subscribed_at,expiration_date)VALUES (?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(Query);
        statement.setInt(1,subscription.getUserID());
        statement.setInt(2,subscription.getPackageId());
        statement.setTimestamp(3,subscription.getSubscribedAt());
        statement.setDate(4,new utils.Expiration().expirationCalculator(period));
        int rows=statement.executeUpdate();
        connection.close();
        if (rows>=1){
            return 1;
        }
        else{
            return 0;
        }
    }
    public int UpdateSubscription(server.models.Subscription subscription) throws java.sql.SQLException {
        int period=new server.services.PackageService().getPackageInfoById(subscription.getPackageId()).getPeriod();
        java.sql.Connection connection = server.config.PostegresConfig.getConnection();
        String Query="UPDATE subscription SET user_id=?,package_id=?,subscribed_at=?,expiration_date=? WHERE sub_id=? ";
        java.sql.PreparedStatement statement= connection.prepareStatement(Query);
        statement.setInt(1,subscription.getUserID());
        statement.setInt(2,subscription.getPackageId());
        statement.setTimestamp(3,subscription.getSubscribedAt());
        statement.setDate(4, new utils.Expiration().expirationCalculator(period));
        statement.setInt(5,subscription.getSubscriptionId());
        int rows=statement.executeUpdate();
        connection.close();
        if (rows >= 1) {
            return 1;
        }
        else{
            return 0;
        }
    }
    public  int DeleteSubscription(int Id) throws java.sql.SQLException {
        java.sql.Connection connection = server.config.PostegresConfig.getConnection();
        String Query="DELETE  from subscription WHERE sub_id=?";
        java.sql.PreparedStatement statement= connection.prepareStatement(Query);
        statement.setInt(1,Id);
        int rows= statement.executeUpdate();
        connection.close();
        if (rows >= 1) {
            return 1;
        }
        else {
            return 0;
        }
    }
    public  server.models.Subscription GetSubscription(int Id) throws java.sql.SQLException {
        server.models.Subscription subscription = new server.models.Subscription();
        java.sql.Connection connection = server.config.PostegresConfig.getConnection();
        String Query="SELECT  * from subscription WHERE sub_id=?";
        java.sql.PreparedStatement statement =connection.prepareStatement(Query);
        statement.setInt(1,Id);
        java.sql.ResultSet rst=statement.executeQuery();
        while (rst.next()){
            subscription.setSubscriptionId(rst.getInt("sub_id"));
            subscription.setUserID(rst.getInt("user_id"));
            subscription.setPackageId(rst.getInt("package_id"));
            subscription.setSubscribedAt(rst.getTimestamp("subscribed_at"));
            subscription.setExpirationDate(rst.getDate("expiration_date"));
        }
        connection.close();
        return subscription;
    }
}

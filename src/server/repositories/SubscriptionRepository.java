package server.repositories;
import server.config.PostegresConfig;
import   server.models.Subscription;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utils.Expiration;
import  server.services.PackageService;

/**
 * @author
 * Cyusa Keny
 * Irakoze Loraine
 */
public class SubscriptionRepository {
    public int AddNewSubcription(Subscription subscription) throws SQLException {
        int period=new PackageService().getPackageInfoById(subscription.getPackageId()).getPeriod();
        Connection connection = PostegresConfig.getConnection();
        String Query="Insert  into  subscription ( user_id ,package_id,subscribed_at,expiration_date)VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(Query);
        statement.setInt(1,subscription.getUserID());
        statement.setInt(2,subscription.getPackageId());
        statement.setTimestamp(3,subscription.getSubscribedAt());
        statement.setDate(4,new Expiration().expirationCalculator(period));
        int rows=statement.executeUpdate();
        connection.close();
        if (rows>=1){
            return 1;
        }
        else{
            return 0;
        }
    }
    public int UpdateSubscription(Subscription subscription) throws SQLException {
        int period=new PackageService().getPackageInfoById(subscription.getPackageId()).getPeriod();
        Connection connection =PostegresConfig.getConnection();
        String Query="UPDATE subscription SET user_id=?,package_id=?,subscribed_at=?,expiration_date=? WHERE sub_id=? ";
        PreparedStatement statement= connection.prepareStatement(Query);
        statement.setInt(1,subscription.getUserID());
        statement.setInt(2,subscription.getPackageId());
        statement.setTimestamp(3,subscription.getSubscribedAt());
        statement.setDate(4, new Expiration().expirationCalculator(period));
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
    public  int DeleteSubscription(int Id) throws SQLException {
        Connection connection =PostegresConfig.getConnection();
        String Query="DELETE  from subscription WHERE sub_id=?";
        PreparedStatement statement= connection.prepareStatement(Query);
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
    public  Subscription GetSubscription(int Id) throws SQLException {
        Subscription subscription = new Subscription();
        Connection connection =PostegresConfig.getConnection();
        String Query="SELECT  * from subscription WHERE sub_id=?";
        PreparedStatement statement =connection.prepareStatement(Query);
        statement.setInt(1,Id);
        ResultSet rst=statement.executeQuery();
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

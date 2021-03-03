package server.repositories;

import server.config.PostegresConfig;
import server.models.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepository {

    public Payment savePayment(Payment payment) throws SQLException {
        Connection connection = PostegresConfig.getConnection();
        String query = "INSERT INTO payment (sub_id, discount, total_amount) VALUES(?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, payment.getSubId());
        statement.setFloat(2, payment.getDiscount());
        statement.setFloat(3, payment.getTotalAmount());

        statement.executeUpdate();
        statement.close();
        connection.close();

        return payment;
    }

    public Payment getPaymentDetails(int payment_id) throws SQLException {

        Connection connection= PostegresConfig.getConnection();
        String query = "SELECT * from payment where pay_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,payment_id);

        ResultSet result = statement.executeQuery(query);

        if(result.next()){

            int payId = result.getInt(1);
            int subId = result.getInt(2);
            float discount = result.getFloat(3);
            float totalAmount = result.getFloat(4);

            return new Payment(payId, subId, discount, totalAmount);
        }
        statement.close();
        connection.close();

        return null;
    }
}

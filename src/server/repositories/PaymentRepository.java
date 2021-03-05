package server.repositories;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class PaymentRepository {

    public server.models.Payment savePayment(server.models.Payment payment) throws java.sql.SQLException {
        java.sql.Connection connection = server.config.PostegresConfig.getConnection();
        String query = "INSERT INTO payment (sub_id, discount, total_amount) VALUES(?,?,?,?)";

        java.sql.PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, payment.getSubId());
        statement.setFloat(2, payment.getDiscount());
        statement.setFloat(3, payment.getTotalAmount());

        statement.executeUpdate();
        statement.close();
        connection.close();

        return payment;
    }

    public server.models.Payment getPaymentDetails(int payment_id) throws java.sql.SQLException {

        java.sql.Connection connection= server.config.PostegresConfig.getConnection();
        String query = "SELECT * from payment where pay_id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,payment_id);

        java.sql.ResultSet result = statement.executeQuery(query);

        if(result.next()){

            int payId = result.getInt(1);
            int subId = result.getInt(2);
            float discount = result.getFloat(3);
            float totalAmount = result.getFloat(4);

            return new server.models.Payment(payId, subId, discount, totalAmount);
        }
        statement.close();
        connection.close();

        return null;
    }
}

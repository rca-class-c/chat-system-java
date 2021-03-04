package server.services;

import server.models.Payment;
import server.repositories.PaymentRepository;

import java.sql.SQLException;


/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class PaymentService {
    private final PaymentRepository paymentRepository = new PaymentRepository();

    public Payment getPaymentDetails(int package_id) throws SQLException {
        return paymentRepository.getPaymentDetails(package_id);
    }
    public Payment savePayment(Payment payed) throws SQLException {
        return paymentRepository.savePayment(payed);
    }
}

package server.services;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class PaymentService {
    private final server.repositories.PaymentRepository paymentRepository = new server.repositories.PaymentRepository();

    public server.models.Payment getPaymentDetails(int package_id) throws java.sql.SQLException {
        return paymentRepository.getPaymentDetails(package_id);
    }
    public server.models.Payment savePayment(server.models.Payment payed) throws java.sql.SQLException {
        return paymentRepository.savePayment(payed);
    }

}

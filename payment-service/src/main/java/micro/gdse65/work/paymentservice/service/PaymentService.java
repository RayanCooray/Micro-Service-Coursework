package micro.gdse65.work.paymentservice.service;

import micro.gdse65.work.paymentservice.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    void savePayment(PaymentDTO paymentDTO);

    List<PaymentDTO> getAllPayment();

    PaymentDTO getPayment(String paymentId);


}

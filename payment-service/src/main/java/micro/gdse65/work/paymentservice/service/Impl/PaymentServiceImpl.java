package micro.gdse65.work.paymentservice.service.Impl;

import lombok.RequiredArgsConstructor;
import micro.gdse65.work.paymentservice.convertion.ConvertionData;
import micro.gdse65.work.paymentservice.dto.PaymentDTO;
import micro.gdse65.work.paymentservice.repostory.PaymentRepo;
import micro.gdse65.work.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;

    @Autowired
    ConvertionData convertionData;

    @Override
    public void savePayment(PaymentDTO paymentDTO) {

    }

    @Override
    public List<PaymentDTO> getAllPayment() {
        return convertionData.mapTo(paymentRepo.findAll(), PaymentDTO.class);

    }

    @Override
    public PaymentDTO getPayment(String paymentId) {
        return convertionData.mapTo(paymentRepo.findById(paymentId).get(), PaymentDTO.class);

    }
}

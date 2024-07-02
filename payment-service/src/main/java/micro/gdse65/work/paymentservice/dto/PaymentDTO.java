package micro.gdse65.work.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDTO {
    private String paymentId;
    private String paymentDate;
    private double amount;
    private String ticketId;
}


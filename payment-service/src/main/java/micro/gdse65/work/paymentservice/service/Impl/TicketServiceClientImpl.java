package micro.gdse65.work.paymentservice.service.Impl;

import lombok.RequiredArgsConstructor;
import micro.gdse65.work.paymentservice.service.TicketServiceClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TicketServiceClientImpl implements TicketServiceClient {
    private final RestTemplate restTemplate;
    @Override
    public boolean isExitsTicket(String id) {
        try {
            String url = "http://ticket-service/api/v1/ticket/ticketExists/" + id;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            return userExists != null && userExists;
        } catch (Exception e) {
        }
        return false;
    }

}

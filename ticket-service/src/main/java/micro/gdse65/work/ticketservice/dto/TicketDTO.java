package micro.gdse65.work.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.gdse65.work.ticketservice.Enum.TicketStatus;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketDTO {
    private String ticketId;
    private LocalDate ticketIssueDate;
    private TicketStatus ticketStatus;
    private String vehicleId;
    private String userId;
}
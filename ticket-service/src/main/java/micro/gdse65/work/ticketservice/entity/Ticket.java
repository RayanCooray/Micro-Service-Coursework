package micro.gdse65.work.ticketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.gdse65.work.ticketservice.Enum.TicketStatus;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {
    @Id
    private String ticketId;
    private LocalDate ticketIssueDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    private String vehicleId;
    private String userId;
}
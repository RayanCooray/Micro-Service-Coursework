package micro.gdse65.work.ticketservice.service;

import micro.gdse65.work.ticketservice.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    void saveTicket(TicketDTO ticketDTO);

    TicketDTO getTicket(String ticketId);

    List<TicketDTO> getAllTickets();

    void updateTicket(TicketDTO ticketDTO);

    void deleteTicket(String ticketId);

    List<TicketDTO> getTicketsByUserId(String userId);

    List<TicketDTO> getTicketsByVehicleId(String vehicleId);

    boolean isTicketExists(String ticketId);
}

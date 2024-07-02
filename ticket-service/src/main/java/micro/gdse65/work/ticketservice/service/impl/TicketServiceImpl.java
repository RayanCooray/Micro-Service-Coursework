package micro.gdse65.work.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import micro.gdse65.work.ticketservice.Enum.TicketStatus;
import micro.gdse65.work.ticketservice.convertion.ConvertionData;
import micro.gdse65.work.ticketservice.dto.TicketDTO;
import micro.gdse65.work.ticketservice.entity.Ticket;
import micro.gdse65.work.ticketservice.repostory.TicketRepo;
import micro.gdse65.work.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    @Autowired
    ConvertionData convertionData;

    @Autowired
    TicketRepo ticketRepo;


    @Override
    public void saveTicket(TicketDTO ticketDTO) {
        ticketRepo.save(convertionData.mapTo(ticketDTO, Ticket.class));
    }

    @Override
    public TicketDTO getTicket(String ticketId) {
        return convertionData.mapTo(ticketRepo.findById(ticketId).get(), TicketDTO.class);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return convertionData.mapTo(ticketRepo.findAll(), TicketDTO.class);
    }

    @Override
    public void updateTicket(TicketDTO ticketDTO) {
        if (!ticketRepo.existsById(ticketDTO.getTicketId())){
            return;
        }
        ticketRepo.save(convertionData.mapTo(ticketDTO, Ticket.class));
    }

    @Override
    public void deleteTicket(String ticketId) {
        ticketRepo.deleteById(ticketId);
    }

    @Override
    public List<TicketDTO> getTicketsByUserId(String userId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<Ticket> tickets = ticketRepo.findAllByUserId(userId);
        for (Ticket ticket : tickets) {
            tickeDTOs.add(convertionData.mapTo(ticket, TicketDTO.class));
        }
        return tickeDTOs;
    }

    @Override
    public List<TicketDTO> getTicketsByVehicleId(String vehicleId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<Ticket> tickets = ticketRepo.findAllByVehicleId(vehicleId);
        for (Ticket ticket : tickets) {
            tickeDTOs.add(convertionData.mapTo(ticket, TicketDTO.class));
        }
        return tickeDTOs;
    }

    @Override
    public boolean isTicketExists(String ticketId) {
        if (ticketRepo.existsById(ticketId)){
            TicketDTO ticket = getTicket(ticketId);
            ticket.setTicketStatus(TicketStatus.PAID);
            updateTicket(ticket);
            return true;
        }
        return false;
    }
}

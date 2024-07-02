package micro.gdse65.work.ticketservice.repostory;

import micro.gdse65.work.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,String> {
    List<Ticket> findAllByVehicleId(String vehicleId);

    List<Ticket> findAllByUserId(String userId);
}

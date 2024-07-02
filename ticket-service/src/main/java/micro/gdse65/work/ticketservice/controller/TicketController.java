package micro.gdse65.work.ticketservice.controller;

import micro.gdse65.work.ticketservice.Enum.TicketStatus;
import micro.gdse65.work.ticketservice.dto.TicketDTO;
import micro.gdse65.work.ticketservice.service.TicketService;
import micro.gdse65.work.ticketservice.service.UserServiceClient;
import micro.gdse65.work.ticketservice.service.VehicleServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/ticket")
// TODO: Implement ticket-related endpoints here (GET, POST, PUT, DELETE)
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    UserServiceClient userServiceClient;
    @Autowired
    VehicleServiceClient vehicleServiceClient;

    @GetMapping("/health")
    public String health(){
        return "Ticket Service is healthy";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTicket(@RequestBody TicketDTO ticketDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userServiceClient.isExitsUser(ticketDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!vehicleServiceClient.isExitsVehicle(ticketDTO.getVehicleId())) {
            return ResponseEntity.badRequest().body("Vehicle not found");
        }
        ticketDTO.setTicketIssueDate(LocalDate.now());
        ticketDTO.setTicketStatus(TicketStatus.UNPAID);
        ticketService.saveTicket(ticketDTO);
        return ResponseEntity.ok("Ticket saved successfully");
    }

    @GetMapping(value = "/{ticketId}")
    public ResponseEntity<?> getTicket(@PathVariable ("ticketId") String ticketId){
        return ResponseEntity.ok(ticketService.getTicket(ticketId));
    }

    @GetMapping
    public ResponseEntity<?> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTicket(@RequestBody TicketDTO ticketDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userServiceClient.isExitsUser(ticketDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!vehicleServiceClient.isExitsVehicle(ticketDTO.getVehicleId())) {
            return ResponseEntity.badRequest().body("Vehicle not found");
        }
        ticketDTO.setTicketStatus(TicketStatus.UNPAID);
        ticketDTO.setTicketIssueDate(LocalDate.now());
        ticketService.updateTicket(ticketDTO);
        return ResponseEntity.ok("Ticket updated successfully");
    }

    @DeleteMapping(value = "/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable ("ticketId") String ticketId){
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("Ticket deleted successfully");
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getTicketsByUserId(@PathVariable ("userId") String userId){
        if (!userServiceClient.isExitsUser(userId)){
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(ticketService.getTicketsByUserId(userId));
    }

    @GetMapping(value = "/vehicle/{vehicleId}")
    public ResponseEntity<?> getTicketByVehicleId(@PathVariable ("vehicleId") String vehicleId){
        if (!vehicleServiceClient.isExitsVehicle(vehicleId)){
            return ResponseEntity.badRequest().body("Vehicle not found");
        }
        return ResponseEntity.ok(ticketService.getTicketsByVehicleId(vehicleId));
    }
    @GetMapping("/ticketExists/{ticketId}")
    public ResponseEntity<?> isTicketExists(@PathVariable ("ticketId") String ticketId) {
        try {
            boolean isTicketExists = ticketService.isTicketExists(ticketId);
            return ResponseEntity.ok(isTicketExists);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to check ticket existence.\nMore Details\n" + exception);
        }
    }
}

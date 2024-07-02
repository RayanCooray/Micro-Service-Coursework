package micro.gdse65.work.vehicleservice.Controller;

import micro.gdse65.work.vehicleservice.dto.VehicleDTO;
import micro.gdse65.work.vehicleservice.service.UserServiceCilent;
import micro.gdse65.work.vehicleservice.service.VehicleService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    @Autowired
    private UserServiceCilent userServiceCilent;

    @Autowired
    private VehicleService VEHICLE_SERVICE;
    @RequestMapping("/get")
    public String helloVehicle() {
        return "Hello, Vehicle!";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userServiceCilent.isExistUser(vehicleDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        VEHICLE_SERVICE.saveVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle saved successfully");
    }

    @PutMapping()
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        VEHICLE_SERVICE.updateVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable ("vehicleId") String vehicleId){
        return ResponseEntity.ok(VEHICLE_SERVICE.getVehicle(vehicleId));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable ("vehicleId") String vehicleId){
        VEHICLE_SERVICE.deleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicles(){
        return ResponseEntity.ok(VEHICLE_SERVICE.getAllVehicles());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehiclesByUserId(@PathVariable ("userId") String userId){
        return ResponseEntity.ok(VEHICLE_SERVICE.getVehicleByUserId(userId));
    }



}

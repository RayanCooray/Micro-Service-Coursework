package micro.gdse65.work.vehicleservice.repostory;

import micro.gdse65.work.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle,String> {
    List<Vehicle> findAllByUserId(String userId);
}

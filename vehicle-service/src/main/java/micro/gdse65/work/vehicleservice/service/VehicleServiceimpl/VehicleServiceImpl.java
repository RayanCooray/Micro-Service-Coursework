package micro.gdse65.work.vehicleservice.service.VehicleServiceimpl;

import lombok.RequiredArgsConstructor;
import micro.gdse65.work.vehicleservice.convertion.ConvertionData;
import micro.gdse65.work.vehicleservice.dto.VehicleDTO;
import micro.gdse65.work.vehicleservice.entity.Vehicle;
import micro.gdse65.work.vehicleservice.repostory.VehicleRepo;
import micro.gdse65.work.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl  implements VehicleService {
    // Implement vehicle-related CRUD operations here
    @Autowired
    ConvertionData convertionData;

    @Autowired
    VehicleRepo vehicleRepository;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleRepository.save(convertionData.mapTo(vehicleDTO, Vehicle.class));
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        if(!vehicleRepository.existsById(vehicleDTO.getVehicleId())){
            return;
        }
        vehicleRepository.save(convertionData.mapTo(vehicleDTO, Vehicle.class));
    }

    @Override
    public VehicleDTO getVehicle(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            return null;
        }
        return convertionData.mapTo(vehicleRepository.findById(vehicleId).get(), VehicleDTO.class);
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return convertionData.mapTo(vehicleRepository.findAll(), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getVehicleByUserId(String userId) {
        return convertionData.mapTo(vehicleRepository.findAllByUserId(userId), VehicleDTO.class);
    }


}

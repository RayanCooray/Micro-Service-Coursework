package micro.gdse65.work.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import micro.gdse65.work.ticketservice.service.VehicleServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceClientImpl implements VehicleServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClientImpl.class);
    private final RestTemplate restTemplate;
    @Override
    public boolean isExitsVehicle(String id) {
        try {
            String url = "http://vehicle-service/api/v1/vehicle/vehicleExists/" + id;
            Boolean vehicleExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("Vehicle Exists: {}", vehicleExists);
            return vehicleExists != null && vehicleExists;
        } catch (Exception e) {
            logger.error("Error checking if vehicle exists", e);
        }
        return false;
    }

}

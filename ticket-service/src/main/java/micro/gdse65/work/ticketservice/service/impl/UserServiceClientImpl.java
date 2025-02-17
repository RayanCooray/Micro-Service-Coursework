package micro.gdse65.work.ticketservice.service.impl;

import lombok.RequiredArgsConstructor;
import micro.gdse65.work.ticketservice.service.UserServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceClientImpl implements UserServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClientImpl.class);
    private final RestTemplate restTemplate;
    @Override
    public boolean isExitsUser(String id) {
        try {
            String url = "http://user-service/api/v1/user/userExists/" + id;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("User Exists: {}", userExists);
            return userExists != null && userExists;
        } catch (Exception e) {
            logger.error("Error checking if user exists", e);
        }
        return false;
    }

}

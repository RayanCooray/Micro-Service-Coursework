package micro.gdse65.work.userservice.service.UserImpl;

import lombok.RequiredArgsConstructor;
import micro.gdse65.work.userservice.convertion.ConvertionData;
import micro.gdse65.work.userservice.dto.UserDTO;
import micro.gdse65.work.userservice.entity.User;
import micro.gdse65.work.userservice.repostory.UserRepo;
import micro.gdse65.work.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // Implement user-related CRUD operations here
    @Autowired
    private ConvertionData convertionData;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void saveUser(UserDTO userDTO) {
        userRepo.save(convertionData.mapTo(userDTO, User.class));

    }
    // Implement user-related operations here
}

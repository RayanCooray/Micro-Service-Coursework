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

import java.util.List;

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

    @Override
    public void updateUser(UserDTO userDTO) {
        if(!userRepo.existsById(userDTO.getUserId())){
            return;
        }
        userRepo.save(convertionData.mapTo(userDTO, User.class));
    }

    @Override
    public UserDTO getUser(String userId) {
        if (!userRepo.existsById(userId)) {
            return null;
        }
        return convertionData.mapTo(userRepo.findById(userId).get(), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return convertionData.mapTo(userRepo.findAll(), UserDTO.class);
    }

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public boolean isUserExists(String userId) {
        return userRepo.existsById(userId);
    }
    // Implement user-related operations here
}

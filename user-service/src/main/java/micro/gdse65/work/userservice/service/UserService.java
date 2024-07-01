package micro.gdse65.work.userservice.service;

import micro.gdse65.work.userservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    UserDTO getUser(String userId);
    List<UserDTO> getAllUsers();
    void deleteUser(String userId);
    boolean isUserExists(String userId);
}

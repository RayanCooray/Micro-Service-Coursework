package micro.gdse65.work.userservice.controller;

import micro.gdse65.work.userservice.dto.UserDTO;
import micro.gdse65.work.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class usercontroller {
    @Autowired
    private UserService USER_SERVICE;
    @RequestMapping("/get")
    public String getUser() {
        return "User Service";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        USER_SERVICE.saveUser(userDTO);
        return ResponseEntity.ok("User saved successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        USER_SERVICE.updateUser(userDTO);
        return ResponseEntity.ok("user updated successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable ("userId") String userId){
        return ResponseEntity.ok(USER_SERVICE.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(USER_SERVICE.getAllUsers());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable ("userId") String userId){
        USER_SERVICE.deleteUser(userId);
        return ResponseEntity.ok("user deleted successfully");
    }

    @GetMapping("/userExists/{userId}")
    public ResponseEntity<?> isUserExists(@PathVariable String userId) {
//        logger.info("Checking user existence with ID: {}", userId);
        try {
            boolean isUserExists = USER_SERVICE.isUserExists(userId);
//            logger.info("User Exists: {}", isUserExists);
            return ResponseEntity.ok(isUserExists);
        } catch (Exception exception) {
//            logger.error("Error checking user existence: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to check user existence.\nMore Details\n" + exception);
        }
    }
}

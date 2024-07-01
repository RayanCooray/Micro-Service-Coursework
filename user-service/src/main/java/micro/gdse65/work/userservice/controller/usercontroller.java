package micro.gdse65.work.userservice.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class usercontroller {
    @RequestMapping("/get")
    public String getUser() {
        return "User Service";
    }
}

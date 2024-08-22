package estudos.spring_secutiry_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String userPage() {
        return "Welcome, authenticated user!";
    }
}


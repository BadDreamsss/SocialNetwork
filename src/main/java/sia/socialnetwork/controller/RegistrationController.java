package sia.socialnetwork.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.socialnetwork.repository.UserRepository;
import sia.socialnetwork.utils.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm) {
        return "redirect:/login";
    }

}

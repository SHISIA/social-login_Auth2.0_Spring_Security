package dev.nebucodenezzar.sociallogin.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SocialController {
    @GetMapping("/")
    public String home(){
        return "Hello,Welcome home";
    }
    @GetMapping("/secured")
    public String secured(){
        return "Hello,Welcome to secured";
    }
}

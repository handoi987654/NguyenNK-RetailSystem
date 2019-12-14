package com.Desert.RestController;

import com.Desert.Payload.Login;
import com.Desert.Payload.Message;
import com.Desert.Service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody Login login, HttpSession session) {
        if (authenticationService.login(login.getUsername(), login.getPassword())) {
            session.setAttribute("username", login.getUsername());
            return ResponseEntity.ok(new Message("Logged In!"));
        }

        return ResponseEntity.badRequest().body(new Message("Login Failed!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<Message> logout(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return ResponseEntity.badRequest().body(new Message("You haven't logged in!"));
        }
        session.invalidate();
        return ResponseEntity.ok(new Message("Logged Out!"));
    }
}

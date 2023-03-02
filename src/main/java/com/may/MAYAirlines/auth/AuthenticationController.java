package com.may.MAYAirlines.auth;

import com.may.MAYAirlines.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody User)
}

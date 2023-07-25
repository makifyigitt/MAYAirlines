package com.may.MAYAirlines.auth;

import com.may.MAYAirlines.entity.User;
import com.may.MAYAirlines.repository.UserRepository;
import com.may.MAYAirlines.token.Token;
import com.may.MAYAirlines.token.TokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationUserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationUserService(UserRepository userRepository,
                                     JWTService jwtService,
                                     PasswordEncoder passwordEncoder,
                                     TokenRepository tokenRepository,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User(request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getSurname(),
                request.getEmail());
        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse(jwtToken);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(request.getUsername()));
        var token = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, token);
        return new AuthenticationResponse(token);
    }


    private void saveUserToken(User user, String jwtToken) {
        var token = new Token(jwtToken, false, false, user);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidUserTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

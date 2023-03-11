package com.may.MAYAirlines.auth;

import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.repository.CustomerRepository;
import com.may.MAYAirlines.token.Token;
import com.may.MAYAirlines.token.TokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(CustomerRepository customerRepository,
                                 JWTService jwtService,
                                 PasswordEncoder passwordEncoder,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var customer = new Customer(request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getSurname(),
                request.getEmail());
        var savedCustomer = customerRepository.save(customer);
        var jwtToken = jwtService.generateToken(customer);
        saveCustomerToken(savedCustomer, jwtToken);
        return new AuthenticationResponse(jwtToken);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var customer = customerRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(request.getUsername()));
        var token = jwtService.generateToken(customer);
        revokeAllCustomerTokens(customer);
        saveCustomerToken(customer, token);
        return new AuthenticationResponse(token);
    }



    private void saveCustomerToken(Customer customer,
                                   String jwtToken) {
        var token = new Token(jwtToken,false,false,customer);
        tokenRepository.save(token);
    }

    private void revokeAllCustomerTokens(Customer customer) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(customer.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

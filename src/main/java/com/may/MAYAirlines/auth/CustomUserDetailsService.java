package com.may.MAYAirlines.auth;

import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.repository.CustomerRepository;
import com.may.MAYAirlines.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Customer customer = customerRepository.findByUsername(username).orElse(null);
        if (customer == null) {
            return userRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
        }
        return customer;
    }
}

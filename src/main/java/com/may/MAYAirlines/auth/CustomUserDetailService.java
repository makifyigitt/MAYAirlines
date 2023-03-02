package com.may.MAYAirlines.auth;

import com.may.MAYAirlines.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public CustomUserDetailService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//TODO user içinde yazmak gerekecek, user ekleyince bu kısmı güncelle.
        if (customerRepository.findByUsername(username).isPresent()){
            return customerRepository.findByUsername(username).get();
        }
        else{
            throw new UsernameNotFoundException(username);
        }

    }
}

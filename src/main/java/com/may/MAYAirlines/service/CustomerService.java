package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.CustomerNotFoundException;
import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.core.exception.UsernameExistException;
import com.may.MAYAirlines.dto.CustomerCreateDTO;
import com.may.MAYAirlines.dto.CustomerDTO;
import com.may.MAYAirlines.dto.CustomerUpdateDTO;
import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }
    protected Customer findById(int id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND));
    }

    public CustomerDTO getById(int id) {
        return new CustomerDTO(findById(id));
    }


    public List<CustomerDTO> getAllActiveCustomers() {
        return customerRepository.getAllActiveCustomers()
                .stream()
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getUsername(),
                        customer.getFirstName(),
                        customer.getSurname(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        customer.getAddress(),
                        customer.isStatus(),
                        customer.getReservationList(),
                        customer.getCreateDate(),
                        customer.getUpdateDate()
                ))
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getAllInactiveCustomers() {
        return customerRepository.getAllInactiveCustomers()
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public void create(CustomerCreateDTO from) {
        if (customerRepository.findByUsername(from.getUsername()).isPresent())
            throw new UsernameExistException(ErrorCode.USERNAME_IS_USED);
        else{
            Customer newCustomer = new Customer();
            newCustomer.setUsername(from.getUsername());
            newCustomer.setPassword(from.getPassword());
            newCustomer.setFirstName(from.getFirstName());
            newCustomer.setSurname(from.getSurname());
            newCustomer.setEmail(from.getEmail());
            newCustomer.setPhoneNumber(from.getPhoneNumber());
            newCustomer.setAddress(from.getAddress());
            customerRepository.save(newCustomer);
        }
    }

    public void update(CustomerUpdateDTO from) {
        Customer customer = findById(from.getId());
        customer.setFirstName(from.getFirstName());
        customer.setSurname(from.getSurname());
        customer.setEmail(from.getEmail());
        customer.setPhoneNumber(from.getPhoneNumber());
        customer.setAddress(from.getAddress());
        customer.setUpdateDate(new Date());
        customerRepository.save(customer);
    }

    public void makeActive(int id) {
        Customer customer = findById(id);
        customer.setStatus(true);
        customerRepository.save(customer);
    }

    public void inactive(int id) {
        Customer customer = findById(id);
        customer.setStatus(false);
        customerRepository.save(customer);
    }
}

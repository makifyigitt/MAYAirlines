package com.may.MAYAirlines.service;

import com.may.MAYAirlines.dto.CustomerDTO;
import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void testGetAllCustomers_shouldReturnCustomerDTOList() {
        Customer customer =new Customer("makifyigit","akif123456789","Mehmet Akif","Yiğit");
        Customer customer1 =new Customer("talhasertokat","talha123456789","Talha","Serttokat");
        Customer customer2 =new Customer("username","password123456789","first name","surname");
        List<Customer> customerList = List.of(customer,customer1,customer2);
        List<CustomerDTO> customerDTOList = customerList.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());


        Mockito.when(customerRepository
                .findAll()
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList()))
                .thenReturn(customerDTOList);

        List<CustomerDTO> result = customerService.getAllCustomers();

        assertEquals(result,customerDTOList);

    }

    @Test
    void testFindById_whenCustomerIdDoesExist_shouldReturnCustomerDTO() {
        Customer customer =new Customer("makifyigit","akif123456789","Mehmet Akif","Yiğit");
    }

    @Test
    void getById() {
    }

    @Test
    void getAllActiveCustomers() {
    }

    @Test
    void getAllInactiveCustomers() {
    }
}
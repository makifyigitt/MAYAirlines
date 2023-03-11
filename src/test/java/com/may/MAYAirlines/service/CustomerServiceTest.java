package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.CustomerNotFoundException;
import com.may.MAYAirlines.dto.CustomerDTO;
import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        customerService = new CustomerService(customerRepository, passwordEncoder);
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


        Mockito.when(customerRepository.findAll())
                .thenReturn(customerList);

        List<CustomerDTO> result = customerService.getAllCustomers();

        Mockito.verify(customerRepository).findAll();
        assertEquals(result,customerDTOList);

    }
    @Test
    void testFindById_whenCustomerIdDoesExist_shouldReturnCustomer() {
        Customer customer =new Customer(111,"makifyigit","akif123456789","Mehmet Akif","Yiğit");

        Mockito.when(customerRepository.findById(111)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(111);

        Mockito.verify(customerRepository).findById(111);
        assertEquals(result,customer);
    }

    @Test
    void testFindById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById(111)).thenReturn(Optional.empty());

       assertThrows(CustomerNotFoundException.class,()->customerService.findById(111));
       Mockito.verify(customerRepository).findById(111);
    }

    @Test
    void testGetById_whenCustomerIdDoesExist_shouldReturnCustomerDTO() {
        Customer customer =new Customer(111,"makifyigit","akif123456789","Mehmet Akif","Yiğit");
        CustomerDTO customerDTO = new CustomerDTO(customer);
        Mockito.when(customerRepository.findById(111)).thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getById(111);

        Mockito.verify(customerRepository).findById(111);
        assertEquals(result,customerDTO);

    }
    @Test
    void testGetById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,()->customerService.getById(111));
        Mockito.verify(customerRepository).findById(111);
    }


    @Test
    void getAllActiveCustomers_shouldReturnCustomerDTOList() {
        Customer customer =new Customer("makifyigit","akif123456789","Mehmet Akif","Yiğit");
        Customer customer1 =new Customer("talhasertokat","talha123456789","Talha","Serttokat");
        Customer customer2 =new Customer("username","password123456789","first name","surname");
        Customer customer3 =new Customer("username1","password1234567891","first name1","surname1");
        customer2.setStatus(false);
        customer3.setStatus(false);

        List<Customer> activeCustomers = List.of(customer,customer1);

        List<CustomerDTO> customerDTOList = activeCustomers.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());


        Mockito.when(customerRepository.getAllActiveCustomers())
                .thenReturn(activeCustomers);

        List<CustomerDTO> result = customerService.getAllActiveCustomers();

        Mockito.verify(customerRepository).getAllActiveCustomers();
        assertEquals(result,customerDTOList);

    }

    @Test
    void getAllInactiveCustomers_shouldReturnCustomerDTOList() {
        Customer customer =new Customer("makifyigit","akif123456789","Mehmet Akif","Yiğit");
        Customer customer1 =new Customer("talhasertokat","talha123456789","Talha","Serttokat");
        Customer customer2 =new Customer("username","password123456789","first name","surname");
        Customer customer3 =new Customer("username1","password1234567891","first name1","surname1");
        customer2.setStatus(false);
        customer3.setStatus(false);

        List<Customer> inactiveCustomers = List.of(customer2,customer3);

        List<CustomerDTO> customerDTOList = inactiveCustomers.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());


        Mockito.when(customerRepository.getAllInactiveCustomers())
                .thenReturn(inactiveCustomers);

        List<CustomerDTO> result = customerService.getAllInactiveCustomers();

        Mockito.verify(customerRepository).getAllInactiveCustomers();
        assertEquals(result,customerDTOList);

    }
}
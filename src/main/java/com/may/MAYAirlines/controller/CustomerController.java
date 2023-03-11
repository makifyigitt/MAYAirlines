package com.may.MAYAirlines.controller;



import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.CustomerCreateDTO;
import com.may.MAYAirlines.dto.CustomerDTO;
import com.may.MAYAirlines.dto.CustomerUpdateDTO;
import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CustomerDTO>> getAll(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping()
    public ResponseEntity<CustomerDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping("/activecustomers")
    public ResponseEntity<List<CustomerDTO>> getAllActiveCustomers(){
        return ResponseEntity.ok(customerService.getAllActiveCustomers());
    }

    @GetMapping("/inactivecustomers")
    public ResponseEntity<List<CustomerDTO>> getAllInactiveCustomers(){
        return ResponseEntity.ok(customerService.getAllInactiveCustomers());
    }

    @PostMapping("/create")
    public BaseResponse createCustomer(@RequestBody @Valid CustomerCreateDTO from){
        customerService.create(from);
        return new BaseResponse(Response.CREATE);
    }


    @PutMapping("/update")
    public BaseResponse updateCustomer(@RequestBody  @Valid CustomerUpdateDTO from){
        customerService.update(from);
        return new BaseResponse(Response.UPDATE);
    }


    //TODO save methodunu geliştir.

    @PatchMapping("/active")
    public BaseResponse makeActive(@RequestParam int id){
        customerService.makeActive(id);
        return new BaseResponse(Response.ACTIVE);
    }

    @DeleteMapping("/delete")
    public BaseResponse inactiveCustomer(@RequestParam int id){
        customerService.inactive(id);
        return new BaseResponse(Response.DELETE);
    }

    @PatchMapping("/passwordChange")
    public BaseResponse changePassword(@RequestParam int id,
                                       @RequestParam String oldPassword,
                                       @RequestParam String newPassword){
        customerService.changePassword(id,oldPassword,newPassword);
        return new BaseResponse(Response.UPDATE);
    }
}

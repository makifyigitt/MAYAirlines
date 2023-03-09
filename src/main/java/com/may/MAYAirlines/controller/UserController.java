package com.may.MAYAirlines.controller;

import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.UserCreateDTO;
import com.may.MAYAirlines.dto.UserDTO;
import com.may.MAYAirlines.dto.UserUpdateDTO;
import com.may.MAYAirlines.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping()
    public ResponseEntity<UserDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/activecustomers")
    public ResponseEntity<List<UserDTO>> getAllActiveUsers(){
        return ResponseEntity.ok(userService.getAllActiveUsers());
    }

    @GetMapping("/inactivecustomers")
    public ResponseEntity<List<UserDTO>> getAllInactiveUsers(){
        return ResponseEntity.ok(userService.getAllInactiveUsers());
    }

    @PostMapping("/create")
    public BaseResponse createUsers(@RequestBody @Valid UserCreateDTO from){
        userService.create(from);
        return new BaseResponse(Response.CREATE);
    }


    @PutMapping("/update")
    public BaseResponse updateUsers(@RequestBody  @Valid UserUpdateDTO from){
        userService.update(from);
        return new BaseResponse(Response.UPDATE);
    }


    @PatchMapping("/active")
    public BaseResponse makeActive(@RequestParam int id){
        userService.makeActive(id);
        return new BaseResponse(Response.ACTIVE);
    }

    @DeleteMapping("/delete")
    public BaseResponse makeInactive(@RequestParam int id){
        userService.inactive(id);
        return new BaseResponse(Response.DELETE);
    }
}

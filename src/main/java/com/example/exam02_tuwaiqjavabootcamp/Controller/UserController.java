package com.example.exam02_tuwaiqjavabootcamp.Controller;

import com.example.exam02_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.exam02_tuwaiqjavabootcamp.Model.User;
import com.example.exam02_tuwaiqjavabootcamp.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAll() {
        if (userService.getAllUsers().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("no users"));
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (userService.addUser(user))
            return ResponseEntity.status(200).body(new ApiResponse("new user added"));
        return ResponseEntity.status(400).body(new ApiResponse("already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id,@RequestBody@Valid User user,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (userService.updateUser(id, user)) {
            return ResponseEntity.status(200).body(new ApiResponse("user updated!!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found or duplicated."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("user is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @GetMapping("/get-balance/{balance}")
    public ResponseEntity searchBalance(@PathVariable int balance){
        if (userService.searchBalance(balance).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("no user with that balance"));
        return ResponseEntity.status(200).body(userService.searchBalance(balance));
    }

    @GetMapping("/get-age/{age}")
    public ResponseEntity searchAge(@PathVariable int age){
        if (userService.searchAge(age).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("no user with that age"));
        return ResponseEntity.status(200).body(userService.searchAge(age));
    }


}

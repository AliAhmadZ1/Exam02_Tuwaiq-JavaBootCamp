package com.example.exam02_tuwaiqjavabootcamp.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    //- User Class :
    // ID , name , age , balance , role
    // 1. all should not be empty
    // 2. balance should be a valid number
    // 3. The role is customer OR librarian

    @NotEmpty(message = "cannot be empty")
    @Size(min = 4,max = 8,message = "Should be between 4 and 8.")
    private String id;
    @NotEmpty(message = "cannot be empty")
    @Size(min = 4,max = 16,message = "name should be between 4 and 16.")
    @Pattern(regexp = "^([a-z]|[A-Z])+$",message = "name should be letters only.")
    private String name;
    @NotNull(message = "cannot be null")
    @Min(value = 8,message = "the age must be at least 8 years old")
    private int age;
    @NotNull
    @PositiveOrZero(message = "balance cannot be negative")
    private int balance;
    @NotEmpty(message = "cannot be empty")
    @Pattern(regexp = "^(librarian|customer)$",message = "role should be librarian or customer..")
    private String role;

}

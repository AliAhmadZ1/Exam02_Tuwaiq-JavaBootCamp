package com.example.exam02_tuwaiqjavabootcamp.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Book {

    //- Book Class :
    // ID , name , number_of_pages , price , category , isAvailable
    //1. all should not be empty
    //2. category is novel OR academic

    @NotEmpty(message = "cannot be empty")
    @Size(min = 4,max = 8,message = "Should be between 4 and 8.")
    private String id;
    @NotEmpty(message = "cannot be empty")
    @Size(min = 4,max = 16,message = "name should be between 4 and 16.")
    @Pattern(regexp = "^([a-z]|[A-Z])+$",message = "name should be letters only.")
    private String name;
    @NotNull(message = "cannot be null")
    @Positive(message = "number of pages should be positive and greater than zero")
    private int number_of_pages;
    @NotNull(message = "cannot be null")
    @PositiveOrZero(message = "the price shouldn't be negative..")
    private double price;
    @NotEmpty(message = "cannot be empty")
    @Pattern(regexp = "^(novel|academic)$",message = "we have only novel and academic books.")
    private String category;
    @AssertFalse(message = "The book didn't published, should librarian add it")
    private boolean isAvailable;


}

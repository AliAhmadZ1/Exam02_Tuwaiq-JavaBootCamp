package com.example.exam02_tuwaiqjavabootcamp.Controller;


import com.example.exam02_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.exam02_tuwaiqjavabootcamp.Model.Book;
import com.example.exam02_tuwaiqjavabootcamp.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/get")
    public ResponseEntity getAllBooks(){
        if (bookService.getAllBooks().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("book is empty"));
        return ResponseEntity.status(200).body(bookService.getAllBooks());
    }

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody@Valid Book book, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (bookService.addBook(book)) {
            bookService.addBook(book);
            return ResponseEntity.status(200).body(new ApiResponse("new book is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("book is already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id,@RequestBody@Valid Book book,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (bookService.updateBook(id, book)){
            return ResponseEntity.status(200).body(new ApiResponse("the book is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("book not found or duplicated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        if (bookService.deleteBook(id))
            return ResponseEntity.status(200).body(new ApiResponse("book is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @GetMapping("/get-by-name/{bookName}")
    public ResponseEntity search_name(@PathVariable String bookName){
        if (bookService.search_name(bookName) == null){
            return ResponseEntity.status(400).body(new ApiResponse("there are no book with "+bookName+" name."));
        }
        return ResponseEntity.status(200).body(bookService.search_name(bookName));
    }

    @GetMapping("/get-by-category/{category}")
    public ResponseEntity search_category(@PathVariable String category){
        if (bookService.search_category(category).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no book with this category"));
        return ResponseEntity.status(200).body(bookService.search_category(category));
    }

    @GetMapping("/get-by-numberof/{numberOfPages}")
    public ResponseEntity search_number_of_pages(@PathVariable int numberOfPages){
        if (bookService.searchNumberOfPages(numberOfPages).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no books with that number of pages"));
        return ResponseEntity.status(200).body(bookService.searchNumberOfPages(numberOfPages));
    }

    @PutMapping("/change/{userId},{bookId}")
    public ResponseEntity changeBookState(@PathVariable String userId,@PathVariable String bookId){
        if (bookService.changeBookState(userId, bookId))
            return ResponseEntity.status(200).body(new ApiResponse("Book state is changed"));
        return ResponseEntity.status(400).body(new ApiResponse("book is not exist or doesn't changed"));
    }


}

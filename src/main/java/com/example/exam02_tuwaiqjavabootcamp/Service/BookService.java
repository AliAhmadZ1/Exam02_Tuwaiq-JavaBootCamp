package com.example.exam02_tuwaiqjavabootcamp.Service;


import com.example.exam02_tuwaiqjavabootcamp.Model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BookService {


    private final UserService userService;
    ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getAllBooks(){
        return books;
    }

    public boolean addBook(Book book){
        for (Book b: books){
            if (b.getId().equals(book.getId()))
                return false;
        }
        books.add(book);
        return true;
    }

    public boolean updateBook(String id, Book book){
        for (Book b: books){
            if (b.getId().equals(id)){
                books.set(books.indexOf(b),book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String id){
        for (Book b: books){
            if (b.getId().equals(id)){
                books.remove(b);
                return true;
            }
        }
        return false;
    }

    //11. Create an endpoint that takes a Book name and then returns one Book.
    public Book search_name(String book_name){
        for (Book b: books){
            if (b.getName().equals(book_name)){
                return b;
            }
        }
        return null;
    }

    //12.Create an endpoint that takes a category then return all books that have
    //this category.
    public ArrayList<Book> search_category(String category){

        ArrayList<Book> categoryList= new ArrayList<>();
        for (Book b:books){
            if (b.getCategory().equals(category)){
                categoryList.add(b);
            }
        }
        return categoryList;
    }

    //13.Create an endpoint that takes a number of pages and returns all Books
    //with this number of pages or above.
    public ArrayList<Book> searchNumberOfPages(int numberOfPages){
        ArrayList<Book> numberOfPagesList = new ArrayList<>();

        for (Book b:books){
            if (b.getNumber_of_pages()>=numberOfPages){
                numberOfPagesList.add(b);
            }
        }
        return numberOfPagesList;
    }

    //14.Create an endpoint that changes a book's status to unavailable (Only the
    //librarian can change the status of the book)
//    private final UserService userService2;
    public boolean changeBookState(String userId,String bookId){
        for (Book b:books) {
            if (b.getId().equals(bookId)) {
                if (userService.librarianRole(userId)) {
                    b.setAvailable(true);
                    return true;
                }
            }
        }
        return false;
    }

}

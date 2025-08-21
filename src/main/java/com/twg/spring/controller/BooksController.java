package com.twg.spring.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.twg.spring.entity.Books;
import com.twg.spring.service.BooksService;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping
    public List<Books> getAllBooks() 
    {
        return booksService.getAllBooks();
    }

    @GetMapping("/{id}")
    
    public Books getBookById(@PathVariable int id) 
    {
        return booksService.getBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Books addBook(@RequestBody Books books) {
        return booksService.addBook(books);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Books updateBook(@PathVariable int id, @RequestBody Books books) {
        return booksService.updateBook(id, books);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable int id) {
        booksService.deleteBook(id);
    }
}



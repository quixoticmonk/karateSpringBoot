package com.oblivion.KarateDemo.Controller;

import com.oblivion.KarateDemo.Repository.BookRepository;
import com.oblivion.KarateDemo.Service.BookService;
import com.oblivion.KarateDemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    @GetMapping
    public List<Book> findAll() {
        return bookService.getAllBooks();
    }

    @RequestMapping("books/{name}")
    @GetMapping
    public Book getABook(@PathVariable("name") String name) {
        return bookService.getABook(name);
    }

}
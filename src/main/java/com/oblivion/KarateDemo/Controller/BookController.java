package com.oblivion.KarateDemo.Controller;

import com.oblivion.KarateDemo.Service.BookService;
import com.oblivion.KarateDemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    public List<Book> findAll() {
        return bookService.getAllBooks();
    }

    @RequestMapping("books/{name}")
    public Book getABook(@PathVariable("name") String name) {
        return bookService.getABook(name);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/books")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/books/{name}")
    public void updateBook(@RequestBody Book book, @PathVariable("name") String name){
        bookService.updateBook(book,name);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/books/{name}")
    public void deleteBook(@PathVariable("name") String name){
        bookService.deleteBook(name);
    }


}
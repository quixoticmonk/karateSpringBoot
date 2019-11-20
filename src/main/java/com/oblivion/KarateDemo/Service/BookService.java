package com.oblivion.KarateDemo.Service;

import com.oblivion.KarateDemo.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private List<Book> books = Arrays.asList(
        new Book("Angels and Demons", "Dan Brown"),
        new Book("Inferno", "Dan Brown"),
        new Book("Harry Potter and the Sorcerer's Stone (Book 1)", "J. K. Rowling"),
        new Book("Harry Potter and the Prisoner of Azkaban", "J. K. Rowling"),
        new Book("The Hobbit", "J. R. R. Tolkien"),
        new Book("1984", "George Orwell"),
        new Book("Pride and Prejudice ", "Jane Austen"),
        new Book("To Kill a Mockingbird", "Harper Lee")
    );


    public List<Book> getAllBooks(){
        return books;
    }

    public Book getABook(String name) {
        return books.stream().filter(t->t.getName().equals(name)).findFirst().get();
    }
}

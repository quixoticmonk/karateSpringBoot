package com.oblivion.KarateDemo.Service;

import com.oblivion.KarateDemo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>(Arrays.asList(
        new Book("Angels and Demons", "Dan Brown"),
        new Book("Inferno", "Dan Brown"),
        new Book("Harry Potter and the Sorcerer's Stone (Book 1)", "J. K. Rowling"),
        new Book("Harry Potter and the Prisoner of Azkaban", "J. K. Rowling"),
        new Book("The Hobbit", "J. R. R. Tolkien"),
        new Book("1984", "George Orwell"),
        new Book("Pride and Prejudice ", "Jane Austen"),
        new Book("To Kill a Mockingbird", "Harper Lee")
    ));


    public List<Book> getAllBooks(){
        return books;
    }

    public Book getABook(String name) {
        return
                books.stream()
                        .filter(t-> name.equals(t.getName()))
                        .findFirst().get();
    }

    public void addBook(Book book) {
            books.add(book);
    }

    public void updateBook(Book book, String name) {
        for(int i=0; i<books.size();i++){
            Book b = books.get(i);
            if(b.getName().equals(book.getName())){
                books.set(i,book);
                return;
            }
        }
    }

    public void deleteBook(String name) {
        books.removeIf(b->b.getName().equals(name));
    }
}

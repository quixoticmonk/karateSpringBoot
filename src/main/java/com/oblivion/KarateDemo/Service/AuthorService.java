package com.oblivion.KarateDemo.Service;

import com.oblivion.KarateDemo.model.Author;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {
    private List<Author> authors = Arrays.asList(
            new Author("Dan Brown"),
            new Author("J. K. Rowling"),
            new Author("J. R. R. Tolkien"),
            new Author("George Orwell"),
            new Author("Jane Austen"),
            new Author("Harper Lee")
    );

    public List<Author> findAuthors() {
        return authors;
    }
}

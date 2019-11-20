package com.oblivion.KarateDemo.Controller;

import com.oblivion.KarateDemo.Service.AuthorService;
import com.oblivion.KarateDemo.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public List<Author> findAuthors() {
        return authorService.findAuthors();
    }

}

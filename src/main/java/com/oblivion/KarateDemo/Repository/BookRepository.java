package com.oblivion.KarateDemo.Repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository{

   public List<String> findAuthors() {
        List<String> authorList = new ArrayList<>();
        authorList.add("Dan Brown");
        authorList.add("George Orwell");
        authorList.add("J. K. Rowling");
        authorList.add("Jane Austen");
        authorList.add("Harper Lee");
        return authorList;
    }
}
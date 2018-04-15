package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    private LibService libService;

    @RequestMapping("/addBook")
    public String addBook(Model model) {
        return "addBook";
    }

    @RequestMapping(value = "/create-book", method = RequestMethod.POST)
    public String createBook(@RequestParam("title") String title, @RequestParam("year") int year,
                             @RequestParam("available") int available) {
        long book_id = libService.createBook(new Book(null, title, year, available));
        return book_id >= 1 ? "index" : "bookCreationFailure";
    }

}

package com.epam.lab.library.web.controller;

import com.epam.lab.library.config.LibApplication;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.LibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private LibService libService;

    @RequestMapping("/addBook")
    public String addBook(Model model) {
        return "addBook";
    }

    @RequestMapping(value = "/create-book", method = RequestMethod.POST)
    public String createBook(@RequestParam("title") String title, @RequestParam("year") int year,
                             @RequestParam("available") int available) {
        Book book = libService.addBook(new Book( title, year, available));
        LOG.info("Add book: " + book.toString());
        return book.getId() == null ? "bookCreationFailure" : "redirect:/index";
    }

}

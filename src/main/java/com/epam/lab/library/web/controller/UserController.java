package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private LibService libService;

    @RequestMapping(value={"/","/index"})
    public String defaultPage (Model model){
        return "index";
    }

    @RequestMapping("/books")
    public String viewBooks(Model model){
        List<Book> books = libService.getAllBooks();
        model.addAttribute("books",books);
        return "book-list";
    }



}
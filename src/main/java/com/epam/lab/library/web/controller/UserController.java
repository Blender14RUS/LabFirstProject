package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.Order;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.epam.lab.library.domain.Status.GIVEN;
import static com.epam.lab.library.domain.Status.IN_LIBRARY;
import static com.epam.lab.library.domain.Status.REQUESTED;

@Controller
public class UserController {

    @Autowired
    private LibService libService;

    @RequestMapping(value = {"/", "/index"})
    public String defaultPage(Model model) {
        return "index";
    }

    @RequestMapping("/requests")
    public String librarianRequestsForBooksIssue(Model model) {
        List<Order> orders = libService.getAllOrderByStatus(REQUESTED);
        model.addAttribute("orders", orders);
        return "librarianRequestsForBooksIssue";
    }

    @RequestMapping("/given-books")
    public String librarianGivenBooks(Model model) {
        List<Order> orders = libService.getAllOrderByStatus(GIVEN);
        model.addAttribute("orders", orders);
        return "librarianGivenBooks";
    }

    @RequestMapping(value = "/give/{id}", method = RequestMethod.POST)
    public String giveBook(@PathVariable("id") Long id) {
        libService.setBookStatus(GIVEN, id);
        return "redirect:/requests";
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.POST)
    public String returnBook(@PathVariable("id") Long id) {
        libService.setBookStatus(IN_LIBRARY, id);
        return "redirect:/given-books";
    }
}

package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.Order;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        List<Order> orders = libService.getAllOrderRequests();
        model.addAttribute("orders", orders);
        return "librarianRequestsForBooksIssue";
    }

    @RequestMapping("/given-books")
    public String librarianGivenBooks(Model model) {
        List<Order> orders = libService.getAllOrderGiven();
        model.addAttribute("orders", orders);
        return "librarianGivenBooks";
    }
}

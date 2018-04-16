package com.epam.lab.library.web.controller;


import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.epam.lab.library.domain.Status.*;

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
    @RequestMapping("/admin")
    public String showAll (Model model){
        List<User> users = libService.getAllUsers();
        model.addAttribute("users",users);
        return  "adminBoard";
    }

    @RequestMapping("/registration")
    public String register (Model model){
        return "registration";
    }

    @RequestMapping(value="/user/create", method = RequestMethod.POST )
    public  String createUser(@RequestParam("login") String login,
                              @RequestParam("name") String name){
        int insertedRowsCount = libService.createUser(new User(null, login, name));
        return insertedRowsCount==1 ? "redirect:/admin" : "creationFailure";
    }

    @RequestMapping(value="/user/delete/{id}",method = RequestMethod.POST)
    public String deleteUser(@PathVariable ("id") long id){
        libService.deleteUserById(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/give/{id}", method = RequestMethod.POST)
    public String giveBook(@PathVariable("id") Long id) {
        libService.setBookStatus(GIVEN, id);
        return "redirect:/requests";
    }
    @RequestMapping(value = "/user/update-access/{id}/{accessLevel}",method = RequestMethod.POST)
    public String updateUserAccessLevel (@PathVariable("id") Long id,
                                         @PathVariable("accessLevel") AccessLevel accessLevel){
        libService.updateUserAccessLevel(id, accessLevel);
        return "redirect:/admin";
    }

    @RequestMapping ("/profile")
    public String userProfile (Model model){
        User user = libService.getUser(1L);
        model.addAttribute(user);
        return "UserProfile";
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.POST)
    public String returnBook(@PathVariable("id") Long id) {
        libService.setBookStatus(IN_LIBRARY, id);
        return "redirect:/given-books";
    }
}


package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.epam.lab.library.domain.Status.GIVEN;
import static com.epam.lab.library.domain.Status.REQUESTED;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String defaultPage(Model model) {
        return "common/index";
    }

    @RequestMapping("/requested-books")
    public String librarianRequestsForBooksIssue(Model model) {
        List<Order> orders = userService.getAllOrderByStatus(REQUESTED);
        model.addAttribute("orders", orders);
        return "librarian/requestedBooks";
    }

    @RequestMapping("/returned-books")
    public String librarianGivenBooks(Model model) {
        List<Order> orders = userService.getAllOrderByStatus(GIVEN);
        model.addAttribute("orders", orders);
        return "librarian/returnedBooks";
    }

    @RequestMapping("/admin")
    public String showAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/adminBoard";
    }

    @RequestMapping("/registration")
    public String register(Model model) {
        return "user/registration";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String createUser(@RequestParam("login") String login,
                             @RequestParam("name") String name) {
        int insertedRowsCount = userService.createUser(new User(null, login, name));
        return insertedRowsCount == 1 ? "redirect:/admin" : "creationFailure";
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/user/update-access/{id}/{accessLevel}", method = RequestMethod.POST)
    public String updateUserAccessLevel(@PathVariable("id") Long id,
                                        @PathVariable("accessLevel") AccessLevel accessLevel) {
        userService.updateUserAccessLevel(id, accessLevel);
        return "redirect:/admin";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model) {
        User user = userService.getUser(1L);
        model.addAttribute(user);
        return "user/UserProfile";
    }

    @RequestMapping(value = "user/orders/{id}", method = RequestMethod.POST)
    public String userOrders (@PathVariable("id") Long id, Model model) {
        List<Order> orders = userService.getAllUserOrders(id);
        model.addAttribute("orders", orders);
        return "user/userOrders";
    }

}

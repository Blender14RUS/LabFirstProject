package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.UserService;
import com.epam.lab.library.service.impl.DataBaseUserDetailService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import com.epam.lab.library.service.impl.DataBaseUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.lab.library.domain.Status.GIVEN;
import static com.epam.lab.library.domain.Status.REQUESTED;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private final DataBaseUserDetailService detailsService;

    public UserController(UserService userService, DataBaseUserDetailService detailsService) {
        this.userService = userService;
        this.detailsService = detailsService;
    }

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

    @RequestMapping("/admin/board")
    public String showAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/adminBoard";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        return "common/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createUser(Model model, @RequestParam("login") String login,
                             @RequestParam("password") String pass,
                             @RequestParam("confirm-password") String confPass,
                             @RequestParam("name") String name) {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        if (pass.equals(confPass)) {
            user.setPass(pass);
            if(userService.createUser(user)){
                LOG.info("User has been created: " + login + " " + pass);
                model.addAttribute("user-created", 1);
                return "redirect: /login";
            }
            else{
                model.addAttribute("error-create", "err");
            }
        } else {
            model.addAttribute("error-password", "erro");
        }
        model.addAttribute("user", user);
        model.addAttribute("message", "hi there");
        return "redirect: common/registration";
    }

    @RequestMapping(value = "admin/delete-user/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/board";
    }

    @RequestMapping(value = "admin/update-access/{id}/{accessLevel}", method = RequestMethod.POST)
    public String updateUserAccessLevel(@PathVariable("id") Long id,
                                        @PathVariable("accessLevel") AccessLevel accessLevel) {
        userService.updateUserAccessLevel(id, accessLevel);
        return "redirect:/admin/board";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model) {
        User user = userService.getUserByLogin(detailsService.getCurrentUsername());
        model.addAttribute(user);
        return "user/UserProfile";
    }

    @RequestMapping("user/orders/{id}")
    public String userOrders(@PathVariable("id") Long id, Model model) {
        List<Order> orders = userService.getAllUserOrders(id);
        model.addAttribute("orders", orders);
        return "user/userOrders";
    }

}

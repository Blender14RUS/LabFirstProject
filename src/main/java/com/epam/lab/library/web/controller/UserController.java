package com.epam.lab.library.web.controller;

import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.UserService;
import com.epam.lab.library.service.impl.DataBaseUserDetailService;
import com.epam.lab.library.service.impl.LocalizationController;
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

    @Autowired
    private final DataBaseUserDetailService detailsService;

    public UserController(UserService userService, DataBaseUserDetailService detailsService) {
        this.userService = userService;
        this.detailsService = detailsService;
    }

    @RequestMapping(value = {"/", "/index"})
    public String defaultPage(Model model,
                              @RequestParam(value = "lang", defaultValue = "en_US") String language,
                              @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed
                              ){
        if(LocalizationController.getLang().equals(language)) {
            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "common/index";
    }


    @RequestMapping("/requested-books")
    public String librarianRequestsForBooksIssue(Model model,
                                                 @RequestParam(value = "lang", defaultValue = "en_US") String language,
                                                 @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        List<Order> orders = userService.getAllOrderByStatus(REQUESTED);
        model.addAttribute("orders", orders);
        if(LocalizationController.getLang().equals(language)) {
            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "librarian/requestedBooks";
    }

    @RequestMapping("/returned-books")
    public String librarianGivenBooks(Model model,
                                      @RequestParam(value = "lang", defaultValue = "en_US") String language,
                                      @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        List<Order> orders = userService.getAllOrderByStatus(GIVEN);
        model.addAttribute("orders", orders);
        if(LocalizationController.getLang().equals(language)) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "librarian/returnedBooks";
    }

    @RequestMapping("/admin/board")
    public String showAll(Model model,
                          @RequestParam(value = "lang", defaultValue = "en_US") String language,
                          @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        if(lang_changed)
            LocalizationController.setLang(language);
        model.addAttribute("language", LocalizationController.getLang());
        return "admin/adminBoard";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model,
                           @RequestParam(value = "lang", defaultValue = "en_US") String language,
                           @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        if(lang_changed)
            LocalizationController.setLang(language);
        model.addAttribute("language", LocalizationController.getLang());
        return "common/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST
    )
    public String createUser(Model model, @RequestParam("login") String login,
                             @RequestParam("password") String pass,
                             @RequestParam("confirm-password") String confPass,
                             @RequestParam("name") String name,
                             @RequestParam(value = "lang", defaultValue = "en_US") String language,
                             @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        User user = new User();
        user.setName(name);
        if (pass.equals(confPass)) {
            user.setPass(pass);
            if (userService.createUser(user)) {
                LOG.info("User has been created: " + login + " " + pass);
                model.addAttribute("user-created", 1);
                if(lang_changed)
                    LocalizationController.setLang(language);
                model.addAttribute("language", LocalizationController.getLang());
                return "redirect: /login";
            } else {
                model.addAttribute("error-create", "err");
            }
        } else {
            model.addAttribute("error-password", "erro");
        }
        if(lang_changed)
            LocalizationController.setLang(language);
        model.addAttribute("language", LocalizationController.getLang());
        model.addAttribute("user", user);
        model.addAttribute("message", "hi there");
        return "redirect: common/registration";
    }

    @RequestMapping(value = "admin/delete-user/{id}", method = RequestMethod.POST)
    public String deleteUser(Model model,
                             @PathVariable("id") long id,
                             @RequestParam(value = "lang", defaultValue = "en_US") String language,
                             @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        userService.deleteUserById(id);
        if(LocalizationController.getLang().equals(language)) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "redirect:/admin/board";
    }

    @RequestMapping(value = "admin/update-access/{id}/{accessLevel}", method = RequestMethod.POST)
    public String updateUserAccessLevel(Model model,
                                        @PathVariable("id") Long id,
                                        @PathVariable("accessLevel") AccessLevel accessLevel,
                                        @RequestParam(value = "lang", defaultValue = "en_US") String language,
                                        @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        userService.updateUserAccessLevel(id, accessLevel);
        if(LocalizationController.getLang().equals(language)) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "redirect:/admin/board";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model,
                              @RequestParam(value = "lang", defaultValue = "en_US") String language,
                              @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        User user = userService.getUserDataByLogin(detailsService.getCurrentUsername());
        model.addAttribute(user);
        if(LocalizationController.getLang().equals(language)) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "user/UserProfile";
    }


    @RequestMapping(value = "/profile/change-name/{login}", method = RequestMethod.POST)
    public String changeUserName(Model model,
                                 @PathVariable String login,
                                 @RequestParam("name") String newName,
                                 @RequestParam(value = "lang", defaultValue = "en_US") String language,
                                 @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        User user = new User();
        user.setLogin(login);
        user.setName(newName);
        userService.updateUserNameByLogin(user);
        if(LocalizationController.getLang().equals(language)) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "redirect:/profile";
    }
  
    @RequestMapping("user/orders")
    public String userOrders(Model model,
                             @RequestParam(value = "lang", defaultValue = "en_US") String language,
                             @RequestParam(value = "lang_changed", defaultValue = "false") boolean lang_changed) {
        User user = userService.getUserByLogin(detailsService.getCurrentUsername());
        List<Order> orders = userService.getAllUserOrders(user.getId());
        model.addAttribute("orders", orders);
        if(LocalizationController.getLang().equals(language)) {

            LocalizationController.setLang(language);
        }
        model.addAttribute("language", LocalizationController.getLang());
        return "user/userOrders";
    }

}

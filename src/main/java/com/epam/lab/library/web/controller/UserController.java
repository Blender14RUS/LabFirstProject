package com.epam.lab.library.web.controller;

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

@Controller
public class UserController {

    @Autowired
    private LibService libService;

    @RequestMapping(value={"/","/index"})
    public String defaultPage (Model model){
        return "index";
    }

    @RequestMapping("/admin")
    public String showAll (Model model){
        List<User> users = libService.getAllUsers();
        //model.addAttribute("checked", );
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

    @RequestMapping(value = "/user/update-access/{id}/{access_level}",method = RequestMethod.POST)
    public String updateUserAccessLevel (@PathVariable("id") Long id,
                                         @PathVariable("access_level") String access_level){
        libService.updateUserAccessLevel(id, access_level);
        return "redirect:/admin";
    }
}

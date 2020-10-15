package com.example.practica_2.controllers;

import java.util.List;
import java.util.Locale;

import com.example.practica_2.entities.Role;
import com.example.practica_2.entities.User;
import com.example.practica_2.repository.RoleRepository;
import com.example.practica_2.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private MessageSource messageSource;

    @GetMapping({"/", ""})
    public String listUsers(Model model, Locale locale) {
        List<User> usersList = userServices.findAll();
        model.addAttribute("usersList", usersList);
        model.addAttribute("action", messageSource.getMessage("createUser", null, locale));
        return "listUsers";
    }

    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("action", "Crear user");
        model.addAttribute("postAddress", "/users/create");
        return "createUpdateViewUser";
    }

    @PostMapping("/create")
    public String createUser(User user, @RequestParam(defaultValue = "null") String isAdmin) {

        System.out.println("\n\n\n"+isAdmin);
        System.out.println(isAdmin.getClass());

        String rolePrefix = "ROLE_";
        /**
         * When admin is true from the template, it arrives as false i the controller.
         * When it is false, it doesn't arrive at all. Hence the manuelly set default "null" value.
         */
        if(!isAdmin.equals("null")){
            Role adminRole = roleRepository.findByName(rolePrefix+"ADMIN");
            user.addToRolesList(adminRole);
        }

        userServices.save(user);
        return "redirect:/users/";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable() Long userId) {
        User user = userServices.findById(userId);
        userServices.delete(user);
        return "redirect:/users/";
    }
}

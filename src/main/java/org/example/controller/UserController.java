package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserName(Model model){
        userService.createTableUsers();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("countOfUsers",users.size());
        return "users";
    }

    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        userService.createTableUsers();
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(
            @RequestParam(value = "name") String name
            ,@RequestParam(value = "lastName") String lastName
            ,@RequestParam(value = "age") Integer age
            ,@RequestParam(value = "email") String email
            ,Model model
    ) {

        userService.addUser(new User(name,lastName,age,email));
        return "redirect:/users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/edit-user")
    public String showEditUserForm(@RequestParam Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/update-user")
    public String updateUser(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam Integer age,
            @RequestParam String email,
            Model model
    ) {
        User user = new User(name, lastName, age, email);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }
}
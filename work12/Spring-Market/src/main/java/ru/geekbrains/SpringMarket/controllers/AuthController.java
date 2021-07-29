package ru.geekbrains.SpringMarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.SpringMarket.configurations.jwt.JwtProvider;
import ru.geekbrains.SpringMarket.model.User;
import ru.geekbrains.SpringMarket.services.UserService;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/signup")
    public String registerUser(@RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setPassword(password);
        user.setUserName(login);
        userService.saveUser(user);
        return "new user: " + login + " password " + password;
//        return "OK";
    }

    @GetMapping("/login")
    public String authUser(@RequestParam String login, @RequestParam String password) {
        User user = userService.findByLoginAndPassword(login, password);
        String token = jwtProvider.generateToken(user.getUserName());
//        return new AuthResponseDto(token);
        return "login user: " + login + " password " + password + " Token " + token;
    }
}

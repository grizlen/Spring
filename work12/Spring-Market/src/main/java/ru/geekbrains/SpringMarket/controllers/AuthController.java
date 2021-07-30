package ru.geekbrains.SpringMarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringMarket.configurations.jwt.JwtProvider;
import ru.geekbrains.SpringMarket.model.User;
import ru.geekbrains.SpringMarket.model.dto.AuthDTO;
import ru.geekbrains.SpringMarket.model.dto.AuthResponseDto;
import ru.geekbrains.SpringMarket.services.UserService;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public AuthResponseDto registerUser(@RequestBody AuthDTO authDTO) {
        User user = new User();
        user.setPassword(authDTO.getPassword());
        user.setUserName(authDTO.getLogin());
        userService.saveUser(user);
        String token = jwtProvider.generateToken(user.getUserName());
        return new AuthResponseDto(authDTO.getLogin(), token);
    }

    @PostMapping("/login")
    public AuthResponseDto authUser(@RequestBody AuthDTO authDTO) {
        User user = userService.findByLoginAndPassword(authDTO);
        String token = jwtProvider.generateToken(user.getUserName());
        return new AuthResponseDto(authDTO.getLogin(), token);
    }
}

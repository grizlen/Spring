package ru.geekbrains.SpringSecurityapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.SpringSecurityapp.entities.User;
import ru.geekbrains.SpringSecurityapp.services.UserService;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/score")
public class AppScoreController {

    private final UserService userService;

    @GetMapping
    public String get() {
        String ret = "<p>/app/score/inc - увеличивает балл текущего пользователя</p>" +
                "<p>/app/score/dec - уменьшает балл текущего пользователя</p>" +
                "<p>/app/score/get/current - показывает балл вошедшего пользователя</p>" +
                "<p>/app/score/get/{id} - показывает балл пользователя с указанным id</p>";

        return ret;
    }

    @GetMapping("/inc")
    public String incCurrentScore(Principal principal) {
        if (principal != null) {
            return String.format("you score = %d", userService.modUserScore(principal.getName(), 1));
        } else {
            return "Can not modify user score.";
        }
    }

    @GetMapping("/dec")
    public String decCurrentScore(Principal principal) {
        if (principal != null) {
            return String.format("you score = %d", userService.modUserScore(principal.getName(), -1));
        } else {
            return "Can not modify user score.";
        }
    }

    @GetMapping("/get/current")
    public String getCurrentScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("find user name"));
        return String.format("you score = %d", user.getScore());
    }

    @GetMapping("/get/{id}")
    public String getId(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return String.format("user (%d) score = %d", id, user.get().getScore());
        } else {
            return String.format("user (%d) not found", id);
        }
    }
}

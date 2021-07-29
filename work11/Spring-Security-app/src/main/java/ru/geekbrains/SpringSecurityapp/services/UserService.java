package ru.geekbrains.SpringSecurityapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.SpringSecurityapp.entities.Role;
import ru.geekbrains.SpringSecurityapp.entities.User;
import ru.geekbrains.SpringSecurityapp.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", userName)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public int modUserScore(String userName, int value) {
        User user = findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", userName)));
        Integer score = user.getScore() + value;
        user.setScore(score);
        userRepository.save(user);
        return score;
    }
}

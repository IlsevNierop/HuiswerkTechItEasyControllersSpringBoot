package com.example.huiswerktechiteasycontrollerspringboot.services;

import com.example.huiswerktechiteasycontrollerspringboot.dtos.UserDto;
import com.example.huiswerktechiteasycontrollerspringboot.exceptions.RecordNotFoundException;
import com.example.huiswerktechiteasycontrollerspringboot.models.Authority;
import com.example.huiswerktechiteasycontrollerspringboot.models.User;
import com.example.huiswerktechiteasycontrollerspringboot.repositories.UserRepository;
import com.example.huiswerktechiteasycontrollerspringboot.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*moest hier niet een annotatie?*/

@Service
public class UserService {
    /*inject de juiste repository*/

    private final UserRepository repos;

    @Autowired
    @Lazy
    // (de spring context etc variant) zodat we niet in een loop van jwtreqfilter . custom service, userservice, security config komen
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repos) {
        this.repos = repos;
    }

    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = repos.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = repos.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new RecordNotFoundException("User not found with " + username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return repos.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);

        // ervoor zorgen dat het password wel gecodeerd wordt!
        User newUser = repos.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        repos.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!repos.existsById(username)) throw new RecordNotFoundException("User not found with " + username);
        User user = repos.findById(username).get();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        repos.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!repos.existsById(username)) throw new RecordNotFoundException("User not found with " + username);
        User user = repos.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!repos.existsById(username)) throw new RecordNotFoundException("User not found with " + username);
        User user = repos.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        repos.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!repos.existsById(username)) throw new RecordNotFoundException("User not found with " + username);
        User user = repos.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        repos.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        // ervoor zorgen dat het password wel gecodeerd wordt!
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        // ervoor zorgen dat het password wel gecodeerd wordt!
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

}

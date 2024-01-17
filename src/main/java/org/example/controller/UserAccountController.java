package org.example.controller;

import org.example.model.exception.InvalidDataException;
import org.example.model.request.CreateUserRequest;
import org.example.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/create")
    public Long createUser(@RequestBody CreateUserRequest request) {
        return userAccountService.createUser(request);
    }
}

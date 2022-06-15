package com.deloitte.todo.controllers;

import com.deloitte.todo.inputs.UserInput;
import com.deloitte.todo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * @param userInput
     * @return
     */
    @PostMapping(value = "/save/user")
    public String createNewUser(
            @RequestBody @Valid UserInput userInput) {
        log.info("UserController:createNewUser");
        return userService.createNewUser(userInput);
    }

}

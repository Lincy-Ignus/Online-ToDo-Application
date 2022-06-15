package com.deloitte.todo.web;

import com.deloitte.todo.TodoApplication;
import com.deloitte.todo.constants.TodoConstants;
import com.deloitte.todo.controllers.TodoController;
import com.deloitte.todo.controllers.UserController;
import com.deloitte.todo.dto.TodoItem;
import com.deloitte.todo.inputs.UserInput;
import com.deloitte.todo.repositories.TodoRepository;
import com.deloitte.todo.services.TodoService;
import com.deloitte.todo.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
   
    @Mock
    TodoRepository todoRepository;
    @Test
    void createNewUsers() {
        UserInput userInput = new UserInput();
        userInput.setName("test");
        userInput.setPassword("pwd123");
        when(userService.createNewUser(any(UserInput.class))).thenReturn(TodoConstants.SUCCESS.toString());
        String status =userController.createNewUser(userInput);
        Assertions.assertThat(status).isEqualTo(TodoConstants.SUCCESS.toString());

        when(userService.createNewUser(any(UserInput.class))).thenReturn(TodoConstants.FAILED.toString());
        status =userController.createNewUser(userInput);
        Assertions.assertThat(status).isEqualTo(TodoConstants.FAILED.toString());
    }

  
}
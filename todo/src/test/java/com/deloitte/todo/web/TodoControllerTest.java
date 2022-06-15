package com.deloitte.todo.web;

import com.deloitte.todo.TodoApplication;
import com.deloitte.todo.constants.TodoConstants;
import com.deloitte.todo.controllers.TodoController;
import com.deloitte.todo.dto.TodoItem;
import com.deloitte.todo.inputs.UserInput;
import com.deloitte.todo.models.User;
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
class TodoControllerTest {
    @InjectMocks
    private TodoController todoController;

    @Mock
    private TodoService service;

    @Mock
    TodoRepository todoRepository;

    @Test
    void fetchAllTodoItems() {
        List<TodoItem> list = new ArrayList<>();
        TodoItem todoItem = new TodoItem();
        todoItem.setTask("task1");
        list.add(todoItem);
        when(service.fetchAllToDoItems(any())).thenReturn(list);
        List<TodoItem> ret = todoController.fetchAllTodoItems(1);
        Assertions.assertThat(ret).isEqualTo(list);
    }

    @Test
    void updateTodoItems() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTask("");
        when(service.updateTodoItems(any(),any(TodoItem.class),any())).thenReturn(todoItem);
        ResponseEntity<?> responseEntity  = todoController.updateTodoItems(1,1,todoItem);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(todoItem);
    }

    @Test
    void createNewTodoItems() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTask("");
        when(service.createNewTodoItems(any(TodoItem.class),any())).thenReturn(TodoConstants.SUCCESS.toString());
        String status =todoController.createNewTodoItems(1,todoItem);
        Assertions.assertThat(status).isEqualTo(TodoConstants.SUCCESS.toString());

        when(service.createNewTodoItems(any(TodoItem.class),any())).thenReturn(TodoConstants.FAILED.toString());
        status =todoController.createNewTodoItems(1,todoItem);
        Assertions.assertThat(status).isEqualTo(TodoConstants.FAILED.toString());
    }

    @Test
    void deleteTodoItem() {
        when(service.deleteTodoItem(any())).thenReturn(TodoConstants.DELETED.toString());
        String ret = todoController.deleteTodoItem(1);
        Assertions.assertThat(ret).isEqualTo(TodoConstants.DELETED.toString());
    }
}
package com.deloitte.todo.services;


import com.deloitte.todo.constants.TodoConstants;
import com.deloitte.todo.dto.TodoItem;
import com.deloitte.todo.mappers.TodoMapper;
import com.deloitte.todo.models.ToDo;
import com.deloitte.todo.models.User;
import com.deloitte.todo.repositories.TodoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * to-do-service
 */
@Service
public class TodoService {

    Logger log = LoggerFactory.getLogger(TodoService.class);
     static final String PATTERN = "dd-MMM-yyyy HH:MM:SS";

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserService userService;

    /**
     * 
     * @param userId
     * @return
     */
    public List<TodoItem> fetchAllToDoItems(Integer userId) {
        log.info("entering fetchAllTodoItems");
        List<ToDo> entiyList = (List<ToDo>) todoRepository.findAllByUserId(userId);
        return TodoMapper.mapToDTO(entiyList);
    }

    /**
     * 
     * @param id
     * @param todoItem
     * @param userId
     * @return
     */
    public TodoItem updateTodoItems(Integer id, TodoItem todoItem, Integer userId) {
        log.info("entering updateTodoItems");
        ToDo savedToDo = null;
        Optional<ToDo> toDoOp = todoRepository.findById(id);
        User user = userService.getUser(userId);
        if (toDoOp.isPresent()) {
            savedToDo = toDoOp.get();
            if(!savedToDo.getUser().equals(user)){
                throw new RuntimeException("User has no authorization to update ToDo Item");
            }
            if (todoItem.getDone() != null) {//update the whether Done, only if the task is not null
                savedToDo.setDone(todoItem.getDone());
            }
            if (todoItem.getTask() != null) {//update the task only if the task is not null
                savedToDo.setTask(todoItem.getTask());
            }
            savedToDo.setLastUpdateTime(createNewTime());//create new time while updating the entry
            todoRepository.save(savedToDo);
            //map the updated entries back to DTO
            ModelMapper modelMapper = new ModelMapper();
            todoItem = modelMapper.map(savedToDo, TodoItem.class);
        }else {
            todoItem = null;
        }
        return todoItem;
    }

    /**
     * 
     * @param todoItem
     * @param userId
     * @return
     */
    public String createNewTodoItems(TodoItem todoItem, Integer userId) {
        log.info("entering create new to-do items");
        User user = userService.getUser(userId);
        ModelMapper modelMapper = new ModelMapper();

        //check whether the task already exists
        List<ToDo> entiyList = (List<ToDo>) todoRepository.findAll();
        Optional<ToDo> entityToUpdate = entiyList.stream().filter(e -> e.getTask().equals(todoItem.getTask())).findFirst();
        if (entityToUpdate.isPresent()) {//update
            updateTodoItems(entityToUpdate.get().getId(), todoItem, userId);
        } else {//save
            ToDo todo = modelMapper.map(todoItem, ToDo.class);
            todo.setLastUpdateTime(createNewTime());
            todo.setUser(user);
            try {
                todoRepository.save(todo);
            } catch (RuntimeException re) {
                log.error(re.getMessage());
                return TodoConstants.FAILED.toString();
            }
        }
        return TodoConstants.SUCCESS.toString();
    }

    /**
     * 
     * @param id
     * @return
     */
    public String deleteTodoItem(Integer id) {
        log.info("Repo entering deleteTodoItem");
        try {
            todoRepository.deleteById(id);
        } catch (RuntimeException re) {
            log.error(re.getMessage());
            return TodoConstants.FAILED.toString();
        }
        return TodoConstants.DELETED.toString();
    }

    /**
     * 
     * @return
     */
    public String createNewTime() {
        SimpleDateFormat sf = new SimpleDateFormat(PATTERN);
        return sf.format(new Date());
    }
}

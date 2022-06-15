package com.deloitte.todo.services;

import com.deloitte.todo.constants.TodoConstants;
import com.deloitte.todo.inputs.UserInput;
import com.deloitte.todo.models.User;
import com.deloitte.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param id
     * @return
     */
    public User getUser(Integer id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User does not exist"));
    }

    /**
     *
     * @param userInput
     * @return
     */
    public String createNewUser(UserInput userInput) {
        User user = new User(userInput.getName(),userInput.getPassword());
        userRepository.save(user);
        return TodoConstants.SUCCESS.toString();
    }
}

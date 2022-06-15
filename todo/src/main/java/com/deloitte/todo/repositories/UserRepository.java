package com.deloitte.todo.repositories;

import com.deloitte.todo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findById(Integer userId);
}

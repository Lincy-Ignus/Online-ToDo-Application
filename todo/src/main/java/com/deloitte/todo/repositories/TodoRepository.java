package com.deloitte.todo.repositories;

import com.deloitte.todo.models.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<ToDo,Integer> {
    List<ToDo> findAllByUserId(Integer userId);
}

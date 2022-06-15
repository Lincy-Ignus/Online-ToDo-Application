package com.deloitte.todo.mappers;

import com.deloitte.todo.dto.TodoItem;
import com.deloitte.todo.models.ToDo;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMapper {

    /**
     * method to map Entity To DTO List
     * @param entiyList
     * @return
     */
    public static List<TodoItem> mapToDTO(List<ToDo> entiyList){
            return entiyList.stream()
                .map(TodoItem::new).collect(Collectors.toList());
    }
}

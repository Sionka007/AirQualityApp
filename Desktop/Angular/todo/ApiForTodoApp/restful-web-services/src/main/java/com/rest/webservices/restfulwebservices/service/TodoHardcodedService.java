package com.rest.webservices.restfulwebservices.service;

import com.rest.webservices.restfulwebservices.entity.Todo;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardcodedService {

    private static List<Todo> todos=new ArrayList<Todo>();
    private static long idCounter;

    static {
        todos.add(new Todo(++idCounter, "in28minutes", "Learn to dance",
                new Date(), false));
        todos.add(new Todo(++idCounter, "in28minutes", "Learn to Microservices",
                new Date(), false));
        todos.add(new Todo(++idCounter, "in28minutes", "Learn to Angular",
                new Date(), false));
    }

    public List<Todo> findAll(){
        return todos;
    }

    public Todo deleteById(long id){
        Todo todo=findById(id);

        if(todos.remove(todo)) return todo;

        return null;

    }

    public Todo findById(long id) {
        for(Todo todo : todos){
            if(todo.getId()==id){
                return todo;
            }
        }
        return null;
    }

    public Todo save(Todo todo){
        if(todo.getId()== 0){
            todo.setId(++idCounter);
            todos.add(todo);
        }else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }


}

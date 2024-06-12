package com.rest.webservices.restfulwebservices.repository;

import com.rest.webservices.restfulwebservices.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUsername(String username);
    void deleteById(Long id);
}

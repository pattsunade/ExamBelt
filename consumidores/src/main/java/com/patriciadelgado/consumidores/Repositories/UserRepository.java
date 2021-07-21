package com.patriciadelgado.consumidores.Repositories;

import java.util.List;

import com.patriciadelgado.consumidores.Models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    User findByEmail(String email);
    
    List<User> findBySubssIdIsNotNull();
    
     boolean existsByEmail(String email);
}

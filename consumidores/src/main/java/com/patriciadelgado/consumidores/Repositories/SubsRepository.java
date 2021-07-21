package com.patriciadelgado.consumidores.Repositories;

import java.util.List;

// import java.util.List;

import com.patriciadelgado.consumidores.Models.Subs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsRepository extends CrudRepository<Subs, Long> {
    List<Subs> findAll();

    List<Subs> findAllByOrderByUsersDesc();

    Subs findByNameContaining(String name);
}

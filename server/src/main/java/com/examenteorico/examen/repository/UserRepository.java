package com.examenteorico.examen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examenteorico.examen.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

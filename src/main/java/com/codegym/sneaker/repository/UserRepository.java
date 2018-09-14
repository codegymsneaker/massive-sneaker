package com.codegym.sneaker.repository;

import com.codegym.sneaker.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findUserByFirstNameContaining(String firstName);

    Iterable<User> findUserByLastNameContaining(String lastName);

    User findUserByEmail(String email);
}

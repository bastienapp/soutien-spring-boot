package com.wildcodechool.moviefav.repository;

import com.wildcodechool.moviefav.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("SELECT u from User u WHERE u.username LIKE :username")
    Optional<User> findByUsername(String username);
}

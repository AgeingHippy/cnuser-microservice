package com.ageinghippy.cnuser_microservice.repository;

import com.ageinghippy.cnuser_microservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername(String username);

}

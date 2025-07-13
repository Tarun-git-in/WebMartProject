package com.project.WebMart.repository;

import com.project.WebMart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {


    Users findByUsername(String username);
}

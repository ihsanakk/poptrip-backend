package com.example.tripadvisorservice.repo;

import com.example.tripadvisorservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}

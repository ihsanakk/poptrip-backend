package com.example.tripadvisorservice.repo;

import com.example.tripadvisorservice.entity.Role;
import com.example.tripadvisorservice.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleName(ERole eRole);

}

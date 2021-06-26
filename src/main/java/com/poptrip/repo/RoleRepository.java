package com.poptrip.repo;

import com.poptrip.entity.Role;
import com.poptrip.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleName(ERole eRole);

}

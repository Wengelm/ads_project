package com.senla.ads.repository;


import com.senla.ads.entity.Role;
import com.senla.ads.entity.RoleType;
import com.senla.ads.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("roleRepository")
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.roles = :role")
    Role getRoleByName(@Param("role") RoleType roleType);
}

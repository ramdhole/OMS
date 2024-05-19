package com.mobileapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileapplication.entity.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByAdminName(String userName);

}

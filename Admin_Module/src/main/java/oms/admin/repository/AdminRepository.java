package oms.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oms.admin.entity.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByAdminName(String userName);

}

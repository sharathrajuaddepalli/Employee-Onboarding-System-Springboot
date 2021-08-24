package com.lister.employeeonboardingsystem.repository;

import com.lister.employeeonboardingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailIdAndPassword(String email, String password);
}

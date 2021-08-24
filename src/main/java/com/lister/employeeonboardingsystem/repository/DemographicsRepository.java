package com.lister.employeeonboardingsystem.repository;

import com.lister.employeeonboardingsystem.model.Demographics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DemographicsRepository extends JpaRepository<Demographics, String> {

    Demographics findByEmailId(String email);

    Demographics findByEmpId(int empId);

    @Query("select max(code) from Demographics")
    String findMaxCode();
}

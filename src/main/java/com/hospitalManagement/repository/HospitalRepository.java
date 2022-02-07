package com.hospitalManagement.repository;

import com.hospitalManagement.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Hospital findAllByName(String name);

    boolean existsByName(String name);
}

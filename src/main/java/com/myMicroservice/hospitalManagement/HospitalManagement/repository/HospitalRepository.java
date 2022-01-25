package com.myMicroservice.hospitalManagement.HospitalManagement.repository;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("from Hospital where name = :name")
    Hospital findByName(@Param("name") String name);
}

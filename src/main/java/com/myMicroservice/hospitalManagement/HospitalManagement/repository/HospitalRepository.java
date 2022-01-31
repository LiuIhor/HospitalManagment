package com.myMicroservice.hospitalManagement.HospitalManagement.repository;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    //    @Query("from Hospital where name = :name")
//    Hospital findAllByNAme(@Param("name") String name);
    Hospital findAllByName(String name);

    boolean existsByName(String name);
}

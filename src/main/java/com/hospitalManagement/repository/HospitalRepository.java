package com.hospitalManagement.repository;

import com.hospitalManagement.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    /**
     * Finds all hospitals by name
     *
     * @param name - this is the hospital name by which the Hospital object will be received
     * @return Hospital
     */
    Hospital findAllByName(String name);

    /**
     * Finds all hospitals by name
     *
     * @param name - this is the hospital name
     * @return if hospital exists - true else false
     */
    boolean existsByName(String name);
}

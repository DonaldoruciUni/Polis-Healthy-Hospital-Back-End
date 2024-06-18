package com.hospitalapp.repository;

import com.hospitalapp.model.AdmissionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionStateRepository extends JpaRepository<AdmissionState, Long> {
}

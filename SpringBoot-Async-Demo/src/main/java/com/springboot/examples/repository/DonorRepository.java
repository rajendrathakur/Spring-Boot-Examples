package com.springboot.examples.repository;

import com.springboot.examples.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {

}

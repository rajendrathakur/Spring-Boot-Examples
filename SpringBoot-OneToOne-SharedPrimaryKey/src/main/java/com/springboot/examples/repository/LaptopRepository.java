package com.springboot.examples.repository;

import com.springboot.examples.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
  Optional<Laptop> findLaptopByEmployeeId(Long id);
}

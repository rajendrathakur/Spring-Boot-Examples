package com.springboot.examples.repository;

import com.springboot.examples.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees")
    Set<Department> findWithJoinFetch();

    @EntityGraph(attributePaths = {
            "employees",
             "employees.projects",
            "employees.projects.manager"
             }, type = EntityGraph.EntityGraphType.LOAD)
    List<Department> findAll();

 /*   @EntityGraph(value = Department.Employees_Projects, type = EntityGraph.EntityGraphType.LOAD)
    List<Department> findAll();*/

}

package com.springboot.examples.repository;

import com.springboot.examples.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);

    List<Author> readByName(String name);

    List<Author> getByName(String name);

    List<Author> queryByName(String name);

    int countByName(String rajendra);

    List<Author> findByAge(int age);

    List<Author> findByNameAndAge(String name, int age);

    List<Author> findByNameOrAge(String name, int age);

    List<Author> findByNameIsNotIn(List<String> asList);

    List<Author> findByActiveTrue();

    List<Author> findByNameIsNull();

    List<Author> findByNameStartingWith(String prefix);

    List<Author> findByNameEndingWith(String prefix);

    List<Author> findByNameContaining(String name);

    List<Author> findByAgeLessThan(int age);

    List<Author> findByAgeLessThanEqual(int age);

    List<Author> findByAgeBetween(int startAge, int endAge);

    List<Author> findByAgeIn(List<Integer> integers);

    List<Author> findByDobAfter(LocalDate parse);

    List<Author> findByDobAfterOrderByNameDesc(LocalDate parse);

    List<Author> findTop2ByDobAfterOrderByNameDesc(LocalDate parse);

    List<Author> findByBooksName(String title);

    @Query("SELECT DISTINCT a.name from Author a where a.active is ?1" )
    List<String> findDistinctNameByActive(boolean active);


    void deleteByAge(int age);

    void deleteAllByName(String name);
}

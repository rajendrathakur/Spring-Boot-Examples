package com.springboot.examples;

import com.springboot.examples.entity.Author;
import com.springboot.examples.entity.Book;
import com.springboot.examples.repository.AuthorRepository;
import com.springboot.examples.repository.BookRepository;
import com.springboot.examples.utility.LazyTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SpringDataJPADerivedQueriesTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LazyTesting lazyTesting;

    @Test
    public void insertRecords() {
        Book book1 = new Book("Spring Boot", 450);
        Book book2 = new Book("Hibernate", 650);
        Book book3 = new Book("Java8", 340);
        Book book4 = new Book("AWS", 340);
        Book book5 = new Book("Azure", 340);

        Author author1 = new Author("Rajendra", 42, true, LocalDate.parse("1980-03-17"));
        Author author2 = new Author("Ajay", 32, false, LocalDate.parse("1990-08-27"));
        Author author3 = new Author("Rajendra", 36, true, LocalDate.parse("1986-04-14"));
        Author author4 = new Author("Ravi", 28, true, LocalDate.parse("1994-06-19"));

        //Author1 has written 4 books
        author1.getBooks().addAll(Arrays.asList(book1, book2, book3, book4));

        //Author2 has written 3 books
        author2.getBooks().addAll(Arrays.asList(book3, book4, book5));

        //Author3 has written 2 books
        author3.getBooks().addAll(Arrays.asList(book2, book3));

        //Author4 has written 1 book
        author4.getBooks().addAll(Arrays.asList(book5));

        authorRepository.saveAll(Arrays.asList(author1, author2, author3, author4));

    }

    //Fetch authors by their name using findBy
    @Test
    public void findByName() {
        List<Author> authors = authorRepository.findByName("Rajendra");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.age as age2_0_,
     * author0_.dob as dob3_0_,
     * author0_.name as name4_0_
     * from
     * author author0_
     * where
     * author0_.name=?
     * Output:
     * Rajendra
     * Rajendra
     */

    //Fetch authors by their name using getBy
    @Test
    public void getByName() {
        List<Author> authors = authorRepository.getByName("Rajendra");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.age as age2_0_,
     * author0_.dob as dob3_0_,
     * author0_.name as name4_0_
     * from
     * author author0_
     * where
     * author0_.name=?
     * Output:
     * Rajendra
     * Rajendra
     */

    //Fetch authors by their name using readBy
    @Test
    public void readByName() {
        List<Author> authors = authorRepository.readByName("Rajendra");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.age as age2_0_,
     * author0_.dob as dob3_0_,
     * author0_.name as name4_0_
     * from
     * author author0_
     * where
     * author0_.name=?
     * Output:
     * Rajendra
     * Rajendra
     */

    //Fetch authors by their name using queryBy
    @Test
    public void queryByName() {
        List<Author> authors = authorRepository.queryByName("Rajendra");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.age as age2_0_,
     * author0_.dob as dob3_0_,
     * author0_.name as name4_0_
     * from
     * author author0_
     * where
     * author0_.name=?
     * Output:
     * Rajendra
     * Rajendra
     */

    //Count the list of authors whose name is Rajendra
    @Test
    public void countByName() {
        int count = authorRepository.countByName("Rajendra");
        System.out.println(count);
    }

    /**
     * select
     * count(author0_.id) as col_0_0_
     * from
     * author author0_
     * where
     * author0_.name=?
     * Output:
     * 2
     */

    //Fetch authors by their age
    @Test
    public void findByAge() {
        List<Author> authors = authorRepository.findByAge(32);
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.age as age2_0_,
     * author0_.dob as dob3_0_,
     * author0_.name as name4_0_
     * from
     * author author0_
     * where
     * author0_.age=?
     * Output:
     * Ajay
     */


    //Usage of And for the combination of 2 attributes
    @Test
    public void findByNameAndAge() {
        List<Author> authors = authorRepository.findByNameAndAge("Rajendra", 42);
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.age as age2_0_,
     * author0_.dob as dob3_0_,
     * author0_.name as name4_0_
     * from
     * author author0_
     * where
     * author0_.name=?
     * and author0_.age=?
     * Output:
     * Rajendra
     */

    //Usage of Or for the combination of 2 attributes
    @Test
    public void findByNameOrAge() {
        List<Author> authors = authorRepository.findByNameOrAge("Rajendra", 42);
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.age as age2_0_,
     * author0_.dob as dob3_0_,
     * author0_.name as name4_0_
     * from
     * author author0_
     * where
     * author0_.name=?
     * or author0_.age=?
     * Output:
     * Rajendra
     * Rajendra
     */

    //Find authors whose name is not matching with list of names passed as input
    @Test
    public void findByNameIsNotIn() {
        List<Author> authors = authorRepository.findByNameIsNotIn(Arrays.asList("Rajendra", "Ajay"));
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.name not in  (
     * ? , ?
     * )
     * Output:
     * Ravi
     */

    @Test
    public void findByActiveTrue() {
        List<Author> authors = authorRepository.findByActiveTrue();
        authors.stream().map(Author::getName).forEach(System.out::println);

        /**
         *   select
         *         author0_.id as id1_0_,
         *         author0_.active as active2_0_,
         *         author0_.age as age3_0_,
         *         author0_.dob as dob4_0_,
         *         author0_.name as name5_0_
         *     from
         *         author author0_
         *     where
         *         author0_.active=1
         *
         *   Output:
         *   Rajendra
         *   Rajendra
         *   Ravi
         *
         */
    }

    //Usage of IsNotNull condition
    @Test
    public void findByNameIsNull() {
        List<Author> authors = authorRepository.findByNameIsNull();
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.name is null
     * Output:
     * No records
     */

    //Fetch authors whose name starting with Ra
    @Test
    public void findByNameStartingWith() {
        List<Author> authors = authorRepository.findByNameStartingWith("Ra");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.name like ? escape ?
     * Output:
     * Rajendra
     * Rajendra
     * Ravi
     */

    //Fetch authors whose name ending with vi
    @Test
    public void findByNameEndingWith() {
        List<Author> authors = authorRepository.findByNameEndingWith("vi");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.name like ? escape ?
     * Output:
     * Ravi
     */

    //Fetch authors whose name containing
    @Test
    public void findByNameContaining() {
        List<Author> authors = authorRepository.findByNameContaining("aj");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.name like ? escape ?
     * Output:
     * Rajendra
     * Ajay
     * Rajendra
     */

    //****************Comparison Condition Keywords*******************************

    //Fetch authors whose age is less than 40 years
    @Test
    public void findByAgeLessThan() {
        List<Author> authors = authorRepository.findByAgeLessThan(40);
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.age<?
     * Output:
     * Ajay
     * Rajendra
     * Ravi
     */

    //Fetch authors whose age is less than or equal to  36 years
    @Test
    public void findByAgeLessThanEqual() {
        List<Author> authors = authorRepository.findByAgeLessThanEqual(36);
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.age<=?
     * Output:
     * Ajay
     * Rajendra
     * Ravi
     */

    //Fetch authors whose age is between 30 & 40 years
    @Test
    public void findByAgeBetween() {
        List<Author> authors = authorRepository.findByAgeBetween(30, 40);
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.age between ? and ?
     * Output:
     * Ajay
     * Rajendra
     */


    //Fetch authors whose age is 32 & 36
    @Test
    public void findByAgeIn() {
        List<Author> authors = authorRepository.findByAgeIn(List.of(32, 36));
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.age in (
     * ? , ?
     * )
     * Output:
     * Ajay
     * Rajendra
     */

    //Fetch authors whose DOB is after 1985-01-01
    @Test
    public void findByDobAfter() {
        List<Author> authors = authorRepository.findByDobAfter(LocalDate.parse("1985-01-01"));
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.dob>?
     * Putput:
     * Ajay
     * Rajendra
     * Ravi
     */

    //****************Comparison Condition Keywords*******************************
    //****************Sorting the records using OrderBy*******************************

    //OrderByNameDesc sorts the records by their name in desc alphabetically
    @Test
    public void findByDobAfterOrderByNameDesc() {
        List<Author> authors = authorRepository.findByDobAfterOrderByNameDesc(LocalDate.parse("1985-01-01"));
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.dob>?
     * order by
     * author0_.name desc
     * Output:
     * Ravi
     * Rajendra
     * Ajay
     */
    //****************Sorting the records using OrderBy*******************************

    //Limit the results either Top2 or First2
    @Test
    public void findTop2ByDobAfterOrderByNameDesc() {
        List<Author> authors = authorRepository.findTop2ByDobAfterOrderByNameDesc(LocalDate.parse("1985-01-01"));
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.dob>?
     * order by
     * author0_.name desc limit ?
     * Output:
     * Ravi
     * Rajendra
     */

    //Fetch the list of authors who wrote book with title Java8
    @Test
    public void findByBooksName() {
        List<Author> authors = authorRepository.findByBooksName("Java8");
        authors.stream().map(Author::getName).forEach(System.out::println);
    }

    /**
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * left outer join
     * author_books books1_
     * on author0_.id=books1_.author_id
     * left outer join
     * book book2_
     * on books1_.books_id=book2_.id
     * where
     * book2_.name=?
     * Output:
     * Rajendra
     * Ajay
     * Rajendra
     */

    //Write a JPQL query to find distinct author names by their active field
    @Test
    public void findDistinctNameByActive() {
        List<String> authorNames = authorRepository.findDistinctNameByActive(true);
        authorNames.stream().forEach(System.out::println);
    }

    /**
     * select
     * distinct author0_.name as col_0_0_
     * from
     * author author0_
     * where
     * author0_.active=?
     * Output:
     * Rajendra
     * Ravi
     */

    // Delete a author record whose age is 28 years.
    @Test
    void deleteByAge() {
        lazyTesting.deleteByAge(28);
    }

    /**
     * Query - 1:
     * select
     * author0_.id as id1_0_,
     * author0_.active as active2_0_,
     * author0_.age as age3_0_,
     * author0_.dob as dob4_0_,
     * author0_.name as name5_0_
     * from
     * author author0_
     * where
     * author0_.age=?
     * <p>
     * Query - 2:
     * delete
     * from
     * author_books
     * where
     * author_id=?
     * <p>
     * Query - 3:
     * delete
     * from
     * author
     * where
     * id=?
     */

    // Delete a author records whose name is Rajendra
    @Test
    void deleteAllByName() {
        lazyTesting.deleteAllByName("Rajendra");
    }

    /**
     * Query - 1:
     * select
     *         author0_.id as id1_0_,
     *         author0_.active as active2_0_,
     *         author0_.age as age3_0_,
     *         author0_.dob as dob4_0_,
     *         author0_.name as name5_0_
     *     from
     *         author author0_
     *     where
     *         author0_.name=?
     *
     *  Query - 2:
     *  delete
     *     from
     *         author_books
     *     where
     *         author_id=?
     *
     *   Query - 3:
     *  delete
     *     from
     *         author_books
     *     where
     *         author_id=?
     *
     *    Query - 4:
     *  delete
     *     from
     *         author
     *     where
     *         id=?
     *
     *    Query - 5:
     *  delete
     *     from
     *         author
     *     where
     *         id=?
     */

}

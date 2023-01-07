package com.springboot.examples.utility;

import com.springboot.examples.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class LazyTesting {
    private AuthorRepository authorRepository;

    @Transactional
    public void deleteByAge(int age) {
        authorRepository.deleteByAge(age);
    }
    @Transactional
    public void deleteAllByName(String name) {
        authorRepository.deleteAllByName("rajendra");
    }
}

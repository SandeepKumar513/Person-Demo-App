package com.myproject.persondemo.service;

import com.myproject.persondemo.controller.Delegater;
import com.myproject.persondemo.model.Person;
import com.myproject.persondemo.repo.PersonRepo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OptionServiceTest {
    @Autowired
    PersonRepo repo;
    @MockBean
    Delegater delegater;

    @Test
    @Order(1)
    @Rollback(false)
    void add() {
        Person person = new Person();
        person.setId(123);
        person.setFirstName("test");
        person.setLastName("test");
        repo.save(person);
        Optional<Person> res =repo.findById(123);
        assertThat(res).isNotEmpty();
    }
    @Test
    @Order(2)
    @Rollback(false)
    void edit() {
        Optional<Person> person =repo.findById(123);
        person.get().setFirstName("abc");
        person.get().setLastName("xyz");
        Optional<Person> ups =repo.findById(123);
        assertThat(ups.get().getFirstName()).isEqualTo("abc");

    }

    @Test
    @Order(3)
    void count() {
        repo.count();
        assertThat(repo.count()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Person> persons = (List<Person>) repo.findAll();
        assertThat(persons).size().isGreaterThan(0);
    }

    @Test
    @Order(5)
    @Rollback(false)
    void delete() {
        Optional<Person> person =repo.findById(123);
        repo.deleteById(person.get().getId());
        Optional<Person> delPerson =repo.findById(123);
        assertThat(delPerson).isEmpty();
    }
}

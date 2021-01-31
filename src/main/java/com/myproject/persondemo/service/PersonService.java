package com.myproject.persondemo.service;

import com.myproject.persondemo.model.Person;
import com.myproject.persondemo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;

    public void save(Person person) {
        personRepo.save(person);
    }
    public ResponseEntity<String> update(Person person) {
        Optional<Person> person1 = personRepo.findById(person.getId());
        if (!person1.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        personRepo.save(person);
        return new ResponseEntity(HttpStatus.OK);
    }

    public void delete(int id) {
        personRepo.deleteById(id);
    }

    public List<Person> getAllPersons() {
        List<Person> pList = new ArrayList<Person>();
        personRepo.findAll().forEach(person -> pList.add(person));
        return pList;
    }

    public long getcount() {
        return personRepo.count();
    }
}

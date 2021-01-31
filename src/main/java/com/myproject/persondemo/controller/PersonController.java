package com.myproject.persondemo.controller;

import com.myproject.persondemo.model.Person;
import com.myproject.persondemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService service;

    //adds a person record
    @PostMapping("/person")
    private int addPerson(@RequestBody Person person){
        service.save(person);
        return person.getId();

    }
    //updates the person record
    @PutMapping("/person")
    private HttpStatus update(@RequestBody Person person) throws Exception {
        HttpStatus status = service.update(person).getStatusCode();
        return status;
    }
    //deletes the person record
    @DeleteMapping("person/{id}")
    private void deletePerson(@PathVariable("id") int id){
        service.delete(id);
    }
    //list of all persons
    @GetMapping("/personslist")
    private List<Person> getPersonsList(){
        return service.getAllPersons();
    }
    @GetMapping("/persons/count")
    private long getCount(){
        return service.getcount();
    }

}

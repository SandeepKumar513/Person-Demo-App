package com.myproject.persondemo.service;

import com.myproject.persondemo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class OptionService {
    @Autowired
    PersonService service;
    private static Logger logger = LoggerFactory.getLogger(OptionService.class);
    Person person = new Person();
    Scanner scan=new Scanner(System.in);
    public void add() {
        System.out.println("Please enter Person details to Add...");
        try {
            System.out.print("Enter id:");
            int id = Integer.parseInt(scan.next());
            person.setId(id);
            System.out.print("Enter firstName:");
            String fName = scan.next();
            person.setFirstName(fName);
            System.out.print("Enter lastName:");
            String lName = scan.next();
            person.setLastName(lName);
            service.save(person);
            System.out.println("Added Person Sucessfully...");
        }
        catch(Exception e) {
            System.out.println("Invalid entry! Please try again...");
            add();
            logger.debug("Invalid entry in Add:",e);
        }

    }

    public void edit() {
        System.out.println("Please enter Person details to Edit...");
        try {
            System.out.print("Enter id:");
            int id = Integer.parseInt(scan.next());
            person.setId(id);
            System.out.print("Enter firstName:");
            String fName = scan.next();
            person.setFirstName(fName);
            System.out.print("Enter lastName:");
            String lName = scan.next();
            person.setLastName(lName);
            String status = service.update(person).getStatusCode().toString();
            if(!status.contains("OK")) {
                System.out.println("Can't find any person with given ID");
                throw new Exception();//throwing exception inorder to continue program;
            }
            System.out.println("Person details edited Sucessfully...");
        }
        catch(Exception e) {
            System.out.println("Invalid Entry! Please Try Again...");
            edit();
            logger.debug("Invalid Entry in Edit:",e);
        }
    }

    public void delete() {
        System.out.println("Please enter Person ID to delete:");
        try {
            System.out.print("enter id:");
            int id = Integer.parseInt(scan.next());
            service.delete(id);
            System.out.println("Person record Deleted Sucessfully...");
        }
        catch(Exception e) {
            System.out.println("Invalid entry! Please try again...");
            delete();
            logger.debug("Invalid Entry in Delete:",e);
        }
    }

    public void count() {
        System.out.println("The total number of Persons:"+service.getcount());
    }

    public void getAll() {
        System.out.println("List of Persons:");
        List<Person> persons = service.getAllPersons();
        if(!persons.isEmpty()) {
            for (Person p : persons) {
                System.out.println("------------------------------");
                System.out.println("ID:"+p.getId());
                System.out.println("FirstName:"+p.getFirstName());
                System.out.println("LastName:"+p.getLastName());
            }
        }else{
            System.out.println("List is empty");
        }
    }
}
